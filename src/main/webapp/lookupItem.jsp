<%-- 
    Document   : lookupItem
    Created on : Jul 4, 2024, 9:43:25 PM
    Author     : Tshiamo
--%>

<%@page import="java.util.List"%>
<%@page import="ItemCreation.Model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="Stylepages/lookupItem.css" rel="stylesheet" type="text/css"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Look Up Item Page</title>
    </head>
    <style>
        
        .formB
        {
            width: 90%;
            height: 90%;
            /*background-color: #C11AF2;*/
            padding: 0 50px;
            
        }
        .searchF
        {
            align-content: center;
            
            
            padding: 10px 0;
        }
        .window
        {
            width: 100%;
            height: 80%;
            /*background-color: #8106EA:*/
            align-content: center;
            
        }
        .findings
        {
            background-color: white;
            width: 100%;
            height: 80%;
            border-radius: 5px;
            box-shadow: 0px 10px 20px -18px;
        }
        h1
        {
            text-align: center;
            margin: 100px 0 0 0;
        }
        h5
        {
            text-align: center;
        }

    </style>
     <%
            HttpSession session1 = request.getSession();
            Product type = (Product) session.getAttribute("item");
            Product product = (Product) session.getAttribute("product");
            List<Product> item = (List<Product>) session.getAttribute("itemList");
            if(type != null)
            {
        %>
    <body>
        <h1>Hello World!</h1>
        <h5>
            Which product are we looking for?
        </h5>
        <hr>
        <div class="window">
           <div class="formB">
            <div class="searchF">
                <input class="input" name="text" placeholder="Search..." type="search">
                <div class="findings">
                    <div class="itemList">
                    <table>   
                        <% if(product != null){ %>
                   
                    <tr>
                      <td><%= product.getSubCategory() %></td>
                      <td><%= product.getName() %></td>
                      <td><%= product.getDescription() %></td>
                      <td><%= product.getGender() %></td>
                      <td><%= product.getPrice() %></td>
                      <td><%= product.getBarcode() %></td>
                      <td><%= product.getQuantity() %></td>
                      <td><%= product.getCreate() %></td>
                      <td><%= product.getUpdate() %></td>
                    </tr>
                    <tr>
                      <td><%= product.getSubCategory() %></td>
                      <td><%= product.getName() %></td>
                      <td><%= product.getDescription() %></td>
                      <td><%= product.getGender() %></td>
                      <td><%= product.getPrice() %></td>
                      <td><%= product.getBarcode() %></td>
                      <td><%= product.getQuantity() %></td>
                      <td><%= product.getCreate() %></td>
                      <td><%= product.getUpdate() %></td>
                    </tr>
                    <% } %>
                 
                  <% } %>
                    </table>
                </div>
                </div>
            </div>
        </div> 
        </div>
        
    </body>
</html>
