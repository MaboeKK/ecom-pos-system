/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IBTOrders.Service;

import IBTOrders.DAO.IBTOrdersRecivedDAO;
import IBTOrders.Model.IBTRecived;
import IBTOrders.Model.StoreIBT;
import java.util.List;

/**
 *
 * @author Tshiamo
 */
public class IBTOrderesRecivedServicesImp implements IBTOrdersRecived{

    public IBTOrderesRecivedServicesImp(IBTOrdersRecivedDAO bTOrdersRecivedDAO) {
        this.bTOrdersRecivedDAO = bTOrdersRecivedDAO;
    }
    
    private IBTOrdersRecivedDAO bTOrdersRecivedDAO;

    @Override
    public void AddIbtRecived(IBTRecived iBTRecived) {
        bTOrdersRecivedDAO.addIBTRecived(iBTRecived);
    }

    @Override
    public List<IBTRecived> getIBTMade() {
        return bTOrdersRecivedDAO.getIBTMadeList();
    }

    @Override
    public IBTRecived getIBTItem(int i) {
        return bTOrdersRecivedDAO.getIBTItem(i);
    }

    @Override
    public void addOrderToStore(StoreIBT ibt) {
        bTOrdersRecivedDAO.addOrderToStore(ibt);
    }

    @Override
    public List<StoreIBT> getIBTOrders() {
        return bTOrdersRecivedDAO.getIBTOrders();//to be continued
    }

    
    
}
