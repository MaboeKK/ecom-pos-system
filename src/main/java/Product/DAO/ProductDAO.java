/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Product.DAO;

import ItemCreation.Model.Product;
import java.util.List;

/**
 *
 * @author Tshiamo
 */
public interface ProductDAO {
    void createProduct(Product product);
    Product getProduct(int type_ID);
    List<Product> getProducts(int type_ID);
    List<Product> getShortage(int quantity);
    Product getProduct(String name);
    boolean setQuantity(int stock,int id);
    Product getProductById(int id);
    void orderItem(int quantity);
    List<Product> getProducts();
    void updateProduct(Product p);
    Product UpdateItem(int productID,int quantity);
    List<Product> getStoreProducts(int store);
    Product getStoreProductById(int id,int store);
    boolean setStoreQuantity(int stock,int id,int store);
    Product getProductByBarcode(int store,String barcode);   
    List<Product> getProductByAmount(int store,double amount);
    //new 
    Product getStoreProductById(int id);
    boolean setStoreQuantity(int stock,int id);
    
}
