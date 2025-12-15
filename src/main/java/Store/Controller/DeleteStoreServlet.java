package Store.Controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Store.DAO.StoreDAO;
import Store.DAO.StoreDAOImpl;
import Store.Service.StoreService;
import Store.Service.StoreServiceImpl;
import User.Model.User;
import User.Service.EmailService;
import User.Service.EmailServiceImpl;

@WebServlet("/DeleteStoreServlet")
public class DeleteStoreServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(DeleteStoreServlet.class.getName());
    private StoreService storeService;

    @Override
    public void init() throws ServletException {
        // Initialize the StoreDAO and StoreService
        StoreDAO storeDAO = new StoreDAOImpl(); // Create StoreDAO instance
        storeService = new StoreServiceImpl(storeDAO); // Pass StoreDAO to StoreServiceImpl

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EmailService emailService = new EmailServiceImpl();
        String storeId = request.getParameter("store_id");

        if (storeId == null || storeId.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Store ID is missing.");
            return;
        }

        try {
            int id = Integer.parseInt(storeId);

            // Call the service to delete the store
            boolean isDeleted = storeService.deleteStore(id);

            //Build User Parameters For Email
            String FIRSTNAME = request.getParameter("firstname");
            String EMAIL = request.getParameter("email");
            User user = User.builder()
                    .firstname(FIRSTNAME)
                    .email(EMAIL)
                    .build();

            if (isDeleted) {
                request.setAttribute("message", "Store successfully deleted.");
                emailService.sendEmailToRegMan(user.getEmail(), user.getFirstname(), storeId);


            } else {
                request.setAttribute("message", "Store not found.");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid store ID format.");
        } catch (Exception e) {
            // Handle any unexpected exceptions
            logger.log(Level.SEVERE, "Error deleting store", e);
            request.setAttribute("message", "Error occurred while deleting the store: " + e.getMessage());
        }

        // Forward to the JSP page to display the message
        request.getRequestDispatcher("StoreList.jsp").forward(request, response);
    }
}
