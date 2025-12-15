<%-- 
    Document   : experiment
    Created on : Aug 1, 2024, 9:10:13 AM
    Author     : Tshiamo
--%>

<%@page import="IBTOrders.Model.IBTRecived"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Store.Model.StoreProduct"%>
<%@page import="IBTOrders.Model.StoreIBT"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="Stylepages/makeIBT.css" rel="stylesheet" type="text/css"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> IBTs Page</title>
    </head>
       <%
        
        int count = 0;
        List<StoreProduct> storeInvent = (List<StoreProduct>) session.getAttribute("st");
        if(storeInvent != null){ 
    %>
    <body>
        
         <section class="order">
            <h3 class="order__title">Make IBTs
                <span class="order__title-count">123</span>
            </h3>

            <table class="table table--order">
                <thead>
                     <tr>
                            <th>Item number</th>
                            <th>Image</th>
                            <th>Name Of Store</th>                                                    
                            <th>Item Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Recived</th>
                            
                          </tr>
                </thead>
                <tbody>
                       <% for(StoreProduct s : storeInvent){ %>
                        <tr>
                            <% count++; %>
                             <td>   <%= count %> <p class="order__price-cur">Item No.</p></td>
                            <td class="order__tr-image"><img src="Images/hange.png" class="order__image" alt="" /></td>
                            <td class="text--left">
                                <pclass = "order__name"> JHB </p>
                                    <p class="order__desc">From <b class="order__time">  <%= s.getStoreName() %>  </b> to <b class="order__time">2</b></p>
                            </td>
                           
                            <td> <%= s.getProductName() %> <p class="order__price-cur">units</p></td>
                            <td>
                                
                                <div class="order__price">
                                   <%= s.getQuantity() %> <p class="order__price-cur">Item Name</p>
                                </div>
                            </td>
                            
                            <td>   <%= s.getPrice() %>  <p class="order__price-cur">Rand value</p></td>
                            <td>   <%= s.getLastUpdate() %>  <p class="order__price-cur">Last Update</p></td>
                            
                           
                        </tr>
                        <% } %>
                    </tbody>
                     <% } %>
            </table>
        </section>
            
    </body>
     
</html>
