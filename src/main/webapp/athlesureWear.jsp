<%-- 
    Document   : athlesureWear
    Created on : Jul 16, 2024, 7:15:17 AM
    Author     : Tshiamo
--%>

<%@page import="java.util.List"%>
<%@page import="ItemCreation.Model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="Stylepages/athlesure.css" rel="stylesheet" type="text/css"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Athleisure wear Page</title>
    </head>
    <style>
        table, th, td {
          border: 1px solid black;
          border-collapse: collapse;
        }
        
        .CRUDBtn
        {
            display: flex;
            width: 100%;
            height: 200px;
            background-color: #c0c0c0;
            margin-top: 30px;
            padding: 50px 50px;
        }
        .DELETE_btn
        {
            width: 30%;
            height: 10%;
            background-color: #8106EA;
        }
        .READ_btn
        {
            width: 30%;
            height: 10%;
            background-color: #8106EA;
        }
        .Update_btn
        {
            width: 30%;
            height: 10%;
            background-color: #8106EA;
        }
        </style>
    <body>
        <%
            HttpSession session1 = request.getSession();
            Product type = (Product) session.getAttribute("item");
            Product product = (Product) session.getAttribute("product");
            List<Product> item = (List<Product>) session.getAttribute("itemList");
            if(type != null)
            {
        %>
        <h1>Hello World!</h1>
        <div>
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
              <div class="CRUDBtn">
                  <div class="Update_btn">
                      <button class="btn"> UPDATE ITEM QUANTITY
                        </button>
                  </div>
                  <div class="DELETE_btn">
                      <button class="btn"> REMOVE/RENAME ITEM
                        </button>
                  </div>
                  <div class="READ_btn">
                     <button class="btn"> READ
                        </button> 
                  </div>
              </div>
    </body>
</html>
