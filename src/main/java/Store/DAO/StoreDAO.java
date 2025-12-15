/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Store.DAO;

import Store.Model.Store;
import java.util.List;

public interface StoreDAO {
    Store getStore(String address);
    void updateUser(Store store);
    boolean deleteStore(int storeId);
    List<Store> storeList();
    void createStore(Store user);
    void createManagedStore(Store store);
    Store getStoreByManager(String manager);
    Store getStoreById(int storeId);
    
}
