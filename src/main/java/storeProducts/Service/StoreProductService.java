/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storeProducts.Service;

import Store.Model.StoreProduct;
import java.util.List;

/**
 *
 * @author Tshiamo
 */
public interface StoreProductService {

    List<StoreProduct> storesInventory();
    StoreProduct getIBTItem(int storeProdID);

    void IBTSubtract(int store_prodID, int quantity);
    List<StoreProduct> getProductForIBT (String name);
    List<StoreProduct> myInventory (int storeID);
    List<StoreProduct> myInventoryShortage(int storeID);

}
