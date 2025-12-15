package com.mycompany.ecom.Controller;

import User.DAO.UserDAOImpl;
import User.Model.User;
import User.Service.UserService;
import User.Service.UserServiceImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "EmployeeController", urlPatterns = {"/EmployeeController"})
public class EmployeeController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(EmployeeController.class.getName());
    private UserService service;
    private HttpSession session;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.log(Level.INFO, "Processing POST request");

        try {
            service = new UserServiceImpl(new UserDAOImpl());
            session = request.getSession();

            String action = request.getParameter("submit");

            if ("logout".equals(action)) {
                // Handle logout
                logger.log(Level.INFO, "User logged out successfully");
                session.invalidate(); // Ensure the session is invalidated
                response.sendRedirect("index.jsp");
                return;
            }

            if ("login".equals(action)) {
                // Handle login
                String identifier = request.getParameter("identifier");
                String password = request.getParameter("password");

                User user = null;
                if (identifier.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
                    // Email
                    user = service.getUserByEmail(identifier);
                } else {
                    // Employee ID
                    user = service.getUserByEmployeeId(identifier);
                }

                if (user != null && user.getPassword().equals(password)) {
                    session.setAttribute("user", user);
                    session.setAttribute("prices", new ArrayList<>());
                    session.setAttribute("ids", new ArrayList<>());

                    switch (user.getAccessLevel()) {
                        case 1:
                        case 2:
                            request.getRequestDispatcher("cashierWel.jsp").forward(request, response);
                            break;
                        case 3:
                            request.getRequestDispatcher("managerWelcome.jsp").forward(request, response);
                            break;
                        case 4:
                            request.getRequestDispatcher("regionalManager.jsp").forward(request, response);
                            break;
                        case 5:
                            request.getRequestDispatcher("Owner.jsp").forward(request, response);
                            break;
                        default:
                            request.getRequestDispatcher("index.jsp").forward(request, response);
                            break;
                    }
                } else {
                    request.setAttribute("errorMessage", "Invalid credentials, try again.");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error during login/logout process", e);
            request.setAttribute("errorMessage", "An error occurred. Please try again.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
