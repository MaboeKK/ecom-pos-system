/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Item.Service;

import Item.DAO.ItemDAO;
import Item.Model.Item;
import Order.Model.Order;
import java.util.List;

/**
 *
 * @author User
 */
public class ItemServiceImpl implements ItemService{
    
    private ItemDAO itemDao;
    
    public ItemServiceImpl(ItemDAO itemDao){
        this.itemDao = itemDao;
    }

    @Override
    public boolean addItem(Item item) {
        return itemDao.addItem(item.getOrderid(), item.getProduct(), item.getQuantity(), item.getPrice());
    }

    @Override
    public List<Integer> getProductId(Order order) {
       return itemDao.getProductId(order.getId());
    }

    @Override
    public boolean addItemOfSale(Item item) {
        return itemDao.addItem(item.getOrderid(), item.getProduct(), item.getQuantity(), item.getPrice(),item.getSale());
    }

    @Override
    public boolean setSale(int order, int sale) {
        return itemDao.setSale(order, sale);
    }

    @Override
    public List<Item> getItemsBySale(int sale) {
        return itemDao.getItemBySale(sale);
    }

    @Override
    public boolean setReversal() {
        return itemDao.setReverse();
    }

    @Override
    public List<Item> getReversedItems() {
        return itemDao.getReversedItem();
    }

    @Override
    public boolean setReverse() {
        return itemDao.setReverse();
    }

    @Override
    public boolean removeItem(int id) {
        return itemDao.removeItem(id);
    }

    @Override
    public Item getItemById(int id) {
        return itemDao.getItemById(id);
    }

    @Override
    public List<Item> getItemsByAmount(double d,int store) {
        return itemDao.getItemsByAmount(d,store);
    }

    @Override
    public List<Item> getItemsByOrder(int order) {
        return itemDao.getItemsByOrder(order);
    }

    @Override
    public Item getRecentItem() {
        return itemDao.getRecentItem();
    }
    
}
