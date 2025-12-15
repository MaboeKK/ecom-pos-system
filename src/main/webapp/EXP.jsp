<%-- 
    Document   : EXP
    Created on : Aug 2, 2024, 7:09:56 AM
    Author     : Tshiamo
--%>

<%@page import="java.util.Date"%>
<%@page import="ItemCreation.Model.Product"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="Stylepages/makeIBT.css" rel="stylesheet" type="text/css"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shortage List Page</title>
    </head>
    <body>
        <%
            HttpSession session1 = request.getSession();
            List<Product> mylist = (List<Product>) session.getAttribute("myList");
            
            Date now = new Date();
            String currentTime = String.valueOf(now.getTime());
            
            if(mylist != null){
    %>
    <body>
        <div>
            <section class="order">
                <h3 class="order__title">Shortage
                    <span class="order__title-count">123</span>
                </h3>

                <table class="table table--order">
                    <thead>
                        <tr>
                          <th>SUB CATEGORY</th>
                          <th>NAME</th>
                          <th>DISCRIPTION</th> 
                          <th>GENDER</th>
                          <th>PRICE</th> 
                          <th>BARCODE</th>
                          <th>STOCK QUANTITY</th>
                          <th>CREATED</th>
                          <th>UPDATED</th>
                        </tr>
                    </thead>
                    <tbody>
                         <% for(Product p : mylist){ %>
                        <tr>
                            <td class="order__tr-image"><img src="Images/lack.png" class="order__image" alt="" /></td>
                            <td class="text--left">
                                <pclass="order__name">  <%= p.getName() %> </p>
                                    <p class="order__desc">с <b class="order__time">11:00</b> Current time <b class="order__time"> <%= currentTime %> </b></p>
                            </td>
                            <td> <%= p.getDescription() %> <p class="order__price-cur">Description</p></td>
                            <td>
                                <div class="order__price">
                                     <%= p.getGender() %>  <p class="order__price-cur">Gender</p>
                                </div>
                            </td>
                            <td> <%= p.getPrice() %>  <p class="order__price-cur">Rand value</p></td>
                            <td> <%= p.getBarcode() %> </td>
                            <td> <%= p.getQuantity() %> </td>
                            <td> <%= p.getCreate() %> </td>
                            <td> <%= p.getUpdate() %> </td>
                        </tr>

                        <% } %>
                        <% } %>
                    </tbody>
                </table>
            </section>
        </div>
    </body>
    </body>
</html>
