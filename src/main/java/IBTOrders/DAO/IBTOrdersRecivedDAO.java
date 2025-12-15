/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IBTOrders.DAO;

import IBTOrders.Model.IBTRecived;
import IBTOrders.Model.StoreIBT;
import java.util.List;

/**
 *
 * @author Tshiamo
 */
public interface IBTOrdersRecivedDAO {
    
    void addIBTRecived(IBTRecived iBTRecived);
    List<IBTRecived> getIBTMadeList();
    IBTRecived getIBTItem(int index);
    
    void addOrderToStore(StoreIBT storesIBTs);
    List<StoreIBT> getIBTOrders();
    
}
