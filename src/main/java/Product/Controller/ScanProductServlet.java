/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Product.Controller;

/**
 *
 * @author User
 */
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
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "ScanProductServlet", urlPatterns = {"/ScanProductServlet"})
public class ScanProductServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ScanProductServlet.class.getName());
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
            out.println("<title>Servlet ScanProductServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ScanProductServlet at " + request.getContextPath() + "</h1>");
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
        try{
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder jsonBody = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            jsonBody.append(line);
        }
        
        // Parse JSON object
        JSONObject jsonObject = new JSONObject(jsonBody.toString());
        String message = jsonObject.getString("product");
                    
        
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        Map<Integer,Integer> idcount = new HashMap<>();
        Product item = ps.getProductByBarcode(user.getStore(),message);
            
        List<Integer> ids = (List<Integer>)session.getAttribute("ids");
        ids.add(item.getId());
        List<Double> prices = (List<Double>)session.getAttribute("prices");
        prices.add(item.getPrice());
        for(Integer i:ids){
    
            if(idcount.containsKey(i)){
                idcount.put(i, idcount.get(i)+1);
            }else{
                idcount.put(i, 1);
            }   
        } 
         
        String state = item.getQuantity()-idcount.get(item.getId())<0 ?"false":"true";
        if(state.equals("false")){
            ids.remove(Integer.valueOf(item.getId()));
            prices.remove(item.getPrice());            
        }
        
        session.setAttribute("ids", ids);
        session.setAttribute("prices", prices);
        
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("id", item.getId());
        jsonResponse.put("name", item.getName());
        jsonResponse.put("description", item.getDescription());
        jsonResponse.put("price", item.getPrice());
        jsonResponse.put("state", state);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse.toString());                
            
        }catch(Exception e){
            logger.log(Level.SEVERE, "Error scanning product", e);
        }
       
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