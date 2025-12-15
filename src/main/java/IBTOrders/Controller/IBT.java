/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IBTOrders.Controller;

import IBTOrders.DAO.IBTOrdersRecivedDAOImp;
import IBTOrders.Model.IBTRecived;
import IBTOrders.Model.StoreIBT;
import IBTOrders.Service.IBTOrderesRecivedServicesImp;
import IBTOrders.Service.IBTOrdersRecived;
import Store.DAO.StoreDAOImpl;

import Store.Model.Store;
import Store.Model.StoreProduct;
import Store.Service.StoreServiceImpl;

import User.Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import storeProducts.DAO.StoreProductDAOImpl;
import storeProducts.Service.StoreProductService;
import storeProducts.Service.StoreProductServiceImpl;

/**
 *
 * @author Tshiamo
 */
@WebServlet(name = "IBT", urlPatterns = {"/IBT"})
public class IBT extends HttpServlet {

    private static final Logger logger = Logger.getLogger(IBT.class.getName());
    private StoreProductService productServices; 
    private IBTOrdersRecived iBTOrdersRecived;
    private Store storesService;
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        productServices = new StoreProductServiceImpl(new StoreProductDAOImpl());
        iBTOrdersRecived = new IBTOrderesRecivedServicesImp(new IBTOrdersRecivedDAOImp());
        
        List<IBTRecived> myIBTs = new ArrayList<>();
        List<StoreIBT> orders = new ArrayList<>();
        
        HttpSession session = request.getSession();
        //GET THE EMPLOYEES INFO
        User emp = new User();
        emp = (User)session.getAttribute("user");
        
        logger.log(Level.FINE, "Submit parameter: {0}", request.getParameter("submit"));

         switch(request.getParameter("submit"))
        {
            case"View Stores IBT(s)":
                logger.log(Level.FINE, "VIEW IBTS");
                String address = (String) session.getAttribute("address");
                request.setAttribute("address", address);
                    List<StoreProduct> st = new ArrayList<>();
                   
                myIBTs = iBTOrdersRecived.getIBTMade();
                st = productServices.storesInventory();
                orders = iBTOrdersRecived.getIBTOrders();
                
                session.setAttribute("st", st);
                session.setAttribute("myIBTs", myIBTs);
                session.setAttribute("orders", orders);
                
                request.getRequestDispatcher("viewIBT.jsp").forward(request, response);
                break;
                
            case"Make IBT(s)":
                logger.log(Level.FINE, "MAKE IBT");
              
                   request.getRequestDispatcher("IBTFilter.jsp").forward(request, response);
             
                
                
                break;
                
            case"next":
               {
                   String colour = request.getParameter("Colour");
                   String Size = request.getParameter("Size");
                   List<StoreProduct> sp = new ArrayList<>();

                myIBTs = iBTOrdersRecived.getIBTMade();
                sp = productServices.storesInventory();
                session.setAttribute("sp", sp);
                request.setAttribute("sp", sp);
                session.setAttribute("colour", colour);
                request.setAttribute("colour", colour);
                session.setAttribute("Size", Size);
                request.setAttribute("Size", Size);
                session.setAttribute("myIBTs", myIBTs);
                request.setAttribute("myIBTs", myIBTs);
                request.getRequestDispatcher("makeIBT.jsp").forward(request, response);
               }
                
               
                break;
                
            case"order":
                String itemSelected = request.getParameter("itemSelect");
                int IBTItem = Integer.parseInt(itemSelected);
                StoreProduct ibtProduct = productServices.getIBTItem(IBTItem);
                
                session.setAttribute("IBTItem", IBTItem);
                session.setAttribute("ibtProduct", ibtProduct);
                request.setAttribute("ibtProduct", ibtProduct);
                
                request.getRequestDispatcher("IBTRecipt.jsp").forward(request, response);
                break;
                
            case"checkout":
                int itemID =(int) session.getAttribute("IBTItem");
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                productServices.IBTSubtract(itemID, quantity);

                String empID = emp.getEmployeeID();
                StoreProduct ibtItem = new StoreProduct();
                //GET product using session
                ibtItem = (StoreProduct) session.getAttribute("ibtProduct");
                String storeID = ibtItem.getStore_ID();
                String storeName = ibtItem.getStoreName();
                String productID = ibtItem.getProduct_ID();
                String prodName = ibtItem.getProductName();
                
                String itemQuantity = String.valueOf(quantity);
                
                
                String storProdID = ibtItem.getStore_productID();
                double totalprice = Double.parseDouble(ibtItem.getPrice()) * quantity;
                session.setAttribute("totalprice", totalprice);
                String price = String.valueOf(totalprice);
                IBTRecived ibtRecived = new IBTRecived(storeName, ibtItem.getStoreName(),ibtItem.getStore_ID(), storeName, price,empID);
                
                iBTOrdersRecived.AddIbtRecived(ibtRecived);
                
                //GET THE Stores Info (that sent REQUEST)
                
                Store storethatRecivedIBT = new Store();
                //storethatRecivedIBT = storesService.getStore(storeID);
                String storeAddy = storethatRecivedIBT.getSTORE_LOCATION_ADDRESS();
                //Get current Store Addy
                int currentStore = emp.getStore();
                //GET the IBT RECIVED ID
                List<IBTRecived> myList = new ArrayList<>();
                myList =(List<IBTRecived>) session.getAttribute("myIBTs");
                //GET DATE
                Date today = new Date();
                String IBTDate = String.valueOf(today.getTime());
                //Prod Name Using sessions
                
                //
                int itemNum = myList.size();
                IBTRecived item = iBTOrdersRecived.getIBTItem(itemNum);
                String IBTID = item.getStoresIBTID();
                //SEND THE INFO TO THE CORRECT STORE
                StoreIBT stIBT = new StoreIBT( storeAddy, IBTDate, IBTID, prodName,price,itemQuantity);
                //ADD store to STORES DATA
                iBTOrdersRecived.addOrderToStore(stIBT);
                request.getRequestDispatcher("Confirmation.jsp").forward(request, response);
                break;
                
            case"confirm":
                
                myIBTs = iBTOrdersRecived.getIBTMade();
                session.setAttribute("myIBTs", myIBTs);
                request.getRequestDispatcher("makeIBT.jsp").forward(request, response);
                break;
        }
        
        
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
