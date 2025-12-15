/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ItemCreation.Service;

import ItemCreation.DAO.ItemDAO;
import ItemCreation.Model.Product;

/**
 *
 * @author Tshiamo
 */
public class ItemServiceImp implements ItemService{
    private ItemDAO itemDAO;

    public ItemServiceImp(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }
    

    @Override
    public Product getItem(int subcat_id) {
        return itemDAO.getItem(subcat_id);
    }

    @Override
    public void addItem(Product item) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
