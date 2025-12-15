/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sale.Service;

import Sale.DAO.SaleDAO;
import Sale.Model.Sale;
import java.util.List;

/**
 *
 * @author User
 */
public class SaleServiceImpl implements SaleService{
    
    private SaleDAO sd;
    
    public SaleServiceImpl(SaleDAO sd){
        this.sd = sd;
    }

    @Override
    public boolean addSale(int product, int employee, double amount, int store,int customer) {
        return sd.addSale(product, employee, amount, store, customer);
    }

    @Override
    public Sale getRecentSale(int user) {
        return sd.getRecentSale(user);
    }

    @Override
    public List<Sale> getSaleBySearch(String search) {
        if(search.contains("@") || search.contains(".com"))return sd.getSaleByEmail(search);
        return sd.getSaleByPhone(search);
    }

    @Override
    public boolean setAmount(double amount, int id) {
        return sd.setAmount(amount, id);
    }

    @Override
    public boolean removeSale(int id) {
        return sd.removeSale(id);
    }

    @Override
    public Sale getSale(int id) {
        return sd.getSale(id);
    }
    
}
