/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Product.Service;

import ItemCreation.Model.Product;
import Product.DAO.ProductDAO;
import java.util.List;

/**
 *
 * @author Tshiamo
 */
public class ProductServicesImp implements ProductServices{
    
    private ProductDAO productDAO;

    public ProductServicesImp(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }
    

    @Override
    public void createProduct(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product getProduct(int type_ID) {
        return productDAO.getProduct(type_ID);
    }

    @Override
    public List<Product> itemList(int type_ID) {
       return (List<Product>) productDAO.getProduct(type_ID);
    }

    @Override
    public List<Product> itemShortage(int quantity) {
        return (List<Product>) productDAO.getShortage(quantity);
    }
    

    @Override
    public Product getProduct(String barcode) {
        return productDAO.getProduct(barcode);
    }

    @Override
    public boolean setStock(int stock,int id) {
        return productDAO.setQuantity(stock, id);
    }

    @Override
    public Product getProductById(int id) {
        return productDAO.getProductById(id);
    }    

    @Override
    public void orderItem(int i) {
        productDAO.orderItem(i);
    }

    @Override
    public Product Ordered_UpdateQuantity(int prod, int quan) {
        return productDAO.UpdateItem(prod, quan);
    }

    @Override
    public void updateProduct(Product prdct) {
        productDAO.updateProduct(prdct);
    }

    @Override
    public List<Product> getProducts() {
        return productDAO.getProducts();
    }

    @Override
    public List<Product> getStoreProducts(int store) {
        return productDAO.getStoreProducts(store);
    }

    @Override
    public Product getStoreProductById(int id, int store) {
        return productDAO.getStoreProductById(id, store);
    }

    @Override
    public boolean setStoreQuantity(int i, int i1, int i2) {
        return productDAO.setStoreQuantity(i, i1, i2);
    }

    @Override
    public Product getProductByBarcode(int i, String string) {
        return productDAO.getProductByBarcode(i, string);
    }    

    @Override
    public List<Product> getProductByAmount(int i, double d) {
        return productDAO.getProductByAmount(i, d);
    }

    @Override
    public Product getStoreProductById(int i) {
        return productDAO.getStoreProductById(i);
    }

    @Override
    public boolean setStoreQuantity(int stock, int id) {
        return productDAO.setStoreQuantity(stock, id);
    }


    
}
