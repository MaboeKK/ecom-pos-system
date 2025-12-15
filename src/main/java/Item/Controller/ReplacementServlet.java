package Item.Controller;

import Customer.DAO.CustomerDAOImpl;
import Customer.Service.CustomerService;
import Customer.Service.CustomerServiceImpl;
import Item.DAO.ItemDAOImpl;
import Item.Service.ItemService;
import Item.Service.ItemServiceImpl;
import Item.Model.Item;
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
@WebServlet(name = "ReplacementServlet", urlPatterns = {"/ReplacementServlet"})
public class ReplacementServlet extends HttpServlet {

    private SaleService ss = new SaleServiceImpl(new SaleDAOImpl());
    private OrderService os = new OrderServiceImpl(new OrderDAOImpl());
    private ItemService is = new ItemServiceImpl(new ItemDAOImpl());
    private ProductServices ps = new ProductServicesImp(new ProductDAOImp()); 
    private CustomerService cs = new CustomerServiceImpl(new CustomerDAOImpl());
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ExchangeServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ExchangeServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
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

        String saleStr = request.getParameter("sale");
        String customerStr = request.getParameter("customer");
        String prodIdStr = request.getParameter("prodId");
        String orderStr = request.getParameter("order");
        String itemStr = request.getParameter("item");
        String amountStr = request.getParameter("amount");

        if (saleStr == null || customerStr == null || prodIdStr == null ||
            orderStr == null || itemStr == null || amountStr == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing required parameters");
            return;
        }

        int sale;
        int customer;
        int product;
        int order;
        int item;
        double amount;
        try {
            sale = Integer.parseInt(saleStr);
            customer = Integer.parseInt(customerStr);
            product = Integer.parseInt(prodIdStr);
            order = Integer.parseInt(orderStr);
            item = Integer.parseInt(itemStr);
            amount = Double.parseDouble(amountStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format");
            return;
        }
        ss.addSale(product, Integer.parseInt(user.getEmployeeID()), amount, user.getStore(), customer);
        int saler = ss.getRecentSale(Integer.parseInt(user.getEmployeeID())).getId();
        String name = is.getItemById(item).getName();
        is.removeItem(item);
        int thesale = ss.getSale(item).getId();
        ss.removeSale(thesale);
        Item item1 = new Item();
        item1.setOrderid(order);
        item1.setProduct(product);
        item1.setPrice(amount);
        item1.setQuantity(1); 
        item1.setSale(saler);       
        
        is.addItemOfSale(item1);

        request.setAttribute("customer",cs.getCustomerById(customer));
        request.setAttribute("new", name);
        request.setAttribute("old", is.getRecentItem().getName());
        
        request.getRequestDispatcher("successfulReplacement.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
