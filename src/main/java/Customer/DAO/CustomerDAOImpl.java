package Customer.DAO;

import Connection.DbConn;
import Customer.Model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object implementation for Customer entity.
 * Handles all database operations related to customers.
 *
 * @author Tsheno
 */
public class CustomerDAOImpl implements CustomerDAO {

    private static final Logger logger = Logger.getLogger(CustomerDAOImpl.class.getName());

    @Override
    public boolean addCustomer(String name, String surname, String email, String phone) {
        String sql = "INSERT INTO customer (first_name, surname, email, PhoneNo) VALUES (?, ?, ?, ?)";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, surname);
            ps.setString(3, email);
            ps.setString(4, phone);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error adding customer", ex);
            return false;
        }
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        String sql = "SELECT * FROM customer WHERE email = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToCustomer(rs);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching customer by email", ex);
        }
        return new Customer();
    }

    @Override
    public Customer getCustomerByPhone(String phone) {
        String sql = "SELECT * FROM customer WHERE PhoneNo = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, phone);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToCustomer(rs);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching customer by phone", ex);
        }
        return new Customer();
    }

    @Override
    public Customer getRecentCustomer() {
        String sql = "SELECT * FROM customer ORDER BY customer_id DESC LIMIT 1";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return mapResultSetToCustomer(rs);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching recent customer", ex);
        }
        return new Customer();
    }

    @Override
    public Customer getCustomerById(int customerId) {
        String sql = "SELECT * FROM customer WHERE customer_id = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToCustomer(rs);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching customer by ID", ex);
        }
        return new Customer();
    }

    @Override
    public Customer getCustomerByOrder(int orderId) {
        String sql = "SELECT * FROM customer JOIN orders ON customer.customer_id = orders.customer_id WHERE order_id = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToCustomer(rs);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching customer by order ID", ex);
        }
        return new Customer();
    }

    /**
     * Maps a ResultSet row to a Customer object.
     * Reduces code duplication across methods.
     */
    private Customer mapResultSetToCustomer(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getInt("customer_id"));
        customer.setName(rs.getString("first_name"));
        customer.setSurname(rs.getString("surname"));
        customer.setPhoneNo(rs.getString("PhoneNo"));
        customer.setEmail(rs.getString("email"));
        return customer;
    }
}
