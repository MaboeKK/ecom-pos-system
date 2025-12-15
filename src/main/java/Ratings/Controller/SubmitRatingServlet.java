package Ratings.Controller;

import Customer.DAO.CustomerDAOImpl;
import Customer.Model.Customer;
import Customer.Service.CustomerService;
import Customer.Service.CustomerServiceImpl;
import Ratings.Model.Rating;

import Ratings.Service.RatingService;
import Ratings.Service.RatingServiceImpl;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/submitRating")
public class SubmitRatingServlet extends HttpServlet {

    private RatingService ratingService = new RatingServiceImpl();
    private CustomerService customerService = new CustomerServiceImpl(new CustomerDAOImpl());

    // Email configuration from environment variables
    private static final String SMTP_HOST = getEnvOrDefault("SMTP_HOST", "smtp.gmail.com");
    private static final int SMTP_PORT = Integer.parseInt(getEnvOrDefault("SMTP_PORT", "587"));
    private static final String SMTP_USER = getEnvOrDefault("SMTP_USER", "");
    private static final String SMTP_PASSWORD = getEnvOrDefault("SMTP_PASSWORD", "");
    private static final String ADMIN_EMAIL = getEnvOrDefault("ADMIN_EMAIL", "admin@yourdomain.com");

    private static String getEnvOrDefault(String key, String defaultValue) {
        String value = System.getProperty(key);
        if (value != null && !value.isEmpty()) return value;
        value = System.getenv(key);
        if (value != null && !value.isEmpty()) return value;
        return defaultValue;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String customerIdStr = request.getParameter("customer_id");
        String productIdStr = request.getParameter("product_id");
        String ratingStr = request.getParameter("rating");
        String comments = request.getParameter("comments");

        if (customerIdStr == null || productIdStr == null || ratingStr == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing required parameters");
            return;
        }

        int customerId;
        int productId;
        int ratingValue;
        try {
            customerId = Integer.parseInt(customerIdStr);
            productId = Integer.parseInt(productIdStr);
            ratingValue = Integer.parseInt(ratingStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format");
            return;
        }

        Rating rating = new Rating();
        rating.setProductId(productId);
        rating.setCustomerId(customerId);
        rating.setRating(ratingValue);
        rating.setComments(comments);

        ratingService.addRating(rating);

        // Notify you about the rating submission
        notifyAdmin(customerId, productId, ratingValue, comments);

        response.sendRedirect("thankYou.jsp");
    }

    private void notifyAdmin(int customerId, int productId, int rating, String comments) {
        if (SMTP_USER.isEmpty() || SMTP_PASSWORD.isEmpty()) {
            // Skip email if credentials not configured
            return;
        }

        Email email = EmailBuilder.startingBlank()
                .from(SMTP_USER)
                .to(ADMIN_EMAIL)
                .withSubject("New Rating Received")
                .withPlainText(String.format("A new rating has been submitted:%n%n"
                        + "Customer ID: %d%n"
                        + "Product ID: %d%n"
                        + "Rating: %d stars%n"
                        + "Comments: %s", customerId, productId, rating, comments))
                .buildEmail();

        Mailer mailer = MailerBuilder
                .withSMTPServer(SMTP_HOST, SMTP_PORT, SMTP_USER, SMTP_PASSWORD)
                .buildMailer();

        mailer.sendMail(email);
    }
}
