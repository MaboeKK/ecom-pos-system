package Sale.DAO;

import Connection.DbConn;
import Sale.Model.Sale;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object implementation for Sale entity.
 * Handles all database operations related to sales transactions.
 *
 * @author User
 */
public class SaleDAOImpl implements SaleDAO {

    private static final Logger logger = Logger.getLogger(SaleDAOImpl.class.getName());

    @Override
    public boolean addSale(int product, int employee, double amount, int store, int customer) {
        String sql = "INSERT INTO sales (product_id, employee_id, amount, store_id, customer_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, product);
            ps.setInt(2, employee);
            ps.setDouble(3, amount);
            ps.setInt(4, store);
            ps.setInt(5, customer);
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error adding sale", ex);
            return false;
        }
    }

    @Override
    public Sale getRecentSale(int user) {
        String sql = "SELECT * FROM sales WHERE employee_id = ? ORDER BY ABS(TIMESTAMPDIFF(SECOND, sale_date, NOW())) LIMIT 1";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, user);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Sale sale = new Sale();
                    sale.setId(rs.getInt("sale_id"));
                    return sale;
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching recent sale", ex);
        }
        return new Sale();
    }

    @Override
    public List<Sale> getSaleByPhone(String phone) {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT * FROM sales JOIN customer ON sales.customer_id = customer.customer_id WHERE PhoneNo = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, phone);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    sales.add(mapResultSetToSale(rs));
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching sales by phone", ex);
        }
        return sales;
    }

    @Override
    public List<Sale> getSaleByEmail(String email) {
        List<Sale> sales = new ArrayList<>();
        String sql = "SELECT * FROM sales JOIN customer ON sales.customer_id = customer.customer_id WHERE email = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    sales.add(mapResultSetToSale(rs));
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching sales by email", ex);
        }
        return sales;
    }

    @Override
    public boolean setAmount(double amount, int id) {
        String sql = "UPDATE sales SET amount = ? WHERE sale_id = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, amount);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error updating sale amount", ex);
            return false;
        }
    }

    @Override
    public boolean removeSale(int id) {
        String sql = "DELETE FROM sales WHERE sale_id = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error removing sale", ex);
            return false;
        }
    }

    @Override
    public Sale getSale(int id) {
        String sql = "SELECT * FROM sales JOIN orderitems ON sales.sale_id = orderitems.sale_id WHERE order_item_id = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Sale sale = new Sale();
                    sale.setId(rs.getInt("sale_id"));
                    return sale;
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching sale", ex);
        }
        return new Sale();
    }

    /**
     * Maps a ResultSet row to a Sale object.
     */
    private Sale mapResultSetToSale(ResultSet rs) throws SQLException {
        Sale sale = new Sale();
        sale.setId(rs.getInt("sale_id"));
        sale.setAmount(rs.getDouble("amount"));
        sale.setCustomer(rs.getInt("customer_id"));
        sale.setEmployee(rs.getInt("employee_id"));
        sale.setProduct(rs.getInt("product_id"));
        sale.setSaleDate(rs.getTimestamp("sale_date"));
        return sale;
    }
}
