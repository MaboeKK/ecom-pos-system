/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer.DAO;

import Customer.Model.Customer;

/**
 *
 * @author Tsheno
 */
public interface CustomerDAO {
    
    boolean addCustomer(String name,String surname,String email,String phone);
    Customer getCustomerByEmail(String email);
    Customer getCustomerByPhone(String phone);
    Customer getRecentCustomer();
    Customer getCustomerById(int customer_id);
    Customer getCustomerByOrder(int order_id);
    
}
