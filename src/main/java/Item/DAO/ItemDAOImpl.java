package Item.DAO;

import Connection.DbConn;
import Item.Model.Item;
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
 * Data Access Object implementation for Item entity.
 * Handles all database operations related to order items.
 *
 * @author User
 */
public class ItemDAOImpl implements ItemDAO {

    private static final Logger logger = Logger.getLogger(ItemDAOImpl.class.getName());

    @Override
    public boolean addItem(int orderid, int productid, int quantity, double price) {
        String sql = "INSERT INTO orderitems (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderid);
            ps.setInt(2, productid);
            ps.setInt(3, quantity);
            ps.setDouble(4, price);
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error adding item", ex);
            return false;
        }
    }

    @Override
    public List<Integer> getProductId(int order) {
        List<Integer> items = new ArrayList<>();
        String sql = "SELECT * FROM orderitems WHERE order_id = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, order);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    items.add(rs.getInt("product_id"));
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching product IDs", ex);
        }
        return items;
    }

    @Override
    public boolean addItem(int orderid, int productid, int quantity, double price, int sale) {
        String sql = "INSERT INTO orderitems (order_id, product_id, quantity, price, sale_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderid);
            ps.setInt(2, productid);
            ps.setInt(3, quantity);
            ps.setDouble(4, price);
            ps.setInt(5, sale);
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error adding item with sale", ex);
            return false;
        }
    }

    @Override
    public boolean setSale(int order, int sale) {
        String sql = "UPDATE orderitems SET sale_id = ? WHERE order_items_id = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, sale);
            ps.setInt(2, order);
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error setting sale for item", ex);
            return false;
        }
    }

    @Override
    public List<Item> getItemBySale(int sale) {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM orderitems JOIN products ON orderitems.product_id = products.product_id WHERE sale_id = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, sale);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    items.add(mapResultSetToItemWithName(rs));
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching items by sale", ex);
        }
        return items;
    }

    @Override
    public boolean setReverse() {
        String sql = "UPDATE orderitems JOIN sales ON orderitems.sale_id = sales.sale_id SET reverse = 'no' WHERE sale_date < ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now().minusMinutes(5)));
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error setting reverse", ex);
            return false;
        }
    }

    @Override
    public List<Item> getReversedItem() {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM orderitems WHERE reverse = 'no'";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                items.add(mapResultSetToItem(rs));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching reversed items", ex);
        }
        return items;
    }

    @Override
    public boolean setReversal() {
        String sql = "UPDATE orderitems SET reverse = 'no'";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error setting reversal", ex);
            return false;
        }
    }

    @Override
    public boolean removeItem(int id) {
        String sql = "DELETE FROM orderitems WHERE order_item_id = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error removing item", ex);
            return false;
        }
    }

    @Override
    public Item getItemById(int id) {
        String sql = "SELECT * FROM orderitems JOIN products ON orderitems.product_id = products.product_id WHERE order_item_id = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Item item = new Item();
                    item.setItemid(rs.getInt("order_item_id"));
                    item.setPrice(rs.getDouble("price"));
                    item.setProduct(rs.getInt("product_id"));
                    item.setSale(rs.getInt("sale_id"));
                    item.setOrderid(rs.getInt("order_id"));
                    item.setName(rs.getString("name"));
                    return item;
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching item by ID", ex);
        }
        return new Item();
    }

    @Override
    public List<Item> getItemsByAmount(double price, int store) {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT order_item_id, order_id, price, product_id, quantity, sale_id, reverse FROM orderitems JOIN store_products ON orderitems.product_id = store_products.productID JOIN product ON store_products.productID = product.product_id WHERE storeID = ? AND store_products.price = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, store);
            ps.setDouble(2, price);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    items.add(mapResultSetToItem(rs));
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching items by amount", ex);
        }
        return items;
    }

    @Override
    public List<Item> getItemsByOrder(int order) {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM orderitems JOIN products ON orderitems.product_id = products.product_id WHERE order_id = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, order);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    items.add(mapResultSetToItemWithName(rs));
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching items by order", ex);
        }
        return items;
    }

    @Override
    public Item getRecentItem() {
        String sql = "SELECT * FROM orderitems JOIN products ON orderitems.product_id = products.product_id ORDER BY ABS(TIMESTAMPDIFF(SECOND, orderitems.created_at, NOW())) LIMIT 1";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                Item item = new Item();
                item.setItemid(rs.getInt("order_item_id"));
                item.setProduct(rs.getInt("product_id"));
                item.setName(rs.getString("name"));
                return item;
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching recent item", ex);
        }
        return new Item();
    }

    /**
     * Maps a ResultSet row to an Item object (without name).
     */
    private Item mapResultSetToItem(ResultSet rs) throws SQLException {
        Item item = new Item();
        item.setItemid(rs.getInt("order_item_id"));
        item.setOrderid(rs.getInt("order_id"));
        item.setPrice(rs.getDouble("price"));
        item.setProduct(rs.getInt("product_id"));
        item.setQuantity(rs.getInt("quantity"));
        item.setSale(rs.getInt("sale_id"));
        item.setReverse(rs.getString("reverse"));
        return item;
    }

    /**
     * Maps a ResultSet row to an Item object (with name from joined products table).
     */
    private Item mapResultSetToItemWithName(ResultSet rs) throws SQLException {
        Item item = mapResultSetToItem(rs);
        item.setName(rs.getString("name"));
        return item;
    }
}
