/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ItemCreation.Controller;

import ItemCreation.DAO.ItemDAOImp;
import ItemCreation.Model.Product;
import ItemCreation.Service.ItemService;
import ItemCreation.Service.ItemServiceImp;
import Product.DAO.ProductDAOImp;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Product.DAO.ProductDAO;
import Product.Service.ProductServices;
import Product.Service.ProductServicesImp;

/**
 *
 * @author Tshiamo
 */
@WebServlet(name = "Recivestock", urlPatterns = {"/Recivestock"})
public class Recivestock extends HttpServlet {
    private ItemService itemService;
    private ProductServices productService;

   
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
        HttpSession session = request.getSession();
        itemService = new ItemServiceImp(new ItemDAOImp());
        productService = new ProductServicesImp(new ProductDAOImp());
        
        Product item = new Product();
        Product product = new Product();
        switch(request.getParameter("submit"))
        {
            case "next":
                request.getRequestDispatcher("CustomCode.jsp").forward(request, response);
                break;

            case"addItem":
                //STILL NEEDS TO BE IMPLEMENTED IN THE JSP
                break;
            case "create":
                //CREATE THE ITEM ON THE DB AFTER PROMTING USER FOR INFO
                
                request.getRequestDispatcher("accept.jsp").forward(request, response); 
                break; 
            case "done":
                
                
                request.getRequestDispatcher("cashierWel.jsp").forward(request, response); 
                break;    
                default:
                   String msg = "Please make sure that all the fields have been insereted ins";
                    request.setAttribute("msg", msg);
                    request.getRequestDispatcher("CustomCode.jsp").forward(request, response); 
                
        }
        ///Get product values from db
       
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public List<Product> subProd(int subcat_id)
    {
        Product product;
        int myCount = 0;
        int prod = 0;
        int Nsubcat_id = subcat_id * 2;       
        while(myCount >= 2)
        {
            prod++;
            myCount++;
            
            
           
        }
               
        
        return null;
    }
}
