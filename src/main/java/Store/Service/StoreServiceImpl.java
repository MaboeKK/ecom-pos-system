package Store.Service;

import Store.DAO.StoreDAO;
import Store.Model.Store;

/**
 * Implementation of the StoreService interface.
 * This class delegates calls to the StoreDAO for CRUD operations related to stores.
 */
public class StoreServiceImpl implements StoreService {

    private final StoreDAO storeDAO;

    /**
     * Constructor to initialize the StoreDAO.
     *
     * @param storeDAO The StoreDAO instance to be used by this service.
     */
    public StoreServiceImpl(StoreDAO storeDAO) {
        this.storeDAO = storeDAO;
    }

    /**
     * Deletes a store by its ID.
     *
     * @param storeId The ID of the store to delete.
     * @return true if the store was successfully deleted, false otherwise.
     * @throws Exception If an error occurs during deletion.
     */
    @Override
    public boolean deleteStore(int storeId) throws Exception {
        // Delegate the deletion to the DAO
        return storeDAO.deleteStore(storeId);
    }

    /**
     * Creates a new store.
     *
     * @param store The store object to be created.
     */
    @Override
    public void createStore(Store store) {
        if (store != null) {
            // Delegate store creation to the DAO
            storeDAO.createStore(store);
        }
    }

    /**
     * Retrieves a store by its address.
     *
     * @param address The address of the store to retrieve.
     * @return The store object if found, or null otherwise.
     */
    @Override
    public Store getStore(String address) {
        // Delegate to DAO for fetching a store by address
        return storeDAO.getStore(address);
    }

    /**
     * Creates a managed store.
     *
     * @param store The store object to be created.
     */
    @Override
    public void createManagedStore(Store store) {
        if (store != null) {
            // Delegate creation of a managed store to DAO
            storeDAO.createManagedStore(store);
        }
    }

    /**
     * Retrieves a store by its manager's name.
     *
     * @param manager The manager's name to search for.
     * @return The store object if found, or null otherwise.
     */
    @Override
    public Store getStoreByManager(String manager) {
        // Delegate to DAO for fetching store by manager
        return storeDAO.getStoreByManager(manager);
    }
}
