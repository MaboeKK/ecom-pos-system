/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ItemCreation.Controller;


import BarcodeGenerate.Barcode;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Tshiamo
 */
@WebServlet(name = "Customcode", urlPatterns = {"/Customcode"})
public class Customcode extends HttpServlet {


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
    Barcode bc = new Barcode();
        //HttpSession session = request.getSession();
        

        
        
        
       
        switch(request.getParameter("submit"))
        {
            case"next":
                break;
            case"Create":
                StringBuilder sb = new StringBuilder("");
                //Check if the batch number from endor has been entered
                String batchNumParam = request.getParameter("batchNum");
                if(batchNumParam == null || batchNumParam.isEmpty())
                {
                    String msg = "Please make sure that all the fields have been insereted ins";
                    request.setAttribute("msg", msg);
                    request.getRequestDispatcher("CustomCode.jsp").forward(request, response);
                    return;
                }
                //Take the customers inputs and create a new barcode
                String batchNumber = batchNumParam.substring(0, 4);
                String gender = request.getParameter("Gender");
                String department = request.getParameter("Department");
                String itemName = request.getParameter("itemName");

                int departmentR = 0;
                int genderR = 0;
                sb.append(batchNumber);
                if(!batchNumber.isEmpty() && gender != null && !gender.isEmpty() && department != null && !department.isEmpty() && itemName != null && !itemName.isEmpty())
                {
                    switch(request.getParameter("Gender"))
                    {

                        case"UNI-SEX":
                            genderR = 0;
                            sb.append(genderR);
                            break;

                        case"MALE":
                            genderR = 1;
                            sb.append(genderR);
                            break;

                        case"FEMALE":
                            genderR = 2;
                            sb.append(genderR);
                            break;
                    }


                    switch(request.getParameter("Department"))
                    {
                        case"TOPS":
                            departmentR = 0;
                            sb.append(departmentR);
                            break;

                        case"BOTTOMS":
                            departmentR = 1;
                            sb.append(departmentR);
                            break;

                        case"SHOES":
                             departmentR = 2;
                            sb.append(departmentR);
                            break;
                    }
                    String customCode = sb.toString();
                    bc.barCode(customCode);
                    request.getRequestDispatcher("cashierWel.jsp").forward(request, response);

                }
                else
                {
                    String msg = "Please make sure that all the fields have been insereted ins";
                    request.setAttribute("msg", msg);
                    request.getRequestDispatcher("CustomCode.jsp").forward(request, response);
                }
                break;
        }
        
       
       
       
    }



    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
