/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Item.DAO;

import Item.Model.Item;
import java.util.List;

/**
 *
 * @author User
 */
public interface ItemDAO {
    boolean addItem(int orderid,int productid,int quantity,double price);
    boolean addItem(int orderid,int productid,int quantity,double price,int sale);
    List<Integer> getProductId(int order);
    boolean setSale(int order,int sale);
    List<Item> getItemBySale(int sale);
    boolean setReverse();
    List<Item> getReversedItem();
    boolean setReversal();
    boolean removeItem(int id);
    Item getItemById(int id);
    List<Item> getItemsByAmount(double amount,int store);
    List<Item> getItemsByOrder(int order);
    Item getRecentItem();
}
