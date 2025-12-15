/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer.Service;

import Customer.DAO.CustomerDAO;
import Customer.Model.Customer;

/**
 *
 * @author Tsheno
 */
public class CustomerServiceImpl implements CustomerService{
    
    private CustomerDAO customerdao;
    
    public CustomerServiceImpl(CustomerDAO customerdao){
        this.customerdao = customerdao;
    }

    @Override
    public boolean registerCustomer(Customer customer) {
        return customerdao.addCustomer(customer.getName(), customer.getSurname(), customer.getEmail(), customer.getPhoneNo());
    }

    @Override
    public Customer getCustomer(String identify) {
        if(identify.contains("@") || identify.contains(".com"))return customerdao.getCustomerByEmail(identify);
        return customerdao.getCustomerByPhone(identify);
    }

    @Override
    public Customer getRecentCustomer() {
        return customerdao.getRecentCustomer();
    }

    @Override
    public Customer getCustomerById(int customer_id) {
        return customerdao.getCustomerById(customer_id);
    }

    @Override
    public Customer getCustomerByOrder(int order_id) {
        return customerdao.getCustomerByOrder(order_id);
    }
    
}
