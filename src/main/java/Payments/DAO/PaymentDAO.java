package Payments.DAO;

import Payments.Model.Payment;
import java.util.List;
import java.sql.*;
import java.util.Map;

public interface PaymentDAO {
    void save(Payment payment);
    Payment findById(int paymentId);
    List<Payment> findAll();
    List<Payment> findByMonth(int year, int month);
}
