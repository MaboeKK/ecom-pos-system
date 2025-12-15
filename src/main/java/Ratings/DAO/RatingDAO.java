package Ratings.DAO;

import Ratings.Model.Rating;

import java.util.List;
import java.sql.SQLException;
import java.util.Map;

public interface RatingDAO {
    void saveRating(Rating rating);
    Rating getRating(int ratingId);
    List<Rating> getRatingsForProduct(int productId);
    void updateRating(Rating rating);
    void deleteRating(int ratingId);
}
