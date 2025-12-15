package User.Controller;

import Store.DAO.StoreDAO;
import Store.DAO.StoreDAOImpl;
import Store.Model.Store;
import User.DAO.UserDAOImpl;
import User.Model.User;
import User.Service.EmailService;
import User.Service.EmailServiceImpl;
import User.Service.SmsService;
import User.Service.SmsServiceImpl;
import User.Service.UserService;
import User.Service.UserServiceImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());
    private final Set<String> existingEmployeeIds = new HashSet<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StoreDAO storeDAO = new StoreDAOImpl();
        List<Store> stores = storeDAO.storeList();
        request.setAttribute("stores", stores);
        request.getRequestDispatcher("Register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService service = new UserServiceImpl(new UserDAOImpl());
        EmailService emailService = new EmailServiceImpl();
        SmsService smsService = new SmsServiceImpl();
        StoreDAO storeDAO = new StoreDAOImpl(); // Add this line to get the store DAO

        String employeeID = request.getParameter("employeeID");
        String firstname = request.getParameter("firstname");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String accessLevel = request.getParameter("accessLevel");
        String phoneNo = request.getParameter("PhoneNo");
        String storeIdStr = request.getParameter("store");

        int access = "Cashier".equalsIgnoreCase(accessLevel) ? 1 : "Floor Manager".equalsIgnoreCase(accessLevel) ? 2 : 0;

        if ("Register".equals(request.getParameter("submit"))) {
            if (firstname != null && surname != null && email != null
                    && password != null && confirmPassword != null &&
                    !firstname.isEmpty() && !surname.isEmpty() && !email.isEmpty()
                    && !password.isEmpty() && !confirmPassword.isEmpty()) {

                if (!password.equals(confirmPassword)) {
                    String msg = "Oops, the password and the confirmation password do not match. Please re-register and double-check.";
                    request.setAttribute("msg", msg);
                    request.getRequestDispatcher("Register.jsp").forward(request, response);
                    return;
                }

                int storeId;
                try {
                    storeId = Integer.parseInt(storeIdStr);
                } catch (NumberFormatException e) {
                    logger.log(Level.WARNING, "Invalid store ID: {0}", storeIdStr);
                    String msg = "Invalid store ID. Please select a valid store.";
                    request.setAttribute("msg", msg);
                    request.getRequestDispatcher("Register.jsp").forward(request, response);
                    return;
                }

                // Fetch store name
                Store store = storeDAO.getStoreById(storeId);
                String storeName = (store != null) ? store.getStore_name() : "Unknown Store";

                // Create user object
                String empId = generateUniqueEmployeeId();
                User user = User.builder()
                        .employeeID(employeeID)
                        .firstname(firstname)
                        .surname(surname)
                        .email(email)
                        .password(password)
                        .empId(empId)
                        .PhoneNo(phoneNo)
                        .accessLevel(access)
                        .store(storeId)  // Set store ID here
                        .registrationDate(LocalDateTime.now())
                        .build();

                try {
                    service.createUser(user);
                    logger.log(Level.INFO, "Registration Successful for: {0}", email);
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Error creating user: " + e.getMessage(), e);
                    String msg = "An error occurred while registering the user. Please try again.";
                    request.setAttribute("msg", msg);
                    request.getRequestDispatcher("Register.jsp").forward(request, response);
                    return;
                }

                String role;
                switch (access) {
                    case 1:
                        role = "Cashier";
                        break;
                    case 2:
                        role = "Floor Manager";
                        break;
                    case 3:
                        role = "Store Manager";
                        break;
                    default:
                        role = "Unknown Role";
                        break;
                }

                emailService.sendEmailToEmp(user.getEmail(), user.getFirstname(), role, storeName);
                // smsService.sendSMS(user.getPhoneNo(), "Congratulations!");

                response.sendRedirect("success.jsp");
            } else {
                String msg = "Oops, one or more required fields are empty. Please re-register and double-check.";
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            }
        }
    }


    private String generateUniqueEmployeeId() {
        Random random = new Random();
        String empId;

        do {
            int randomNumber = 100 + random.nextInt(900);
            empId = "emp" + randomNumber;
        } while (existingEmployeeIds.contains(empId));

        existingEmployeeIds.add(empId);
        return empId;
    }

    @Override
    public String getServletInfo() {
        return "UserController handles user registration and interactions.";
    }
}




