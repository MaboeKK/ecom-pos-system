/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IBTOrders.Model;

/**
 *
 * @author Tshiamo
 */
public class IBTRecived {
    
    private String storesIBTID;
    private String store_ProductID;
    private String storeName;
    private String storeID;
    private String productName;
    private String price;
    private String employeeID;

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "IBTRecived{" + "storesIBTID=" + storesIBTID + ", store_ProductID=" + store_ProductID + ", storeName=" + storeName + ", storeID=" + storeID + ", productName=" + productName + ", price=" + price + ", employeeID=" + employeeID + '}';
    }
    
    

    public IBTRecived(String storesIBTID, String store_ProductID, String storeName, String storeID, String productName, String price, String employeeID) {
        this.storesIBTID = storesIBTID;
        this.store_ProductID = store_ProductID;
        this.storeName = storeName;
        this.storeID = storeID;
        this.productName = productName;
        this.price = price;
        this.employeeID = employeeID;
    }

    public IBTRecived(String store_ProductID, String storeName, String storeID, String productName, String price, String employeeID) {
        this.store_ProductID = store_ProductID;
        this.storeName = storeName;
        this.storeID = storeID;
        this.productName = productName;
        this.price = price;
        this.employeeID = employeeID;
    }

    public IBTRecived(String storesIBTID, String store_ProductID, String storeName, String price, String employeeID) {
        this.storesIBTID = storesIBTID;
        this.store_ProductID = store_ProductID;
        this.storeName = storeName;
        this.price = price;
        this.employeeID = employeeID;
    }

    public IBTRecived(String store_ProductID, String storeName, String price, String employeeID) {
        this.store_ProductID = store_ProductID;
        this.storeName = storeName;
        this.price = price;
        this.employeeID = employeeID;
    }

    public IBTRecived() {
    }

    public String getStoresIBTID() {
        return storesIBTID;
    }

    public void setStoresIBTID(String storesIBTID) {
        this.storesIBTID = storesIBTID;
    }

    public String getStore_ProductID() {
        return store_ProductID;
    }

    public void setStore_ProductID(String store_ProductID) {
        this.store_ProductID = store_ProductID;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }
    
    
}
