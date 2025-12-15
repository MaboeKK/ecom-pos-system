/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Product.Controller;

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
@WebServlet(name = "MinusServlet", urlPatterns = {"/MinusServlet"})
public class MinusServlet extends HttpServlet {

    
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
            out.println("<title>Servlet MinusServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MinusServlet at " + request.getContextPath() + "</h1>");
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
        while ((line = reader.readLine()) != null) {
            jsonBody.append(line);
        }
        // Parse JSON object
        
        
        JSONObject jsonObject = new JSONObject(jsonBody.toString());
        String recievedPrododuct = jsonObject.getString("product");
        String recievedPrice = jsonObject.getString("price");
        
        String state = "true";
        
        if(!ids.contains(Integer.valueOf(recievedPrododuct))){
            state="false";
        }
        
        if(ids.contains(Integer.valueOf(recievedPrododuct)) && prices.contains(Double.valueOf(recievedPrice))){
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
          
        jsonResponse.put("id", recievedPrododuct); 
        jsonResponse.put("count", count);
        jsonResponse.put("price", total);
        jsonResponse.put("state", state);
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
