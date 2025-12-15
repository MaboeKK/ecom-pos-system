/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sale.Controller;

import Customer.DAO.CustomerDAOImpl;
import Customer.Model.Customer;
import Customer.Service.CustomerService;
import Customer.Service.CustomerServiceImpl;
import Order.DAO.OrderDAOImpl;
import Order.Model.Order;
import Order.Service.OrderService;
import Order.Service.OrderServiceImpl;
import ResponseData.ResponseData;
import User.Model.User;
import com.google.gson.Gson;
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
 * @author User
 */
@WebServlet(name = "SaleServlet", urlPatterns = {"/SaleServlet"})
public class SaleServlet extends HttpServlet {

    OrderService os = new OrderServiceImpl(new OrderDAOImpl());
    CustomerService cs = new CustomerServiceImpl(new CustomerDAOImpl());
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
            out.println("<title>Servlet SaleServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SaleServlet at " + request.getContextPath() + "</h1>");
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
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder jsonBody = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            jsonBody.append(line);
        }
        
        // Parse JSON object
        JSONObject jsonObject = new JSONObject(jsonBody.toString());
        String message = jsonObject.getString("search");
        
        List<Order> sales  = os.succesfullOrders(message, user.getStore());
        Customer customer = cs.getCustomer(message);
        
        ResponseData respond = new ResponseData(sales,customer);
        Gson gson = new Gson();
        
        String json = gson.toJson(respond);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);        
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
