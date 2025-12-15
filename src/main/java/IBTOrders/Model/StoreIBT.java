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
public class StoreIBT {
    
    private String storesIBT;
    private String storeRequestingName;
    private String storeName;
    private String orderDate;
    private String requestID;
    private String productName;
    private String totalPrice;
    private String quantity;

    public StoreIBT(String storesIBT, String storeRequestingName, String storeName, String orderDate, String requestID, String productName, String totalPrice, String quantity) {
        this.storesIBT = storesIBT;
        this.storeRequestingName = storeRequestingName;
        this.storeName = storeName;
        this.orderDate = orderDate;
        this.requestID = requestID;
        this.productName = productName;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
    }
    
    

    public StoreIBT() {
    }

    public StoreIBT(String storeRequestingName, String storeName, String orderDate, String requestID, String productName, String totalPrice, String quantity) {
        this.storeRequestingName = storeRequestingName;
        this.storeName = storeName;
        this.orderDate = orderDate;
        this.requestID = requestID;
        this.productName = productName;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
    }

    
    public StoreIBT(String storesIBT, String storeRequestingName, String storeName, String orderDate) {
        this.storesIBT = storesIBT;
        this.storeRequestingName = storeRequestingName;
        this.storeName = storeName;
        this.orderDate = orderDate;
    }
    public StoreIBT(String storesIBT, String storeRequestingName, String storeName) {
        this.storesIBT = storesIBT;
        this.storeRequestingName = storeRequestingName;
        this.storeName = storeName;
    }

    public StoreIBT(String storeRequestingName, String storeName, String orderDate, String requestID, String productName) {
        this.storeRequestingName = storeRequestingName;
        this.storeName = storeName;
        this.orderDate = orderDate;
        this.requestID = requestID;
        this.productName = productName;
    }

    public StoreIBT(String storesIBT, String storeRequestingName, String storeName, String orderDate, String requestID, String productName) {
        this.storesIBT = storesIBT;
        this.storeRequestingName = storeRequestingName;
        this.storeName = storeName;
        this.orderDate = orderDate;
        this.requestID = requestID;
        this.productName = productName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    
    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    
    
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    
    
    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    
    

    public String getStoresIBT() {
        return storesIBT;
    }

    public void setStoresIBT(String storesIBT) {
        this.storesIBT = storesIBT;
    }

    public String getStoreRequestingName() {
        return storeRequestingName;
    }

    public void setStoreRequestingName(String storeRequestingName) {
        this.storeRequestingName = storeRequestingName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    
    
}
