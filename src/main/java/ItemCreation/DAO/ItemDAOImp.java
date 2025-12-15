package ItemCreation.DAO;

import Connection.DbConn;
import ItemCreation.Model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object implementation for Item Creation.
 * Handles all database operations related to creating new items/products.
 *
 * @author Tshiamo
 */
public class ItemDAOImp implements ItemDAO {

    private static final Logger logger = Logger.getLogger(ItemDAOImp.class.getName());

    @Override
    public Product getItem(int subcat_id) {
        String sql = "SELECT name, description, created_at, updated_at FROM types WHERE sub_category_id = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, subcat_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    Timestamp createdAt = rs.getTimestamp("created_at");
                    Timestamp updatedAt = rs.getTimestamp("updated_at");

                    return new Product(name, description, createdAt, updatedAt);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching item by subcategory ID", ex);
        }

        return null;
    }

    @Override
    public void addItem(Product item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
