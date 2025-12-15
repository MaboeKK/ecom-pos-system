package Order.Model;

import java.sql.Timestamp;
import lombok.*;

@Getter@Setter
public class Order {
    private int id;
    private int user;
    private double total;
    private String status;
    private int store;
    private int customer;
    private Timestamp create;
    private Timestamp update;
}
