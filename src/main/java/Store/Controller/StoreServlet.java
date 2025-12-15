/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Store.Controller;

import Store.DAO.StoreDAOImpl;
import Store.Model.Store;
import Store.Service.StoreService;
import Store.Service.StoreServiceImpl;
import User.DAO.UserDAOImpl;
import User.Model.User;
import User.Service.UserServiceImpl;
import User.Service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tshiamo
 */
@WebServlet(name = "StoreServlet", urlPatterns = {"/StoreServlet"})
public class StoreServlet extends HttpServlet {
    
    private StoreService ss = new StoreServiceImpl(new StoreDAOImpl());
    private UserService us = new UserServiceImpl(new UserDAOImpl());
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String province = request.getParameter("STORE_LOCATION_PROVINCE");
            String city = request.getParameter("STORE_LOCATION_CITY");
            String address = request.getParameter("STORE_LOCATION_ADDRESS");
            String manager = request.getParameter("MANAGER_ID");
            Store store = null;
            if(manager != null && !manager.isEmpty()){
                User user = us.getUserByEmployeeId(manager);
                
                store = new Store(province,city,address,user.getEmployeeID());
                ss.createManagedStore(store);
                int id = ss.getStoreByManager(user.getEmployeeID()).getStore_id();
                us.setUserStore(id+"",user.getEmployeeID());
            }
            else{

                store = new Store(province,city,address);
                ss.createStore(store);
            }
            request.setAttribute("msg", "Store created");
            request.getRequestDispatcher("CreateStore.jsp").forward(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
