/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Item.Controller;

import Customer.DAO.CustomerDAOImpl;
import Customer.Model.Customer;
import Customer.Service.CustomerService;
import Customer.Service.CustomerServiceImpl;
import Item.DAO.ItemDAOImpl;
import Item.Service.ItemService;
import Item.Service.ItemServiceImpl;
import Item.Model.Item;
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
import java.util.ArrayList;
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
@WebServlet(name = "RefundServlet", urlPatterns = {"/RefundServlet"})
public class RefundServlet extends HttpServlet {

    private ItemService is = new ItemServiceImpl(new ItemDAOImpl());
    private ProductServices ps = new ProductServicesImp(new ProductDAOImp());
    private SaleService ss = new SaleServiceImpl(new SaleDAOImpl());
    private OrderService os = new OrderServiceImpl(new OrderDAOImpl());
    private CustomerService cs = new CustomerServiceImpl(new CustomerDAOImpl());
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RefundServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RefundServlet at " + request.getContextPath() + "</h1>");
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
        
        User user = (User)session.getAttribute("user");
        List<Integer> items = (List<Integer>)session.getAttribute("ids");
        List<Double> prices = (List<Double>)session.getAttribute("prices");
        List<Item> refunded = new ArrayList<>();
        Item item = new Item();
        double total = 0;
        for(int i=0;i<items.size();++i){
            item = is.getItemById(items.get(i));
            total += prices.get(i);
            refunded.add(item);
            Product product = ps.getStoreProductById(item.getProduct(), user.getStore());
            ps.setStoreQuantity(product.getQuantity()+1,item.getProduct(), user.getStore());
            int sale = item.getSale();
            double old = os.getOrderById(item.getOrderid()).getTotal();
            os.setAmount(old-item.getPrice(), item.getOrderid());
            is.removeItem(items.get(i));
            ss.removeSale(sale);
            
        }
        
        
        int id = item.getOrderid();
        items.clear();
        prices.clear();
        Customer customer = cs.getCustomerByOrder(id);
        
        session.setAttribute("ids", items);
        session.setAttribute("prices", prices);
        
        request.setAttribute("customer", customer);
        request.setAttribute("items", refunded);
        request.setAttribute("amount", total);
        request.getRequestDispatcher("successfulRefund.jsp").forward(request, response);
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
