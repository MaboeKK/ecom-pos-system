package Payments.Service;

import Payments.Model.Payment;
import Payments.DAO.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class PaymentServiceImpl implements PaymentService {
    private PaymentDAO paymentDAO = new PaymentDAOImpl();
    
    public PaymentServiceImpl(PaymentDAO paymentDAO){
        this.paymentDAO = paymentDAO;
    }

    @Override
    public void savePayment(Payment payment) {
        paymentDAO.save(payment);
    }

    @Override
    public Payment findPaymentById(int paymentId) {
        return paymentDAO.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentDAO.findAll();
    }

    @Override
    public List<Payment> getPaymentsByMonth(int year, int month) {
        return paymentDAO.findByMonth(year, month);
    }




}
