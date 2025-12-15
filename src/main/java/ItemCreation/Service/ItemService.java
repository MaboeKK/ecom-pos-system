/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ItemCreation.Service;

import ItemCreation.Model.Product;

/**
 *
 * @author Tshiamo
 */
public interface ItemService {
    Product getItem(int subcat_id);
    void addItem(Product item);
}
