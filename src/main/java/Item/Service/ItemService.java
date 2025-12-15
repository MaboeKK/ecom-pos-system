/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Item.Service;

import Item.Model.Item;
import Order.Model.Order;
import java.util.List;

/**
 *
 * @author User
 */
public interface ItemService {
    boolean addItem(Item item);
    List<Integer> getProductId(Order order);
    boolean addItemOfSale(Item item);
    boolean setSale(int order,int sale);
    List<Item> getItemsBySale(int sale);
    boolean setReversal();
    List<Item>getReversedItems();
    boolean setReverse();
    boolean removeItem(int id);
    Item getItemById(int id);
    List<Item> getItemsByAmount(double price,int store);
    List<Item> getItemsByOrder(int order);
    Item getRecentItem();
    
}
