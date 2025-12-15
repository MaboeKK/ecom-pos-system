/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Order.Service;

import Order.Model.Order;
import java.util.List;


/**
 *
 * @author User
 */
public interface OrderService {

    boolean addOrder(Order order);
    Order getRecentOrder(int user);
    boolean setOrderStatus();
    List<Order> failedOrders();
    boolean updateStatus(int id,String status);
    List<Order> getOrders();
    boolean setStatus();
    List<Order> setAsideOrders();
    boolean setSent(int id);
    List<Order> getLayBuyOrders(int store);
    Order getOrderById(int id);
    List<Order> getCustomerOrders(String detail,int store);
    boolean setAmount(double amount,int id);
    List<Order> succesfullOrders(String customer,int store);
}
