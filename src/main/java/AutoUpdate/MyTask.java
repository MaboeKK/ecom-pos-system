/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AutoUpdate;

import Customer.DAO.CustomerDAOImpl;
import Customer.Model.Customer;
import Customer.Service.CustomerService;
import Customer.Service.CustomerServiceImpl;
import Item.DAO.ItemDAOImpl;
import Item.Service.ItemService;
import Item.Service.ItemServiceImpl;
import Order.DAO.OrderDAOImpl;
import Order.Model.Order;
import Order.Service.OrderService;
import Order.Service.OrderServiceImpl;
import Product.DAO.ProductDAOImp;
import Product.Service.ProductServices;
import Product.Service.ProductServicesImp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Tsheno
 */
public class MyTask implements Runnable {
    
    private OrderService os = new OrderServiceImpl(new OrderDAOImpl());
    private ItemService is = new ItemServiceImpl(new ItemDAOImpl());
    private ProductServices ps = new ProductServicesImp(new ProductDAOImp());
    private CustomerService cs = new CustomerServiceImpl(new CustomerDAOImpl());
    
    @Override
    public void run() {
        // Task logic goes here
        
        List<Order> aside = os.setAsideOrders();
        List<Order> failed = os.failedOrders();
        Customer customer = new Customer();
  
        for(Order order:aside){
            /*get the customer and send email*/
            os.setSent(order.getId());
            customer = cs.getCustomerById(order.getCustomer());
            String email = customer.getEmail();
        }
        
        List<Integer> products = new ArrayList<>();
        Map<Integer,Integer> proditem = new HashMap<>();
     
        for(Order order:failed){
            
            products.addAll(is.getProductId(order));
            os.updateStatus(order.getId(), "cancelled");
            
        }

        for(Integer i:products){
            if(proditem.containsKey(i)){
                proditem.put(i, proditem.get(i)+1);
            }else{
                proditem.put(i, 1);
            }   
        } 

        for(Map.Entry<Integer,Integer> entry:proditem.entrySet()){
            int quantity = ps.getStoreProductById(entry.getKey()).getQuantity();
       
            ps.setStoreQuantity(quantity + entry.getValue(), entry.getKey());
        }
        
/*        is.setReversal();
        if(is.getReversedItems().size()==2){
            is.setReverse();
        }*/
    }
}
