/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Order.DAO;


import Order.Model.Order;
import java.util.List;

/**
 *
 * @author User
 */
public interface OrderDAO {
    boolean addOrder(int user,double total,String status,int store,int customer);
    Order getRecentOrder(int user);
    List<Order> failedOrders();
    List<Order> setAsideOrders();
    boolean setStatus(int id,String status);
    List<Order> getOrders();
    boolean setStatus();
    boolean setSent(int id);
    Order getOrderById(int id);
    List<Order> getLayBuyOrders(int store);
    List<Order> getLaybuysByEmail(String email,int store);
    List<Order> getLaybuysByPhone(String phone,int store);
    boolean setAmount(double amount,int id);
    List<Order> getSuccessFullOrdersByEmail(String customer,int store);
    List<Order> getSuccessFullOrdersByPhone(String customer,int store);
}
