package Store.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Connection.DbConn;

@WebServlet("/UpdateStoreServlet")
public class UpdateStoreServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UpdateStoreServlet.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String storeId = request.getParameter("store_id");
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        String address = request.getParameter("address");

        if (storeId == null || storeId.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Store ID is missing.");
            return;
        }

        String query = "UPDATE stores SET province = ?, city = ?, address = ? WHERE store_id = ?";
        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, province);
            ps.setString(2, city);
            ps.setString(3, address);
            ps.setInt(4, Integer.parseInt(storeId));

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                response.sendRedirect("StoreList.jsp");  // Redirect back to the store list
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Store not found.");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error updating store", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
