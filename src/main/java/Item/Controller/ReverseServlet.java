/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Item.Controller;

import Item.DAO.ItemDAOImpl;
import Item.Service.ItemService;
import Item.Service.ItemServiceImpl;
import ItemCreation.Model.Product;
import Order.DAO.OrderDAOImpl;
import Order.Service.OrderService;
import Order.Service.OrderServiceImpl;
import Product.DAO.ProductDAOImp;
import Product.Service.ProductServices;
import Product.Service.ProductServicesImp;
import Sale.DAO.SaleDAOImpl;
import Sale.Service.SaleService;
import Sale.Service.SaleServiceImpl;
import User.Model.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

/**
 *
 * @author Tsheno
 */
@WebServlet(name = "ReverseServlet", urlPatterns = {"/ReverseServlet"})
public class ReverseServlet extends HttpServlet {

    private SaleService ss = new SaleServiceImpl(new SaleDAOImpl());
    private OrderService os = new OrderServiceImpl(new OrderDAOImpl());
    private ItemService is = new ItemServiceImpl(new ItemDAOImpl());
    private ProductServices ps = new ProductServicesImp(new ProductDAOImp());
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ReverseServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReverseServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Session expired");
            return;
        }
        List<Integer> ids = (List<Integer>) session.getAttribute("ids");
        List<Double> prices = (List<Double>) session.getAttribute("prices");
        if (ids == null || prices == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Cart not initialized");
            return;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
                StringBuilder jsonBody = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    jsonBody.append(line);
                }
                       
        // Parse JSON object
        JSONObject jsonObject = new JSONObject(jsonBody.toString());
        String itemrecieved = jsonObject.getString("item");
        String productrecieved = jsonObject.getString("product");
        String pricerecieved = jsonObject.getString("price");
        String name = jsonObject.getString("name");
        double total = 0;
        String remove = "false";
        
        if(ids.contains(Integer.valueOf(itemrecieved))){
            ids.remove(Integer.valueOf(itemrecieved));
            prices.remove(Double.valueOf(pricerecieved));
            remove = "true";
        }else {
            ids.add(Integer.parseInt(itemrecieved));
            prices.add(Double.parseDouble(pricerecieved));
        }
        
        for(Double price:prices){
            total+=price;
        }
        
        JSONObject jsonResponse = new JSONObject();
        
        jsonResponse.put("price", total);
        jsonResponse.put("name", name);
        jsonResponse.put("priceresponse", pricerecieved);
        jsonResponse.put("product", productrecieved);
        jsonResponse.put("id", itemrecieved);
        jsonResponse.put("remove", remove);
        jsonResponse.put("count", ids.size());
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse.toString()); 
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
