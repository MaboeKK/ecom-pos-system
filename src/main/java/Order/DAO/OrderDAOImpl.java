package Order.DAO;

import Connection.DbConn;
import Order.Model.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object implementation for Order entity.
 * Handles all database operations related to orders.
 *
 * @author User
 */
public class OrderDAOImpl implements OrderDAO {

    private static final Logger logger = Logger.getLogger(OrderDAOImpl.class.getName());

    @Override
    public boolean addOrder(int user, double total, String status, int store, int customer) {
        String sql = "INSERT INTO orders (user_id, total_amount, status, store_id, customer_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, user);
            ps.setDouble(2, total);
            ps.setString(3, status);
            ps.setInt(4, store);
            ps.setInt(5, customer);
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error adding order", ex);
            return false;
        }
    }

    @Override
    public Order getRecentOrder(int user) {
        String sql = "SELECT * FROM orders WHERE user_id = ? ORDER BY ABS(TIMESTAMPDIFF(SECOND, created_at, NOW())) LIMIT 1";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, user);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Order order = new Order();
                    order.setId(rs.getInt("order_id"));
                    return order;
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching recent order", ex);
        }
        return new Order();
    }

    @Override
    public List<Order> failedOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE status = 'pending' AND created_at < ? AND sent = 'yes'";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now().minusMinutes(4)));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setId(rs.getInt("order_id"));
                    orders.add(order);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching failed orders", ex);
        }
        return orders;
    }

    @Override
    public boolean setStatus(int id, String status) {
        String sql = "UPDATE orders SET status = ? WHERE order_id = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error updating order status", ex);
            return false;
        }
    }

    @Override
    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("order_id"));
                order.setStatus(rs.getString("status"));
                order.setTotal(rs.getDouble("total_amount"));
                orders.add(order);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching all orders", ex);
        }
        return orders;
    }

    @Override
    public boolean setStatus() {
        String sql = "UPDATE orders SET status = 'pending' WHERE created_at < ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now().minusMinutes(5)));
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error setting order status", ex);
            return false;
        }
    }

    @Override
    public List<Order> setAsideOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE status = 'pending' AND created_at < ? AND sent = 'no'";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now().minusMinutes(2)));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setId(rs.getInt("order_id"));
                    order.setCustomer(rs.getInt("customer_id"));
                    orders.add(order);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching set aside orders", ex);
        }
        return orders;
    }

    @Override
    public boolean setSent(int id) {
        String sql = "UPDATE orders SET sent = 'yes' WHERE order_id = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error setting order as sent", ex);
            return false;
        }
    }

    @Override
    public List<Order> getLayBuyOrders(int store) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE status = 'pending' AND store_id = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, store);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setId(rs.getInt("order_id"));
                    order.setStatus(rs.getString("status"));
                    order.setTotal(rs.getDouble("total_amount"));
                    order.setCreate(rs.getTimestamp("created_at"));
                    orders.add(order);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching lay-buy orders", ex);
        }
        return orders;
    }

    @Override
    public Order getOrderById(int id) {
        String sql = "SELECT * FROM orders WHERE order_id = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Order order = new Order();
                    order.setId(rs.getInt("order_id"));
                    order.setStatus(rs.getString("status"));
                    order.setTotal(rs.getDouble("total_amount"));
                    return order;
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching order by ID", ex);
        }
        return new Order();
    }

    @Override
    public List<Order> getLaybuysByEmail(String email, int store) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders JOIN customer ON orders.customer_id = customer.customer_id WHERE status = 'pending' AND email = ? AND store_id = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setInt(2, store);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setId(rs.getInt("order_id"));
                    order.setStatus(rs.getString("status"));
                    order.setTotal(rs.getDouble("total_amount"));
                    order.setCreate(rs.getTimestamp("created_at"));
                    orders.add(order);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching lay-buys by email", ex);
        }
        return orders;
    }

    @Override
    public List<Order> getLaybuysByPhone(String phone, int store) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders JOIN customer ON orders.customer_id = customer.customer_id WHERE status = 'pending' AND PhoneNo = ? AND store_id = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, phone);
            ps.setInt(2, store);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setId(rs.getInt("order_id"));
                    order.setStatus(rs.getString("status"));
                    order.setTotal(rs.getDouble("total_amount"));
                    order.setCreate(rs.getTimestamp("created_at"));
                    orders.add(order);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching lay-buys by phone", ex);
        }
        return orders;
    }

    @Override
    public boolean setAmount(double amount, int id) {
        String sql = "UPDATE orders SET total_amount = ? WHERE order_id = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, amount);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error updating order amount", ex);
            return false;
        }
    }

    @Override
    public List<Order> getSuccessFullOrdersByEmail(String customer, int store) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders JOIN customer ON orders.customer_id = customer.customer_id WHERE status = 'accepted' AND email = ? AND store_id = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, customer);
            ps.setInt(2, store);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setId(rs.getInt("order_id"));
                    order.setTotal(rs.getDouble("total_amount"));
                    order.setCreate(rs.getTimestamp("created_at"));
                    orders.add(order);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching successful orders by email", ex);
        }
        return orders;
    }

    @Override
    public List<Order> getSuccessFullOrdersByPhone(String customer, int store) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders JOIN customer ON orders.customer_id = customer.customer_id WHERE status = 'accepted' AND PhoneNo = ? AND store_id = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, customer);
            ps.setInt(2, store);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setId(rs.getInt("order_id"));
                    order.setTotal(rs.getDouble("total_amount"));
                    order.setCreate(rs.getTimestamp("created_at"));
                    orders.add(order);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching successful orders by phone", ex);
        }
        return orders;
    }
}
