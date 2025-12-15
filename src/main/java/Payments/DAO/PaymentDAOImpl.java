package Payments.DAO;

import Payments.Model.Payment;
import Connection.DbConn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object implementation for Payment entity.
 * Handles all database operations related to payments.
 *
 * @author User
 */
public class PaymentDAOImpl implements PaymentDAO {

    private static final Logger logger = Logger.getLogger(PaymentDAOImpl.class.getName());

    @Override
    public void save(Payment payment) {
        String sql = "INSERT INTO payments (order_id, amount, status, payment_date, updated_at) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, payment.getOrderId());
            ps.setDouble(2, payment.getAmount());
            ps.setString(3, payment.getStatus());
            ps.setTimestamp(4, Timestamp.valueOf(payment.getPaymentDate()));
            ps.setTimestamp(5, Timestamp.valueOf(payment.getUpdatedDate()));
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    payment.setPaymentId(rs.getInt(1));
                }
            }
            logger.info("Payment saved successfully with ID: " + payment.getPaymentId());

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error saving payment", ex);
        }
    }

    @Override
    public Payment findById(int paymentId) {
        String sql = "SELECT * FROM payments WHERE payment_id = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, paymentId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return extractPaymentFromResultSet(rs);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error finding payment by ID", ex);
        }
        return null;
    }

    @Override
    public List<Payment> findAll() {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payments";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                payments.add(extractPaymentFromResultSet(rs));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error finding all payments", ex);
        }
        return payments;
    }

    @Override
    public List<Payment> findByMonth(int year, int month) {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM payments WHERE YEAR(payment_date) = ? AND MONTH(payment_date) = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, year);
            ps.setInt(2, month);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    payments.add(extractPaymentFromResultSet(rs));
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error finding payments by month", ex);
        }
        return payments;
    }

    /**
     * Extracts a Payment object from a ResultSet row.
     */
    private Payment extractPaymentFromResultSet(ResultSet rs) throws SQLException {
        Payment payment = new Payment();
        payment.setPaymentId(rs.getInt("payment_id"));
        payment.setOrderId(rs.getInt("order_id"));
        payment.setAmount(rs.getDouble("amount"));
        payment.setStatus(rs.getString("status"));
        payment.setPaymentDate(rs.getTimestamp("payment_date").toLocalDateTime());
        payment.setUpdatedDate(rs.getTimestamp("updated_at").toLocalDateTime());
        return payment;
    }
}
