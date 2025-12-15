/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Payments.Controller;

import Customer.DAO.CustomerDAOImpl;
import Customer.Service.CustomerService;
import Customer.Service.CustomerServiceImpl;
import Item.DAO.ItemDAOImpl;
import Item.Service.ItemService;
import Item.Service.ItemServiceImpl;
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
import Sale.Model.Sale;
import Sale.Service.SaleService;
import Sale.Service.SaleServiceImpl;
import User.Model.User;
import java.io.IOException;
import java.util.List;
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
@WebServlet(name = "PaymentServlet", urlPatterns = {"/PaymentServlet"})
public class PaymentServlet extends HttpServlet {
    
    private OrderService os = new OrderServiceImpl(new OrderDAOImpl());
    private CustomerService cs = new CustomerServiceImpl(new CustomerDAOImpl());
    private PaymentService ps = new PaymentServiceImpl(new PaymentDAOImpl());
    private ProductServices pss = new ProductServicesImp(new ProductDAOImp());
    private SaleService ss = new SaleServiceImpl(new SaleDAOImpl());
    private ItemService is = new ItemServiceImpl(new ItemDAOImpl());
  

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

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

                String amountStr = request.getParameter("amount");
                String customerStr = request.getParameter("customer");
                String orderStr = request.getParameter("order");
                if (amountStr == null || customerStr == null || orderStr == null) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing required parameters");
                    return;
                }

                double amount;
                int customer;
                int orderId;
                try {
                    amount = Double.parseDouble(amountStr);
                    customer = Integer.parseInt(customerStr);
                    orderId = Integer.parseInt(orderStr);
                } catch (NumberFormatException e) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format");
                    return;
                }

                PaymentStub stub = new PaymentStub(amount);
                
                String method = request.getParameter("method");
                boolean state = "card".equals(method) ? stub.Card(amount) : stub.Cash(amount);
                
                if(state){
                    os.updateStatus(orderId, "accepted");
                    Payment pay = new Payment();
                    pay.setAmount(amount);
                    pay.setOrderId(orderId);
                    //pay.setMethod(request.getParameter("method"));
                    pay.setStatus("accepted");

                    //ps.savePayment(pay);
                    Order order = os.getOrderById(orderId);

                    List<Integer> products = is.getProductId(order);

                    for(Integer i:products){

                        ss.addSale(i,Integer.parseInt(user.getEmployeeID()), amount, customer, user.getStore());
                    }


                    Sale sale = ss.getRecentSale(Integer.parseInt(user.getEmployeeID()));
                    is.setSale(orderId, sale.getId());
                }
                request.setAttribute("products", pss.getStoreProducts(user.getStore()));
                request.getRequestDispatcher("cashierSale.jsp").forward(request, response);
                
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
