<%-- 
    Document   : shortageList
    Created on : Jul 18, 2024, 2:39:14 PM
    Author     : Tshiamo
--%>

<%@page import="ItemCreation.Model.Product"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shortage List Page</title>
    </head>
    <style>

    </style>
    </head>
    <%
            HttpSession session1 = request.getSession();
            List<Product> mylist = (List<Product>) session.getAttribute("myList");
            
            if(mylist != null){
    %>
    <body>

        <h2>Shortage Borders</h2>
        <p>If you want the borders to collapse into one border, add the CSS border-collapse property.</p>

        <table style="width:100%">
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
                <% for(Product p : mylist){ %>
            <tr>
              <td> <%= p.getSubCategory() %> </td>
              <td> <%= p.getName() %> </td>
              <td> <%= p.getDescription() %> </td>
              <td> <%= p.getGender() %> </td>
              <td> <%= p.getPrice() %> </td>
              <td> <%= p.getBarcode() %> </td>
              <td> <%= p.getQuantity() %> </td>
              <td> <%= p.getCreate() %> </td>
              <td> <%= p.getUpdate() %> </td>
            </tr>
         <% } %>
         <% } %>
        </table>

    </body>
</html>
