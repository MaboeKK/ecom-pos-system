package Ratings.Service;

import Ratings.Model.Rating;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface RatingService {
    void addRating(Rating rating);
    Rating getRatingById(int ratingId);
    List<Rating> getRatingsForProduct(int productId);
    void updateRating(Rating rating);
    void deleteRating(int ratingId);

}
