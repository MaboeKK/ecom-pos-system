package storeProducts.DAO;

import Connection.DbConn;
import Store.Model.StoreProduct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object implementation for Store Products.
 * Handles all database operations related to store inventory.
 *
 * @author Tshiamo
 */
public class StoreProductDAOImpl implements StoreProductDAO {

    private static final Logger logger = Logger.getLogger(StoreProductDAOImpl.class.getName());

    @Override
    public List<StoreProduct> storesInventory() {
        List<StoreProduct> storeProducts = new ArrayList<>();
        String sql = "SELECT * FROM store_products";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                storeProducts.add(mapResultSetToStoreProduct(rs));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching store inventory", ex);
        }

        return storeProducts;
    }

    @Override
    public StoreProduct getIBTItem(int store_productsID) {
        String sql = "SELECT store_productsID, productID, storeID, quantity, barcode, item_recived, last_update, colour, size, price, storeName, productName FROM store_products WHERE store_productsID = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, store_productsID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToStoreProduct(rs);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching IBT item", ex);
        }

        return null;
    }

    @Override
    public void IBTSubtract(int quantity, int id) {
        String sql = "UPDATE store_products SET quantity = ? WHERE store_productsID = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, quantity);
            ps.setInt(2, id);
            ps.executeUpdate();
            logger.info("Store product quantity updated for ID: " + id);

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error updating store product quantity", ex);
        }
    }

    @Override
    public List<StoreProduct> getProductForIBT(String name) {
        List<StoreProduct> mylist = new ArrayList<>();
        String sql = "SELECT store_productsID, productID, storeID, quantity, barcode, item_recived, last_update, colour, size, price, storeName, productName FROM store_products WHERE barcode = ? OR productName = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, name);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    mylist.add(mapResultSetToStoreProductAlt(rs));
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching products for IBT", ex);
        }

        return mylist;
    }

    @Override
    public List<StoreProduct> myInventory(int storeID) {
        List<StoreProduct> mylist = new ArrayList<>();
        String sql = "SELECT * FROM store_products WHERE storeID = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, storeID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    mylist.add(mapResultSetToStoreProductAlt(rs));
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching store inventory", ex);
        }

        return mylist;
    }

    @Override
    public List<StoreProduct> myInventoryShortage(int storeID) {
        List<StoreProduct> mylist = new ArrayList<>();
        String sql = "SELECT * FROM store_products WHERE storeID = ? AND quantity < 5";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, storeID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    mylist.add(mapResultSetToStoreProductAlt(rs));
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching inventory shortage", ex);
        }

        return mylist;
    }

    /**
     * Maps a ResultSet row to a StoreProduct object.
     */
    private StoreProduct mapResultSetToStoreProduct(ResultSet rs) throws SQLException {
        String storeProductsID = String.valueOf(rs.getInt("store_productsID"));
        String productID = String.valueOf(rs.getInt("productID"));
        String quantity = String.valueOf(rs.getInt("quantity"));
        String barcode = rs.getString("barcode");
        String itemReceived = String.valueOf(rs.getDate("item_recived"));
        String lastUpdate = String.valueOf(rs.getDate("last_update"));
        String size = rs.getString("size");
        String colour = rs.getString("colour");
        String storeName = rs.getString("storeName");
        String productName = rs.getString("productName");
        String price = rs.getString("price");

        return new StoreProduct(storeProductsID, productID, quantity, barcode, itemReceived, lastUpdate, size, colour, storeName, productName, price);
    }

    /**
     * Maps a ResultSet row to a StoreProduct object (alternative constructor).
     */
    private StoreProduct mapResultSetToStoreProductAlt(ResultSet rs) throws SQLException {
        String storeProductsID = String.valueOf(rs.getInt("store_productsID"));
        String productID = String.valueOf(rs.getInt("productID"));
        String storeID = String.valueOf(rs.getInt("storeID"));
        String quantity = String.valueOf(rs.getInt("quantity"));
        String barcode = rs.getString("barcode");
        String itemReceived = String.valueOf(rs.getDate("item_recived"));
        String lastUpdate = String.valueOf(rs.getDate("last_update"));
        String colour = rs.getString("colour");
        String price = Double.toString(rs.getDouble("price"));
        String storeName = rs.getString("storeName");
        String productName = rs.getString("productName");

        return new StoreProduct(storeProductsID, productID, storeID, quantity, barcode, itemReceived, lastUpdate, price, colour, storeName, productName);
    }
}
