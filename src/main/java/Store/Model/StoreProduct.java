/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Store.Model;

import java.util.Date;

/**
 *
 * @author Tshiamo
 */
public class StoreProduct {
    
    private String store_productID;
    private String product_ID;
    private String store_ID;
    private String quantity;
    private String barcode;
    private String ItemRecivedDate;
    private String lastUpdate;
    private String size;
    private String colour;
    private String storeName;
    private String productName;
    private String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "StoreProduct{" + "store_productID=" + store_productID + ", product_ID=" + product_ID + ", store_ID=" + store_ID + ", quantity=" + quantity + ", barcode=" + barcode + ", ItemRecivedDate=" + ItemRecivedDate + ", lastUpdate=" + lastUpdate + ", size=" + size + ", colour=" + colour + ", storeName=" + storeName + ", productName=" + productName + ", price=" + price + '}';
    }

   

    public StoreProduct(String product_ID, String store_ID, String quantity, String barcode, String ItemRecivedDate, String lastUpdate, String size, String colour, String storeName, String productName, String price) {
        this.product_ID = product_ID;
        this.store_ID = store_ID;
        this.quantity = quantity;
        this.barcode = barcode;
        this.ItemRecivedDate = ItemRecivedDate;
        this.lastUpdate = lastUpdate;
        this.size = size;
        this.colour = colour;
        this.storeName = storeName;
        this.productName = productName;
        this.price = price;
    }

    
    
    public StoreProduct(String product_ID, String store_ID, String quantity, String barcode, String ItemRecivedDate, String lastUpdate, String size, String colour, String storeName, String productName) {
        this.product_ID = product_ID;
        this.store_ID = store_ID;
        this.quantity = quantity;
        this.barcode = barcode;
        this.ItemRecivedDate = ItemRecivedDate;
        this.lastUpdate = lastUpdate;
        this.size = size;
        this.colour = colour;
        this.storeName = storeName;
        this.productName = productName;
    }

    
    public StoreProduct() {
    }

    public StoreProduct(String product_ID, String store_ID, String quantity, String barcode, String ItemRecivedDate, String lastUpdate, String size, String colour) {
        this.product_ID = product_ID;
        this.store_ID = store_ID;
        this.quantity = quantity;
        this.barcode = barcode;
        this.ItemRecivedDate = ItemRecivedDate;
        this.lastUpdate = lastUpdate;
        this.size = size;
        this.colour = colour;
    }

    
    public StoreProduct(String store_productID, String product_ID, String store_ID, String quantity, String barcode, String ItemRecivedDate, String lastUpdate) {
        this.store_productID = store_productID;
        this.product_ID = product_ID;
        this.store_ID = store_ID;
        this.quantity = quantity;
        this.barcode = barcode;
        this.ItemRecivedDate = ItemRecivedDate;
        this.lastUpdate = lastUpdate;
    }
        public StoreProduct( String product_ID, String store_ID, String quantity, String barcode, String ItemRecivedDate, String lastUpdate) {
        
        this.product_ID = product_ID;
        this.store_ID = store_ID;
        this.quantity = quantity;
        this.barcode = barcode;
        this.ItemRecivedDate = ItemRecivedDate;
        this.lastUpdate = lastUpdate;
    }
    
    public StoreProduct(String store_productID, String store_ID, String quantity) {
        this.store_productID = store_productID;
        this.store_ID = store_ID;
        this.quantity = quantity;
    }

    public StoreProduct(String product_ID, String store_ID, String quantity, String barcode, String lastUpdate) {
        
        this.product_ID = product_ID;
        this.store_ID = store_ID;
        this.quantity = quantity;
        this.barcode = barcode;
        this.lastUpdate = lastUpdate;
    }

    public String getStore_productID() {
        return store_productID;
    }

    public void setStore_productID(String store_productID) {
        this.store_productID = store_productID;
    }

    public String getProduct_ID() {
        return product_ID;
    }

    public void setProduct_ID(String product_ID) {
        this.product_ID = product_ID;
    }

    public String getStore_ID() {
        return store_ID;
    }

    public void setStore_ID(String store_ID) {
        this.store_ID = store_ID;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getItemRecivedDate() {
        return ItemRecivedDate;
    }

    public void setItemRecivedDate(String ItemRecivedDate) {
        this.ItemRecivedDate = ItemRecivedDate;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }


        public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    
}
