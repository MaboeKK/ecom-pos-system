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
import Product.DAO.ProductDAOImp;
import Product.Service.ProductServices;
import Product.Service.ProductServicesImp;
import User.Model.User;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "CreateOrderServlet", urlPatterns = {"/CreateOrderServlet"})
public class CreateOrderServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(CreateOrderServlet.class.getName());
    private OrderService od = new OrderServiceImpl(new OrderDAOImpl());
    private ItemService is = new ItemServiceImpl(new ItemDAOImpl());
    private ProductServices ps = new ProductServicesImp(new ProductDAOImp());
    private CustomerService cs = new CustomerServiceImpl(new CustomerDAOImpl());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
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
        List<Integer> ids = (List<Integer>) session.getAttribute("ids");
        List<Double> prices = (List<Double>) session.getAttribute("prices");
        if (ids == null || prices == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Cart not initialized");
            return;
        }

        try {
            double totalPrice = 0;
            for (Double price:prices) {
                totalPrice += price;
            }

            // Create a new order
            Order order = new Order();
            Customer customer = new Customer();
            order.setStatus("pending");
            order.setTotal(totalPrice);
            order.setUser(Integer.parseInt(user.getEmployeeID()));
            order.setStore(user.getStore());
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

            od.addOrder(order);

            int orderId = od.getRecentOrder(Integer.parseInt(user.getEmployeeID())).getId();

            for (int i = 0; i < ids.size(); ++i) {
                Item item = new Item();
                item.setOrderid(orderId);
                item.setProduct(ids.get(i));
                item.setPrice(prices.get(i));
                item.setQuantity(1);
                is.addItem(item);

                int productId = ids.get(i);
                logger.log(Level.FINE, "Processing product ID: {0}", productId);
                int currentStock = ps.getStoreProductById(productId, user.getStore()).getQuantity();
                logger.log(Level.FINE, "Current quantity: {0}, num of products: {1}", new Object[]{currentStock, ids.size()});
                ps.setStoreQuantity(currentStock - 1, productId,user.getStore());


            }

            ids.clear();
            prices.clear();

            request.setAttribute("orders", od.getOrders());
            request.setAttribute("ids", ids);
            request.setAttribute("prices", prices);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error creating order", e);

        }

        request.getRequestDispatcher("laybuys.jsp").forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Create Order Servlet";
    }
}