<%-- 
    Document   : leisureWear
    Created on : Jul 16, 2024, 7:14:04 AM
    Author     : Tshiamo
--%>

<%@page import="ItemCreation.Model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Leisure wear Page</title>
    </head>
     <style>
        table, th, td {
          border: 1px solid black;
          border-collapse: collapse;
        }
        </style>
    <body>
        <%
            HttpSession session1 = request.getSession();
            Product type = (Product) session.getAttribute("item");
            if(type != null){
        %>
        <h1>Hello World!</h1>
        <div>
            <table style="width:100%">
                <tr>
                  <th>SUB CAT</th>
                  <th>DISCRIPTION</th> 
                  <th>CREATED</th>
                  <th>UPDATED</th>
                </tr>
                <tr>
                  <td><%= type.getType() %></td>
                  <td><%= type.getDescription() %></td>
                  <td><%= type.getCreate()%></td>
                  <td><%= type.getUpdate() %></td>
                </tr>
                <tr>
                  <td><%= type.getType() %></td>
                  <td><%= type.getDescription() %></td>
                  <td><%= type.getCreate()%></td>
                  <td><%= type.getUpdate() %></td>
                </tr>

                <% } %>
                
              </table>
        </div>
    </body>
</html>
