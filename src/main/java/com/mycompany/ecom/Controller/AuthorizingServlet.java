/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ecom.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tshiamo
 */
@WebServlet(name = "AuthorizingServlet", urlPatterns = {"/AuthorizingServlet"})
public class AuthorizingServlet extends HttpServlet {

    // Authorization password loaded from environment variable for security
    // Set MANAGER_AUTH_PASSWORD environment variable before deployment
    private static final String AUTH_PASSWORD = getEnvOrDefault("MANAGER_AUTH_PASSWORD", "");

    private static String getEnvOrDefault(String key, String defaultValue) {
        String value = System.getProperty(key);
        if (value != null && !value.isEmpty()) {
            return value;
        }
        value = System.getenv(key);
        if (value != null && !value.isEmpty()) {
            return value;
        }
        return defaultValue;
    }

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

    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String submitAction = request.getParameter("submit");
        String password = request.getParameter("password");

        if (submitAction == null || password == null) {
            request.setAttribute("msg", "Invalid request parameters");
            request.getRequestDispatcher("cashierRefund.jsp").forward(request, response);
            return;
        }

        switch(submitAction)
        {
            case "refund":
                if(password.equals(AUTH_PASSWORD) && !AUTH_PASSWORD.isEmpty())
                {
                    String msg = "Approved, you may refund the customer";
                    request.setAttribute("msg", msg);
                    request.getRequestDispatcher("cashierRefund.jsp").forward(request, response);
                }
                else
                {
                    String msg = "Invalid password, you have 2 more tried before the Owner is alerted";
                    request.setAttribute("msg", msg);
                    request.getRequestDispatcher("cashierRefund.jsp").forward(request, response);
                }
                break;
        }
        
        
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
