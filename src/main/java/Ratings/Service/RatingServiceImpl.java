package Ratings.Service;

import Ratings.DAO.RatingDAO;
import Ratings.DAO.RatingDAOImpl;
import Ratings.Model.Rating;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class RatingServiceImpl implements RatingService {
    private RatingDAO ratingDAO = new RatingDAOImpl();

    @Override
    public void addRating(Rating rating) {
        ratingDAO.saveRating(rating);
    }

    @Override
    public Rating getRatingById(int ratingId) {
        return ratingDAO.getRating(ratingId);
    }

    @Override
    public List<Rating> getRatingsForProduct(int productId) {
        return ratingDAO.getRatingsForProduct(productId);
    }

    @Override
    public void updateRating(Rating rating) {
        ratingDAO.updateRating(rating);
    }

    @Override
    public void deleteRating(int ratingId) {
        ratingDAO.deleteRating(ratingId);
    }

}
