package Ratings.DAO;

import Ratings.Model.Rating;
import Connection.DbConn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object implementation for Rating entity.
 * Handles all database operations related to product ratings.
 *
 * @author User
 */
public class RatingDAOImpl implements RatingDAO {

    private static final Logger logger = Logger.getLogger(RatingDAOImpl.class.getName());

    private static final String INSERT_RATING_SQL = "INSERT INTO ratings (product_id, customer_id, rating, comments, rating_date) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_RATING_BY_ID = "SELECT * FROM ratings WHERE rating_id = ?";
    private static final String SELECT_RATINGS_BY_PRODUCT = "SELECT * FROM ratings WHERE product_id = ?";
    private static final String UPDATE_RATING_SQL = "UPDATE ratings SET product_id = ?, customer_id = ?, rating = ?, comments = ?, rating_date = ? WHERE rating_id = ?";
    private static final String DELETE_RATING_SQL = "DELETE FROM ratings WHERE rating_id = ?";

    @Override
    public void saveRating(Rating rating) {
        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_RATING_SQL)) {

            ps.setInt(1, rating.getProductId());
            ps.setInt(2, rating.getCustomerId());
            ps.setInt(3, rating.getRating());
            ps.setString(4, rating.getComments());
            ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            ps.executeUpdate();
            logger.info("Rating saved successfully");

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error saving rating", ex);
        }
    }

    @Override
    public Rating getRating(int ratingId) {
        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_RATING_BY_ID)) {

            ps.setInt(1, ratingId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToRating(rs);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching rating by ID", ex);
        }
        return null;
    }

    @Override
    public List<Rating> getRatingsForProduct(int productId) {
        List<Rating> ratings = new ArrayList<>();

        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_RATINGS_BY_PRODUCT)) {

            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ratings.add(mapResultSetToRating(rs));
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error fetching ratings for product", ex);
        }
        return ratings;
    }

    @Override
    public void updateRating(Rating rating) {
        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_RATING_SQL)) {

            ps.setInt(1, rating.getProductId());
            ps.setInt(2, rating.getCustomerId());
            ps.setInt(3, rating.getRating());
            ps.setString(4, rating.getComments());
            ps.setTimestamp(5, rating.getRateDate());
            ps.setInt(6, rating.getRatingId());
            ps.executeUpdate();
            logger.info("Rating updated successfully");

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error updating rating", ex);
        }
    }

    @Override
    public void deleteRating(int ratingId) {
        try (Connection conn = DbConn.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_RATING_SQL)) {

            ps.setInt(1, ratingId);
            ps.executeUpdate();
            logger.info("Rating deleted successfully");

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error deleting rating", ex);
        }
    }

    /**
     * Maps a ResultSet row to a Rating object.
     */
    private Rating mapResultSetToRating(ResultSet rs) throws SQLException {
        Rating rating = new Rating();
        rating.setRatingId(rs.getInt("rating_id"));
        rating.setProductId(rs.getInt("product_id"));
        rating.setCustomerId(rs.getInt("customer_id"));
        rating.setRating(rs.getInt("rating"));
        rating.setComments(rs.getString("comments"));
        rating.setRateDate(rs.getTimestamp("rateDate"));
        return rating;
    }
}
