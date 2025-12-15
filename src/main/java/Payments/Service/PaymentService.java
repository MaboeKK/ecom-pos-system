package Payments.Service;

import Payments.Model.Payment;

import java.sql.SQLException;
import java.util.List;

import java.util.List;
import java.util.Map;

public interface PaymentService {
    void savePayment(Payment payment);
    Payment findPaymentById(int paymentId);
    List<Payment> getAllPayments();
    List<Payment> getPaymentsByMonth(int year, int month);

}
