/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sale.Service;

import Sale.Model.Sale;
import java.util.List;

/**
 *
 * @author User
 */
public interface SaleService {
    boolean addSale(int product, int employee, double amount, int store,int customer);
    Sale getRecentSale(int user);
    List<Sale> getSaleBySearch(String search);
    boolean setAmount(double amount,int id);
    boolean removeSale(int id);
    Sale getSale(int id);
}
