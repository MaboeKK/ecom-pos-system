/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer.Service;

import Customer.Model.Customer;

/**
 *
 * @author Tsheno
 */
public interface CustomerService {
    boolean registerCustomer(Customer customer);
    Customer getCustomer(String email);
    Customer getRecentCustomer();
    Customer getCustomerById(int customer_id);
    Customer getCustomerByOrder(int order_id);
}
