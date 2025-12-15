/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResponseData;

import Customer.Model.Customer;
import Order.Model.Order;
import java.util.List;

/**
 *
 * @author User
 */
public class ResponseData {
    private List<Order> sales;
    private Customer customer;

    // Constructor
    public ResponseData(List<Order> sales, Customer customer) {
        this.sales = sales;
        this.customer = customer;
    }

    // Getters and Setters
    public List<Order> getSales() {
        return sales;
    }

    public void setSales(List<Order> sales) {
        this.sales = sales;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
