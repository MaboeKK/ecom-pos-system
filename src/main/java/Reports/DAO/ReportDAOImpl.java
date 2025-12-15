package Reports.DAO;

import Connection.DbConn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object implementation for Reports.
 * Handles all database operations related to sales and performance reports.
 *
 * @author User
 */
public class ReportDAOImpl implements ReportDAO {

    private static final Logger logger = Logger.getLogger(ReportDAOImpl.class.getName());

    @Override
    public List<Map<String, Object>> getTopStores(int limit) throws SQLException {
        List<Map<String, Object>> result = new ArrayList<>();
        String sql = "SELECT s.store_id, s.store_name, SUM(p.amount) AS total_sales " +
                "FROM payments p " +
                "JOIN stores s ON p.store_id = s.store_id " +
                "GROUP BY s.store_id, s.store_name " +
                "ORDER BY total_sales DESC LIMIT ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, limit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> storeData = new HashMap<>();
                    storeData.put("store_id", rs.getInt("store_id"));
                    storeData.put("store_name", rs.getString("store_name"));
                    storeData.put("total_sales", rs.getDouble("total_sales"));
                    result.add(storeData);
                }
            }
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> getTopRatedStores(int month, int limit) throws SQLException {
        List<Map<String, Object>> result = new ArrayList<>();
        String sql = "SELECT s.store_id, s.store_name, ROUND(AVG(r.rating), 2) AS average_rating " +
                "FROM ratings r " +
                "JOIN stores s ON r.store_id = s.store_id " +
                "WHERE MONTH(r.rating_date) = ? " +
                "GROUP BY s.store_id, s.store_name " +
                "ORDER BY average_rating DESC LIMIT ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, month);
            ps.setInt(2, limit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> storeData = new HashMap<>();
                    storeData.put("store_id", rs.getInt("store_id"));
                    storeData.put("store_name", rs.getString("store_name"));
                    storeData.put("average_rating", rs.getDouble("average_rating"));
                    result.add(storeData);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching top rated stores", ex);
            throw ex;
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> getMonthlySales(int storeId, int year) throws SQLException {
        List<Map<String, Object>> results = new ArrayList<>();
        String sql = "SELECT DATE_FORMAT(payment_date, '%M %Y') AS month, SUM(amount) AS total_sales " +
                "FROM payments WHERE store_id = ? AND YEAR(payment_date) = ? " +
                "GROUP BY month ORDER BY month";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, storeId);
            ps.setInt(2, year);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> data = new HashMap<>();
                    data.put("month", rs.getString("month"));
                    data.put("total_sales", rs.getDouble("total_sales"));
                    results.add(data);
                }
            }
        }
        return results;
    }

    @Override
    public List<Map<String, Object>> getTopSellingEmployees(int limit) throws SQLException {
        List<Map<String, Object>> result = new ArrayList<>();
        String sql = "SELECT u.FIRSTNAME, u.SURNAME, SUM(p.amount) AS total_sales " +
                "FROM payments p " +
                "JOIN user u ON p.employee_id = u.EMPLOYEEID " +
                "GROUP BY u.FIRSTNAME, u.SURNAME " +
                "ORDER BY total_sales DESC LIMIT ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, limit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> data = new HashMap<>();
                    data.put("FIRSTNAME", rs.getString("FIRSTNAME"));
                    data.put("SURNAME", rs.getString("SURNAME"));
                    data.put("total_sales", rs.getDouble("total_sales"));
                    result.add(data);
                }
            }
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> getStoreTargets(int month, int year, int target) throws SQLException {
        List<Map<String, Object>> result = new ArrayList<>();
        String sql = "SELECT store_id, SUM(amount) AS total_sales " +
                "FROM payments WHERE MONTH(payment_date) = ? AND YEAR(payment_date) = ? " +
                "GROUP BY store_id HAVING total_sales >= ? ORDER BY total_sales DESC";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, month);
            ps.setInt(2, year);
            ps.setInt(3, target);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> data = new HashMap<>();
                    data.put("store_id", rs.getInt("store_id"));
                    data.put("total_sales", rs.getDouble("total_sales"));
                    result.add(data);
                }
            }
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> getTopSellingProducts(int limit) throws SQLException {
        List<Map<String, Object>> result = new ArrayList<>();
        String sql = "SELECT p.product_id AS product_id, p.name AS product_name, SUM(pay.amount) AS total_sales " +
                "FROM payments pay JOIN products p ON pay.product_id = p.product_id " +
                "GROUP BY p.product_id, p.name ORDER BY total_sales DESC LIMIT ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, limit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> data = new HashMap<>();
                    data.put("product_id", rs.getInt("product_id"));
                    data.put("product_name", rs.getString("product_name"));
                    data.put("total_sales", rs.getDouble("total_sales"));
                    result.add(data);
                }
            }
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> getLeastPerformingStore() throws SQLException {
        List<Map<String, Object>> result = new ArrayList<>();
        String sql = "SELECT s.store_name, ROUND(AVG(p.amount)) AS average_sales " +
                "FROM payments p " +
                "JOIN stores s ON p.store_id = s.store_id " +
                "WHERE p.payment_date >= DATE_SUB(CURDATE(), INTERVAL 6 MONTH) " +
                "GROUP BY s.store_name ORDER BY average_sales ASC";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> data = new HashMap<>();
                data.put("store_name", rs.getString("store_name"));
                data.put("average_sales", rs.getDouble("average_sales"));
                result.add(data);
            }
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> getProductSalesReport(int productId) throws SQLException {
        List<Map<String, Object>> result = new ArrayList<>();
        String sql = "SELECT store_id, SUM(amount) AS total_sales " +
                "FROM payments " +
                "WHERE product_id = ? " +
                "GROUP BY store_id " +
                "ORDER BY total_sales DESC";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> data = new HashMap<>();
                    data.put("store_id", rs.getInt("store_id"));
                    data.put("total_sales", rs.getDouble("total_sales"));
                    result.add(data);
                }
            }
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> getCurrentDailySales(int storeId, double dailyTarget) throws SQLException {
        List<Map<String, Object>> result = new ArrayList<>();
        String sql = "SELECT SUM(amount) AS total_sales, ? AS daily_target " +
                "FROM payments " +
                "WHERE store_id = ? AND DATE(payment_date) = CURDATE()";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, dailyTarget);
            ps.setInt(2, storeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> data = new HashMap<>();
                    data.put("total_sales", rs.getDouble("total_sales"));
                    data.put("daily_target", rs.getDouble("daily_target"));
                    result.add(data);
                }
            }
        }
        return result;
    }
}
