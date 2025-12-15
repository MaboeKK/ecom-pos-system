package Ratings.Model;

import java.sql.Timestamp;
import lombok.*;

@Data
public class Rating {
    private int ratingId;
    private int productId;
    private int customerId;
    private int rating;
    private String comments;
    private Timestamp rateDate;
    private int storeId;
}
