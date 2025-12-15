package Store.Service;
import Store.Model.Store;

public interface StoreService {
    void createStore(Store store);
    void createManagedStore(Store store);
    Store getStore(String address);
    Store getStoreByManager(String manager);
    boolean deleteStore(int storeId) throws Exception;
}
