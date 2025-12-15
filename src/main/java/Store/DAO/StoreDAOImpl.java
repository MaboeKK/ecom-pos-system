package Store.DAO;

import Store.Model.Store;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Connection.DbConn;

/**
 * Data Access Object implementation for Store entity.
 * Handles all database operations related to stores.
 *
 * @author User
 */
public class StoreDAOImpl implements StoreDAO {

    private static final Logger logger = Logger.getLogger(StoreDAOImpl.class.getName());

    @Override
    public Store getStore(String address) {
        String sql = "SELECT * FROM stores WHERE address = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, address);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToStore(rs);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching store by address", ex);
        }
        return null;
    }

    @Override
    public void updateUser(Store store) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Store> storeList() {
        List<Store> stores = new ArrayList<>();
        String sql = "SELECT * FROM stores";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                stores.add(mapRowToStore(rs));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching store list", ex);
        }
        return stores;
    }

    @Override
    public void createStore(Store store) {
        String sql = "INSERT INTO stores (province, city, address) VALUES (?, ?, ?)";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, store.getSTORE_LOCATION_PROVINCE());
            ps.setString(2, store.getSTORE_LOCATION_CITY());
            ps.setString(3, store.getSTORE_LOCATION_ADDRESS());
            ps.executeUpdate();
            logger.info("Store added to database.");

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error creating store", ex);
        }
    }

    @Override
    public void createManagedStore(Store store) {
        String sql = "INSERT INTO stores (province, city, address, manager) VALUES (?, ?, ?, ?)";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, store.getSTORE_LOCATION_PROVINCE());
            ps.setString(2, store.getSTORE_LOCATION_CITY());
            ps.setString(3, store.getSTORE_LOCATION_ADDRESS());
            ps.setString(4, store.getMANAGER_ID());
            ps.executeUpdate();
            logger.info("Managed store added to database.");

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error creating managed store", ex);
        }
    }

    @Override
    public Store getStoreByManager(String manager) {
        String sql = "SELECT * FROM stores WHERE manager = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, manager);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToStore(rs);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching store by manager", ex);
        }
        return null;
    }

    @Override
    public boolean deleteStore(int storeId) {
        String sql = "DELETE FROM stores WHERE store_id = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, storeId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Store with ID " + storeId + " deleted successfully.");
                return true;
            } else {
                logger.warning("No store found with ID " + storeId + ".");
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error deleting store with ID " + storeId, ex);
        }
        return false;
    }

    @Override
    public Store getStoreById(int storeId) {
        String sql = "SELECT * FROM stores WHERE store_id = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, storeId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Store store = new Store();
                    store.setStore_id(rs.getInt("store_id"));
                    store.setStore_name(rs.getString("store_name"));
                    return store;
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching store by ID", ex);
        }
        return null;
    }

    /**
     * Maps a ResultSet row to a Store object.
     */
    private Store mapRowToStore(ResultSet rs) throws SQLException {
        return Store.builder()
                .store_id(rs.getInt("store_id"))
                .STORE_LOCATION_PROVINCE(rs.getString("province"))
                .STORE_LOCATION_CITY(rs.getString("city"))
                .STORE_LOCATION_ADDRESS(rs.getString("address"))
                .MANAGER_ID(rs.getString("manager"))
                .build();
    }
}
