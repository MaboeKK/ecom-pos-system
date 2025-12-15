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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
@WebServlet(name = "AmountServlet", urlPatterns = {"/AmountServlet"})
public class AmountServlet extends HttpServlet {

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
            out.println("<title>Servlet AmountServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AmountServlet at " + request.getContextPath() + "</h1>");
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

        String productStr = request.getParameter("product");
        String orderStr = request.getParameter("order");
        String itemStr = request.getParameter("item");
        String customerStr = request.getParameter("customer");
        String amountStr = request.getParameter("amount");

        if (productStr == null || orderStr == null || itemStr == null ||
            customerStr == null || amountStr == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing required parameters");
            return;
        }

        int product;
        int order;
        int item;
        int customer;
        double price;
        try {
            product = Integer.parseInt(productStr);
            order = Integer.parseInt(orderStr);
            item = Integer.parseInt(itemStr);
            customer = Integer.parseInt(customerStr);
            price = Double.parseDouble(amountStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format");
            return;
        }

        List<Product> products = ps.getProductByAmount(user.getStore(), price);
        
        request.setAttribute("order", order);
        request.setAttribute("product", product);
        request.setAttribute("order", order);
        request.setAttribute("item", item);
        request.setAttribute("products", products);
        request.setAttribute("customer", customer);
        
        request.getRequestDispatcher("replacements.jsp").forward(request, response);
                
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
        processRequest(request, response);
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
