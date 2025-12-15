package Ratings.Controller;

import Customer.DAO.CustomerDAOImpl;
import Customer.Model.Customer;
import Customer.Service.CustomerService;
import Customer.Service.CustomerServiceImpl;

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

@WebServlet("/sendRatingRequest")
public class SendRatingRequestServlet extends HttpServlet {

    private CustomerService customerService = new CustomerServiceImpl(new CustomerDAOImpl());

    // Email configuration from environment variables
    private static final String SMTP_HOST = getEnvOrDefault("SMTP_HOST", "smtp.mailgun.org");
    private static final int SMTP_PORT = Integer.parseInt(getEnvOrDefault("SMTP_PORT", "587"));
    private static final String SMTP_USER = getEnvOrDefault("SMTP_USER", "");
    private static final String SMTP_PASSWORD = getEnvOrDefault("SMTP_PASSWORD", "");
    private static final String APP_BASE_URL = getEnvOrDefault("APP_BASE_URL", "http://localhost:8080/Ecom-1.0-SNAPSHOT");

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

        if (customerIdStr == null || productIdStr == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing required parameters");
            return;
        }

        int customerId;
        int productId;
        try {
            customerId = Integer.parseInt(customerIdStr);
            productId = Integer.parseInt(productIdStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format");
            return;
        }

        // Fetch customer details
        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Customer not found");
            return;
        }

        // Construct the URL to RateUs.jsp with query parameters
        String rateUsUrl = APP_BASE_URL + "/RateUs.jsp?customer_id=" + customerId;

        sendEmail(customer.getEmail(), customer.getName(), rateUsUrl);

        response.sendRedirect("notificationPage.jsp"); // Redirect to a notification page
    }

    private void sendEmail(String toEmail, String customerName, String rateUsUrl) {
        if (SMTP_USER.isEmpty() || SMTP_PASSWORD.isEmpty()) {
            // Skip email if credentials not configured
            return;
        }

        Email email = EmailBuilder.startingBlank()
                .from(SMTP_USER)
                .to(toEmail)
                .withSubject("We Value Your Feedback!")
                .withHTMLText(String.format("Dear %s,<br><br>We value your feedback! Please click the link below to rate your recent experience with our product.<br><br><a href=\"%s\">Rate Us</a><br><br>Best regards,<br>Carol's Boutique", customerName, rateUsUrl))
                .buildEmail();

        Mailer mailer = MailerBuilder
                .withSMTPServer(SMTP_HOST, SMTP_PORT, SMTP_USER, SMTP_PASSWORD)
                .buildMailer();

        mailer.sendMail(email);
    }
}
