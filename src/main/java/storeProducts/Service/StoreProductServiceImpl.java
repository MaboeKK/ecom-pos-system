/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storeProducts.Service;

import Store.Model.StoreProduct;

import java.util.List;
import storeProducts.DAO.StoreProductDAO;

/**
 *
 * @author Tshiamo
 */
public class StoreProductServiceImpl implements StoreProductService {

    private StoreProductDAO productDAO;

    public StoreProductServiceImpl(StoreProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public List<StoreProduct> storesInventory() {
        return productDAO.storesInventory();
    }

    @Override
    public StoreProduct getIBTItem(int storeProdID) {
        return productDAO.getIBTItem(storeProdID);
    }

    @Override
    public void IBTSubtract(int id, int quantity) {
        productDAO.IBTSubtract(id, quantity);
    }

    @Override
    public List<StoreProduct> getProductForIBT(String name) {
        return productDAO.getProductForIBT(name);
    }

    @Override
    public List<StoreProduct> myInventory(int storeID) {
        return productDAO.myInventory(storeID);
    }

    @Override
    public List<StoreProduct> myInventoryShortage(int storeID) {
        return productDAO.myInventoryShortage(storeID);
    }
}
