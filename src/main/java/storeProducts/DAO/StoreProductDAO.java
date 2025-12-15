/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storeProducts.DAO;

import Store.Model.StoreProduct;
import java.util.List;

/**
 *
 * @author Tshiamo
 */
public interface StoreProductDAO {

    List<StoreProduct> storesInventory();
    StoreProduct getIBTItem(int store_productsID);

    void IBTSubtract(int prodID,int quantity);
    List<StoreProduct> getProductForIBT (String name);
    List<StoreProduct> myInventory (int storeID);
    List<StoreProduct> myInventoryShortage(int storeID);
}
