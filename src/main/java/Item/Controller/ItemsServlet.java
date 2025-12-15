package Item.Controller;

import Customer.DAO.CustomerDAOImpl;
import Customer.Service.CustomerService;
import Customer.Service.CustomerServiceImpl;
import Item.DAO.ItemDAOImpl;
import Item.Service.ItemService;
import Item.Service.ItemServiceImpl;
import Item.Model.Item;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet(name = "ItemsServlet", urlPatterns = {"/ItemsServlet"})
public class ItemsServlet extends HttpServlet {

    ItemService is = new ItemServiceImpl(new ItemDAOImpl());
    CustomerService cs = new CustomerServiceImpl(new CustomerDAOImpl());
  
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ItemsServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ItemsServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Item> items = is.getItemsByOrder(Integer.parseInt(request.getParameter("sale")));

        double total = 0;

        for(Item item:items){
            total += item.getPrice();
        }

        request.setAttribute("customer", cs.getCustomerById(Integer.parseInt(request.getParameter("customer"))));
        request.setAttribute("items", items);
        request.setAttribute("price", total);
        request.setAttribute("order", request.getParameter("sale"));
        request.getRequestDispatcher("customerSale.jsp").forward(request, response);
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
