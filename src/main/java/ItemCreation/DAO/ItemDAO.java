/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ItemCreation.DAO;

import ItemCreation.Model.Product;

/**
 *
 * @author Tshiamo
 */
public interface ItemDAO {
    Product getItem(int subcat_id);
    void addItem(Product subcat_id);
}
