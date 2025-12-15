/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Product.Controller;

import ItemCreation.Model.Product;
import Product.DAO.ProductDAOImp;
import Product.Service.ProductServices;
import Product.Service.ProductServicesImp;
import User.Model.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@WebServlet(name = "PlusServlet", urlPatterns = {"/PlusServlet"})
public class PlusServlet extends HttpServlet {
    
    ProductServices ps = new ProductServicesImp(new ProductDAOImp());

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
            out.println("<title>Servlet PlusServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PlusServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not logged in");
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
        int count = 0;
        double total = 0;
        Product product = null;

        while ((line = reader.readLine()) != null) {
            jsonBody.append(line);
        }
        Map<Integer,Integer> idcount = new HashMap<>();
        
        // Parse JSON object
        JSONObject jsonObject = new JSONObject(jsonBody.toString());
        String recievedPrododuct = jsonObject.getString("product");
        String recievedPrice = jsonObject.getString("price");
        
        
        product = ps.getStoreProductById(Integer.parseInt(recievedPrododuct),user.getStore());
        
        ids.add(Integer.parseInt(recievedPrododuct));
        prices.add(Double.parseDouble(recievedPrice));
        for(Integer i:ids){
            if(idcount.containsKey(i)){
                idcount.put(i, idcount.get(i)+1);
            }else{
                idcount.put(i, 1);
            }   
        }

        String state = product.getQuantity()-idcount.get(Integer.parseInt(recievedPrododuct))<0 ?"false":"true";
        if(state.equals("false")){
            ids.remove(Integer.valueOf(recievedPrododuct));
            prices.remove(Double.valueOf(recievedPrice));            
        }
        
        JSONObject jsonResponse = new JSONObject();
        
        for(Integer id:ids){
            count += (id==Integer.parseInt(recievedPrododuct))?1:0; 
        }
        
        for(Double price:prices){
            total += price;
        }
        
        session.setAttribute("ids",ids);
        session.setAttribute("prices",prices);
       
        jsonResponse.put("state", state);
        jsonResponse.put("id", recievedPrododuct); 
        jsonResponse.put("count", count);
        jsonResponse.put("price", total);
        jsonResponse.put("product", product.getName());
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
