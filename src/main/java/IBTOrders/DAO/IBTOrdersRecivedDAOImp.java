package IBTOrders.DAO;

import Connection.DbConn;
import IBTOrders.Model.IBTRecived;
import IBTOrders.Model.StoreIBT;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object implementation for Inter-Branch Transfer orders.
 * Handles all database operations related to IBT transactions.
 *
 * @author Tshiamo
 */
public class IBTOrdersRecivedDAOImp implements IBTOrdersRecivedDAO {

    private static final Logger logger = Logger.getLogger(IBTOrdersRecivedDAOImp.class.getName());

    @Override
    public void addIBTRecived(IBTRecived iBTRecived) {
        String sql = "INSERT INTO ibt_ordersrecived (storeName, productID, storeID, productName, EmployeeID, price) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, iBTRecived.getStoreID());
            ps.setString(2, iBTRecived.getStore_ProductID());
            ps.setString(3, iBTRecived.getStoreID());
            ps.setString(4, iBTRecived.getProductName());
            ps.setString(5, iBTRecived.getEmployeeID());
            ps.setString(6, iBTRecived.getPrice());
            ps.executeUpdate();
            logger.info("IBT item received added successfully");

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error adding IBT received item", ex);
        }
    }

    @Override
    public List<IBTRecived> getIBTMadeList() {
        List<IBTRecived> myIBTs = new ArrayList<>();
        String sql = "SELECT * FROM ibt_ordersrecived";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                myIBTs.add(mapResultSetToIBTRecived(rs));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching IBT made list", ex);
        }

        return myIBTs;
    }

    @Override
    public IBTRecived getIBTItem(int i) {
        String sql = "SELECT IBTOrderID, storeName, productID, productName, price, EmployeeID FROM ibt_ordersrecived WHERE IBTOrderID = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, i);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToIBTRecived(rs);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching IBT item", ex);
        }

        return new IBTRecived();
    }

    @Override
    public void addOrderToStore(StoreIBT ibt) {
        String sql = "INSERT INTO storesibt (storeRequestingName, productName, orderDate, requestID, storeRecievengName, totalPrice, quantity) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ibt.getStoreRequestingName());
            ps.setString(2, ibt.getStoresIBT());
            ps.setString(3, ibt.getOrderDate());
            ps.setString(4, ibt.getRequestID());
            ps.setString(5, ibt.getStoreName());
            ps.setString(6, ibt.getTotalPrice());
            ps.setString(7, ibt.getQuantity());
            ps.executeUpdate();
            logger.info("IBT order added to store successfully");

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error adding order to store", ex);
        }
    }

    @Override
    public List<StoreIBT> getIBTOrders() {
        List<StoreIBT> myIBTs = new ArrayList<>();
        String sql = "SELECT * FROM storesibt";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                myIBTs.add(mapResultSetToStoreIBT(rs));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching IBT orders", ex);
        }

        return myIBTs;
    }

    /**
     * Maps a ResultSet row to an IBTRecived object.
     */
    private IBTRecived mapResultSetToIBTRecived(ResultSet rs) throws SQLException {
        String ibtOrderID = String.valueOf(rs.getInt("IBTOrderID"));
        String storeName = rs.getString("storeName");
        String productID = rs.getString("productID");
        String productName = rs.getString("productName");
        String price = String.valueOf(rs.getDouble("price"));
        String empId = rs.getString("EmployeeID");

        return new IBTRecived(ibtOrderID, productID, storeName, productName, price, empId);
    }

    /**
     * Maps a ResultSet row to a StoreIBT object.
     */
    private StoreIBT mapResultSetToStoreIBT(ResultSet rs) throws SQLException {
        String id = String.valueOf(rs.getInt("storesIBT"));
        String storeRequest = rs.getString("storeRequestingName");
        String prodName = rs.getString("productName");
        String date = rs.getString("orderDate");
        String requestID = rs.getString("requestID");
        String storeReceiving = rs.getString("storeRecievengName");
        String totalPrice = rs.getString("totalPrice");
        String quantity = rs.getString("quantity");

        return new StoreIBT(id, storeRequest, storeReceiving, date, requestID, prodName, totalPrice, quantity);
    }
}
