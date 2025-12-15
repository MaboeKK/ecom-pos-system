/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ItemCreation.Model;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Tshiamo
 */
public class Product {


    private int id;
    private String batchNumber;
    private String category;
    private int subCategory;
    private String name;
    private String description;
    private String barcode;
    private String gender;
    private double price;
    private int quantity;
    private int type;
    private Date create;
    private Date update;
    private String colour;
    private String size;

    public Product(String category, String name, String description, String barcode, String gender, double price, int quantity, int type, Date create, Date update, String colour, String size) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.barcode = barcode;
        this.gender = gender;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        this.create = create;
        this.update = update;
        this.colour = colour;
        this.size = size;
    }

    
    
    public Product(int id, String batchNumber, String category, int subCategory, String name, String description, String barcode, String gender, double price, int quantity, int type, Date create, Date update, String colour, String size) {
        this.id = id;
        this.batchNumber = batchNumber;
        this.category = category;
        this.subCategory = subCategory;
        this.name = name;
        this.description = description;
        this.barcode = barcode;
        this.gender = gender;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        this.create = create;
        this.update = update;
        this.colour = colour;
        this.size = size;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Product(int id, String name, String description, String barcode, String gender, double price, int quantity, int type, Date create, Date update) {
        this.id = id;
       
        this.name = name;
        this.description = description;
        this.barcode = barcode;
        this.gender = gender;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        this.create = create;
        this.update = update;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", batchNumber=" + batchNumber + ", category=" + category + ", subCategory=" + subCategory + ", name=" + name + ", description=" + description + ", barcode=" + barcode + ", gender=" + gender + ", price=" + price + ", quantity=" + quantity + ", type=" + type + ", create=" + create + ", update=" + update + '}';
    }

    
    

    public Product(int id, int subCategory, String name, String description, String barcode, String gender, double price, int quantity, int type, Date create, Date update) {
        this.id = id;
        this.subCategory = subCategory;
        this.name = name;
        this.description = description;
        this.barcode = barcode;
        this.gender = gender;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        this.create = create;
        this.update = update;
    }
    
    public int getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(int subCategory) {
        this.subCategory = subCategory;
    }
    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the id
     */
    public Product(int id, String batchNumber, String category, int subCategory, String name, String description, String barcode, String gender, double price, int quantity, int type, Date create, Date update) {
        this.id = id;
        this.batchNumber = batchNumber;
        this.category = category;
        this.subCategory = subCategory;
        this.name = name;
        this.description = description;
        this.barcode = barcode;
        this.gender = gender;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        this.create = create;
        this.update = update;
    }

    public Product(int id, int subCategory, String name, String description, Date create, Date update) {
       this(id,null,null,subCategory,name,description,null,null,0,0,0,create,update);
    }

    public Product(String name, String description, Date create, Date update) {
        this.name = name;
        this.description = description;
        this.create = create;
        this.update = update;
    }

    public Product(int subCategory, String name, String description, Date create) {
        this.subCategory = subCategory;
        this.name = name;
        this.description = description;
        this.create = create;
    }

    public Product(int subCategory, String name, String description, int type, Date create, Date update) {
        this.subCategory = subCategory;
        this.name = name;
        this.description = description;
        this.type = type;
        this.create = create;
        this.update = update;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the create
     */
    public Date getCreate() {
        return create;
    }

    /**
     * @param create the create to set
     */
    public void setCreate(Date create) {
        this.create = create;
    }

    /**
     * @return the update
     */
    public Date getUpdate() {
        return update;
    }

    /**
     * @param update the update to set
     */
    public void setUpdate(Date update) {
        this.update = update;
    }


}
