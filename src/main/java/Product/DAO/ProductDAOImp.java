package Product.DAO;

import Connection.DbConn;
import ItemCreation.Model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object implementation for Product entity.
 * Handles all database operations related to products and inventory.
 *
 * @author Tshiamo
 */
public class ProductDAOImp implements ProductDAO {

    private static final Logger logger = Logger.getLogger(ProductDAOImp.class.getName());

    @Override
    public void createProduct(Product product) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Product getProduct(int type_ID) {
        String sql = "SELECT product_id, name, description, price, stock_quantity, type_id, created_at, updated_at, gendre, barcode, sub_category_id FROM products WHERE type_ID = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, type_ID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToFullProduct(rs);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching product by type ID", ex);
        }
        return null;
    }

    @Override
    public List<Product> getProducts(int type_ID) {
        List<Product> itemsList = new ArrayList<>();
        String sql = "SELECT product_id, name, description, price, stock_quantity, type_id, created_at, updated_at, gendre, barcode, sub_category_id FROM products WHERE type_id = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, type_ID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    itemsList.add(mapResultSetToProductWithDates(rs));
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching products by type ID", ex);
        }
        return itemsList;
    }

    @Override
    public Product getProduct(String barcode) {
        String sql = "SELECT * FROM products WHERE barcode = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, barcode);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("product_id"));
                    product.setPrice(rs.getDouble("price"));
                    product.setDescription(rs.getString("description"));
                    product.setName(rs.getString("name"));
                    return product;
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching product by barcode", ex);
        }
        return new Product();
    }

    @Override
    public boolean setQuantity(int stock, int id) {
        String sql = "UPDATE products SET stock_quantity = ? WHERE product_id = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, stock);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error updating product quantity", ex);
            return false;
        }
    }

    @Override
    public Product getProductById(int id) {
        String sql = "SELECT * FROM products WHERE product_id = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("product_id"));
                    product.setPrice(rs.getDouble("price"));
                    product.setDescription(rs.getString("description"));
                    product.setName(rs.getString("name"));
                    product.setQuantity(rs.getInt("stock_quantity"));
                    return product;
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching product by ID", ex);
        }
        return new Product();
    }

    @Override
    public List<Product> getShortage(int stockquantity) {
        List<Product> myList = new ArrayList<>();
        String sql = "SELECT product_id, name, description, price, stock_quantity, type_id, created_at, updated_at, gendre, barcode, sub_category_id FROM products WHERE stock_quantity < ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, stockquantity);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    myList.add(mapResultSetToProductWithDates(rs));
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching products with shortage", ex);
        }
        return myList;
    }

    @Override
    public void orderItem(int i) {
        String sql = "UPDATE products SET stock_quantity = ? WHERE product_id = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, i);
            ps.setInt(2, i);
            ps.executeUpdate();

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error ordering item", ex);
        }
    }

    @Override
    public Product UpdateItem(int productID, int quantity) {
        String sql = "UPDATE products SET stock_quantity = ? WHERE product_id = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, quantity);
            ps.setInt(2, productID);
            ps.executeUpdate();

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error updating item", ex);
        }
        return null;
    }

    @Override
    public void updateProduct(Product prdct) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Product> getProducts() {
        List<Product> itemsList = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setDescription(rs.getString("description"));
                itemsList.add(product);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching all products", ex);
        }
        return itemsList;
    }

    @Override
    public List<Product> getStoreProducts(int store) {
        List<Product> itemsList = new ArrayList<>();
        String sql = "SELECT * FROM store_products JOIN products ON store_products.productID = products.product_id WHERE storeID = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, store);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("product_id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getDouble("price"));
                    product.setDescription(rs.getString("description"));
                    product.setQuantity(rs.getInt("quantity"));
                    itemsList.add(product);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching store products", ex);
        }
        return itemsList;
    }

    @Override
    public Product getStoreProductById(int id, int store) {
        String sql = "SELECT store_productsID, products.price as price, description, name, quantity FROM store_products JOIN products ON store_products.productID = products.product_id WHERE product_id = ? AND storeID = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.setInt(2, store);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("store_productsID"));
                    product.setPrice(rs.getDouble("price"));
                    product.setDescription(rs.getString("description"));
                    product.setName(rs.getString("name"));
                    product.setQuantity(rs.getInt("quantity"));
                    return product;
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching store product by ID", ex);
        }
        return new Product();
    }

    @Override
    public boolean setStoreQuantity(int quantity, int id, int store) {
        String sql = "UPDATE store_products SET quantity = ? WHERE productID = ? AND storeID = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, quantity);
            ps.setInt(2, id);
            ps.setInt(3, store);
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error updating store quantity", ex);
            return false;
        }
    }

    @Override
    public Product getProductByBarcode(int store, String barcode) {
        String sql = "SELECT * FROM store_products JOIN products ON store_products.productID = products.product_id WHERE storeID = ? AND store_products.barcode = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, store);
            ps.setString(2, barcode);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("productID"));
                    product.setPrice(rs.getDouble("price"));
                    product.setDescription(rs.getString("description"));
                    product.setName(rs.getString("name"));
                    product.setQuantity(rs.getInt("quantity"));
                    return product;
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching product by barcode", ex);
        }
        return new Product();
    }

    @Override
    public List<Product> getProductByAmount(int store, double amount) {
        List<Product> itemsList = new ArrayList<>();
        String sql = "SELECT * FROM store_products JOIN products ON store_products.productID = products.product_id WHERE storeID = ? AND products.price = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, store);
            ps.setDouble(2, amount);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("product_id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getDouble("price"));
                    product.setDescription(rs.getString("description"));
                    itemsList.add(product);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching products by amount", ex);
        }
        return itemsList;
    }

    @Override
    public Product getStoreProductById(int i) {
        String sql = "SELECT * FROM store_products WHERE productID = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, i);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("productID"));
                    product.setPrice(rs.getDouble("price"));
                    product.setQuantity(rs.getInt("quantity"));
                    return product;
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching store product by ID", ex);
        }
        return new Product();
    }

    @Override
    public boolean setStoreQuantity(int stock, int id) {
        String sql = "UPDATE store_products SET quantity = ? WHERE productID = ?";

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, stock);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error updating store quantity", ex);
            return false;
        }
    }

    /**
     * Maps a ResultSet row to a full Product object with all fields.
     */
    private Product mapResultSetToFullProduct(ResultSet rs) throws SQLException {
        Timestamp createdAt = rs.getTimestamp("created_at");
        Timestamp updatedAt = rs.getTimestamp("updated_at");
        Date createdDate = createdAt != null ? new Date(createdAt.getTime()) : null;
        Date updatedDate = updatedAt != null ? new Date(updatedAt.getTime()) : null;

        return new Product(
            rs.getInt("product_id"),
            rs.getInt("sub_category_id"),
            rs.getString("name"),
            rs.getString("description"),
            rs.getString("barcode"),
            rs.getString("gendre"),
            rs.getDouble("price"),
            rs.getInt("stock_quantity"),
            rs.getInt("type_id"),
            createdDate,
            updatedDate
        );
    }

    /**
     * Maps a ResultSet row to a Product object with dates.
     */
    private Product mapResultSetToProductWithDates(ResultSet rs) throws SQLException {
        Timestamp createdAt = rs.getTimestamp("created_at");
        Timestamp updatedAt = rs.getTimestamp("updated_at");
        Date createdDate = createdAt != null ? new Date(createdAt.getTime()) : null;
        Date updatedDate = updatedAt != null ? new Date(updatedAt.getTime()) : null;

        return new Product(
            rs.getInt("product_id"),
            rs.getString("name"),
            rs.getString("description"),
            rs.getString("barcode"),
            rs.getString("gendre"),
            rs.getDouble("price"),
            rs.getInt("stock_quantity"),
            rs.getInt("type_id"),
            createdDate,
            updatedDate
        );
    }
}
