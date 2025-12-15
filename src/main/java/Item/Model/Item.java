/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Item.Model;

/**
 *
 * @author User
 */
public class Item {
    private int itemid;
    private int orderid;
    private int product;
    private int quantity;
    private double price;
    private int sale;
    private String reverse;
    private String name;

    /**
     * @return the itemid
     */
    public int getItemid() {
        return itemid;
    }

    /**
     * @param itemid the itemid to set
     */
    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    /**
     * @return the orderid
     */
    public int getOrderid() {
        return orderid;
    }

    @Override
    public String toString() {
        return "Item{" + "itemid=" + itemid + ", orderid=" + orderid + ", product=" + product + ", quantity=" + quantity + ", price=" + price + '}';
    }

    /**
     * @param orderid the orderid to set
     */
    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the product
     */
    public int getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(int product) {
        this.product = product;
    }

    /**
     * @return the sale
     */
    public int getSale() {
        return sale;
    }

    /**
     * @param sale the sale to set
     */
    public void setSale(int sale) {
        this.sale = sale;
    }

    /**
     * @return the reversal
     */
    public String getReverse() {
        return reverse;
    }

    /**
     * @param reversal the reversal to set
     */
    public void setReverse(String reverse) {
        this.reverse = reverse;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
}
