/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Product.Service;

import ItemCreation.Model.Product;
import java.util.List;

/**
 *
 * @author Tshiamo
 */
public interface ProductServices {
    void createProduct(Product product);
    Product getProduct(int type_ID);
    List<Product> itemList(int type_ID);
    List<Product> itemShortage(int quantity);
    Product getProduct(String barcode);
    boolean setStock(int stock,int id);
    Product getProductById(int id);
    List<Product> getProducts();
    void orderItem(int quantity);
    Product Ordered_UpdateQuantity(int productID,int qantityOrdered);
    void updateProduct(Product p);
    List<Product> getStoreProducts(int store);
    Product getStoreProductById(int id,int store);
    boolean setStoreQuantity(int quantity,int id,int store);
    Product getProductByBarcode(int store,String barcode);
    List<Product> getProductByAmount(int store,double amount);
    Product getStoreProductById(int id);
    boolean setStoreQuantity(int stock,int id);
}
