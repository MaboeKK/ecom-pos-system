/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IBTOrders.Service;

import IBTOrders.Model.IBTRecived;
import IBTOrders.Model.StoreIBT;
import java.util.List;

/**
 *
 * @author Tshiamo
 */
public interface IBTOrdersRecived {
    
    /*THIS IS WHERE THE STORE MAKING IBTS SERVICES BEGIN*/
    void AddIbtRecived(IBTRecived iBTRecived);
    List<IBTRecived> getIBTMade();
    IBTRecived getIBTItem(int index);
    
    /*THIS IS WHERE THE STORE GETTING IBTS SERVICES BEGIN*/
    void addOrderToStore(StoreIBT storesIBTs);
    List<StoreIBT> getIBTOrders();
    
}
