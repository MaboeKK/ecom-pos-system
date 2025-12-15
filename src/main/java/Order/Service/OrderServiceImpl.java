/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Order.Service;

import Order.DAO.OrderDAO;
import Order.Model.Order;

import java.util.List;

/**
 *
 * @author User
 */
public class OrderServiceImpl implements OrderService {

    private OrderDAO od;

    public OrderServiceImpl(OrderDAO od){
        this.od = od;
    }

    @Override
    public boolean addOrder(Order order) {
        return od.addOrder(order.getUser(),order.getTotal(),order.getStatus(),order.getStore(),order.getCustomer());
    }

    @Override
    public Order getRecentOrder(int user) {
        return od.getRecentOrder(user);
    }

    @Override
    public boolean setOrderStatus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> failedOrders() {
        return od.failedOrders();
    }

    @Override
    public boolean updateStatus(int i, String status) {
        return od.setStatus(i, status);
    }

    @Override
    public List<Order> getOrders() {
        return od.getOrders();
    }

    @Override
    public boolean setStatus() {
        return od.setStatus();
    }

    @Override
    public List<Order> setAsideOrders() {
        return od.setAsideOrders();
    }

    @Override
    public boolean setSent(int id) {
        return od.setSent(id);
    }

    @Override
    public List<Order> getLayBuyOrders(int store) {
        return od.getLayBuyOrders(store);
    }

    @Override
    public Order getOrderById(int i) {
        return od.getOrderById(i);
    }

    @Override
    public List<Order> getCustomerOrders(String string,int store) {
        if(string.contains("@") || string.contains(".com"))return od.getLaybuysByEmail(string,store);
        return od.getLaybuysByPhone(string,store);
    }

    @Override
    public boolean setAmount(double amount, int id) {
        return od.setAmount(amount, id);
    }

    @Override
    public List<Order> succesfullOrders(String string,int store) {
        if(string.contains("@") || string.contains(".com"))return od.getSuccessFullOrdersByEmail(string,store);
        return od.getSuccessFullOrdersByPhone(string,store);
    }

}
