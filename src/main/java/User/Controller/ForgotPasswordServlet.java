package User.Controller;

import Connection.DbConn;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet for handling password reset requests.
 * Requires manager authorization to reset user passwords.
 *
 * @author Tshiamo
 */
@WebServlet(name = "ForgotPasswordServlet", urlPatterns = {"/forgotpassword"})
public class ForgotPasswordServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ForgotPasswordServlet.class.getName());
    private static final String MANAGER_CODE = "1234"; // Manager authorization code

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirect to the forgot password page
        request.getRequestDispatcher("Forgotpassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String managerCode = request.getParameter("managerCode");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        // Validate manager code
        if (!MANAGER_CODE.equals(managerCode)) {
            request.setAttribute("message", "Invalid manager authorization code.");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("Forgotpassword.jsp").forward(request, response);
            return;
        }

        // Validate passwords match
        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("message", "Passwords do not match.");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("Forgotpassword.jsp").forward(request, response);
            return;
        }

        // Validate password length
        if (newPassword.length() < 4) {
            request.setAttribute("message", "Password must be at least 4 characters.");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("Forgotpassword.jsp").forward(request, response);
            return;
        }

        // Update password in database
        String checkSql = "SELECT user_id FROM user WHERE email = ?";
        String updateSql = "UPDATE user SET password = ? WHERE email = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql);
             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

            // First check if user exists
            checkStmt.setString(1, email);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (!rs.next()) {
                    request.setAttribute("message", "No user found with that email address.");
                    request.setAttribute("messageType", "error");
                    request.getRequestDispatcher("Forgotpassword.jsp").forward(request, response);
                    return;
                }
            }

            // Update the password
            updateStmt.setString(1, newPassword);
            updateStmt.setString(2, email);
            int rowsUpdated = updateStmt.executeUpdate();

            if (rowsUpdated > 0) {
                request.setAttribute("message", "Password has been reset successfully. User can now login with the new password.");
                request.setAttribute("messageType", "success");
            } else {
                request.setAttribute("message", "Failed to reset password. Please try again.");
                request.setAttribute("messageType", "error");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error during password reset", e);
            request.setAttribute("message", "Database error occurred. Please try again later.");
            request.setAttribute("messageType", "error");
        }

        request.getRequestDispatcher("Forgotpassword.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Password Reset Servlet";
    }
}
