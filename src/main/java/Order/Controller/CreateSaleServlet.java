/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Order.Controller;

import Customer.DAO.CustomerDAOImpl;
import Customer.Model.Customer;
import Customer.Service.CustomerService;
import Customer.Service.CustomerServiceImpl;
import Item.DAO.ItemDAOImpl;
import Item.Service.ItemService;
import Item.Service.ItemServiceImpl;
import Item.Model.Item;

import Order.DAO.OrderDAOImpl;
import Order.Model.Order;

import Order.Service.OrderService;
import Order.Service.OrderServiceImpl;
import PaymentStub.PaymentStub;
import Payments.DAO.PaymentDAOImpl;
import Payments.Model.Payment;
import Payments.Service.PaymentService;
import Payments.Service.PaymentServiceImpl;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tsheno
 */
@WebServlet(name = "CreateSaleServlet", urlPatterns = {"/CreateSaleServlet"})
public class CreateSaleServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(CreateSaleServlet.class.getName());
    private OrderService od = new OrderServiceImpl(new OrderDAOImpl());
    private ItemService is = new ItemServiceImpl(new ItemDAOImpl());
    private ProductServices ps = new ProductServicesImp(new ProductDAOImp());
    private CustomerService cs = new CustomerServiceImpl(new CustomerDAOImpl());
    private PaymentService payservice = new PaymentServiceImpl(new PaymentDAOImpl());
    private SaleService ss = new SaleServiceImpl(new SaleDAOImpl());
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateSaleServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateSaleServlet at " + request.getContextPath() + "</h1>");
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
        doPost(request,response);
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
        boolean transact = false;
        String method = request.getParameter("method");
        if(method == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Payment method is required");
            return;
        }
        if("laybuy".equals(method)){
            request.getRequestDispatcher("CreateOrderServlet").forward(request, response);
            return;
        }

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

        try {
            // Retrieve user information from session

            double totalPrice = 0;
            for (Double price:prices) {
                totalPrice += price;
            }

            PaymentStub pay = new PaymentStub(totalPrice);

            transact = "cash".equals(method) ? pay.Cash(totalPrice) : pay.Card(totalPrice);
            Order order = new Order();
            Customer customer = new Customer();
            if(request.getParameter("customer")!=null){
                order.setCustomer(Integer.parseInt(request.getParameter("customer")));
            }else{
                customer.setEmail(request.getParameter("email"));
                customer.setName(request.getParameter("name"));
                customer.setSurname(request.getParameter("surname"));
                customer.setPhoneNo(request.getParameter("phone"));
                cs.registerCustomer(customer);
                order.setCustomer(cs.getRecentCustomer().getId());
            }
            // Create a new order
            if(transact){

                order.setStatus("accepted");
                order.setTotal(totalPrice);
                order.setUser(Integer.parseInt(user.getEmployeeID()));
                order.setStore(user.getStore());

                od.addOrder(order);
                Order recent = od.getRecentOrder(Integer.parseInt(user.getEmployeeID()));
                int orderId = recent.getId();

                int sale = 0;

                for (int i = 0; i < ids.size(); ++i) {
                    Item item = new Item();
                    item.setOrderid(orderId);
                    item.setProduct(ids.get(i));
                    item.setPrice(prices.get(i));
                    item.setQuantity(1);

                    ss.addSale(ids.get(i),Integer.parseInt(user.getEmployeeID()),prices.get(i),user.getStore(),request.getParameter("customer")==null?order.getCustomer(): Integer.parseInt(request.getParameter("customer")));
                    sale = ss.getRecentSale(Integer.parseInt(user.getEmployeeID())).getId();
                    item.setSale(sale);
                    is.addItemOfSale(item);

                    int productId = ids.get(i);
                    int currentStock = ps.getStoreProductById(productId,user.getStore()).getQuantity();
                    ps.setStoreQuantity(currentStock - 1, productId,user.getStore());

                }

                Payment payment = new Payment();
                payment.setOrderId(orderId);
                payment.setAmount(totalPrice);
                // payment.setMethod(request.getParameter("method"));
                payment.setStatus("accepted");

                //  payservice.savePayment(payment);


            }


        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error processing sale", e);

        }
        ids.clear();
        prices.clear();


        request.setAttribute("orders", od.getOrders());
        request.setAttribute("ids", ids);
        request.setAttribute("prices", prices);
        request.setAttribute("products", ps.getStoreProducts(user.getStore()));
        if(transact)request.getRequestDispatcher("successfulSale.jsp").forward(request, response);
        else request.getRequestDispatcher("failedsale.jsp").forward(request, response);
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
