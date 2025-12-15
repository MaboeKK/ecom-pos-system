package Product.Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ItemCreation.Model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Product.DAO.ProductDAO;
import Product.DAO.ProductDAOImp;
import Product.Service.ProductServices;
import Product.Service.ProductServicesImp;
import Store.Model.StoreProduct;
import User.Model.User;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import storeProducts.DAO.StoreProductDAOImpl;
import storeProducts.Service.StoreProductService;
import storeProducts.Service.StoreProductServiceImpl;

/**
 *
 * @author Tshiamo
 */
@WebServlet(urlPatterns = {"/ProductServlet"})
public class ShortageServlet extends HttpServlet {

    private ProductServices productService;
    private StoreProductService storeProductsServices;
    
    
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
        productService = new ProductServicesImp(new ProductDAOImp());
        storeProductsServices = new StoreProductServiceImpl(new StoreProductDAOImpl());
        
        HttpSession session = request.getSession();
        //Get the usere that is logged in 
        User ur = new User();
        ur =(User) session.getAttribute("user");
        ///Got User
        //Get store ID
        
        int storeID = ur.getStore();
        //
        Product item = new Product();
        List<Product> myList = new ArrayList<>();
        List<StoreProduct> myProductList = new ArrayList<>();
        List<StoreProduct> myProductListShortage = new ArrayList<>();

        if(request.getParameter("submit") != null )
        {
            //Get products that are 3> form warehouse
            int shortageMark = 5;
            myList = productService.itemShortage(shortageMark);
            ///Get products that are 3> form individula store
            
            myProductList = storeProductsServices.myInventoryShortage(storeID);
            
            myProductListShortage = storeProductsServices.myInventoryShortage(storeID);
            //SESSIONS
            session.setAttribute("myList", myList);
            session.setAttribute("myProductList", myProductList);
            session.setAttribute("myProductListShortage", myProductListShortage);
            
            request.getRequestDispatcher("EXP.jsp").forward(request, response);
        }
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
