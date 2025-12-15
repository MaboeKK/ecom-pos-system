<%-- 
    Document   : viewIBT
    Created on : Jul 25, 2024, 8:48:40 PM
    Author     : Tshiamo
--%>

<%@page import="IBTOrders.Model.StoreIBT"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="Stylepages/makeIBT.css" rel="stylesheet" type="text/css"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View IBTs Page</title>
    </head>
    <style>
        
.button {
    margin-right: 10px;
  width: 50px;
  height: 50px;
  border-radius: 50%;
  background-color: rgb(20, 20, 20);
  border: none;
  font-weight: 600;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.164);
  cursor: pointer;
  transition-duration: 0.3s;
  overflow: hidden;
  position: relative;
  gap: 2px;
}

.svgIcon {
  width: 12px;
  transition-duration: 0.3s;
}

.svgIcon path {
  fill: white;
}

.button:hover {
  transition-duration: 0.3s;
  background-color: rgb(255, 69, 69);
  align-items: center;
  gap: 0;
}

.bin-top {
  transform-origin: bottom right;
}
.button:hover .bin-top {
  transition-duration: 0.5s;
  transform: rotate(160deg);
}

.order__tr img:hover
{
    background-color: #ee0d0d;
}

    </style>
    <%
        List<StoreIBT> myOrders = (List<StoreIBT>) session.getAttribute("orders");
        double i,t;
        if(myOrders != null){
    %>
    <body>
        <div class="viewIBTs">
            <section class="order">
                <h3 class="order__title">IBTs Received
                    <span class="order__title-count">store Name</span>
                </h3>

                <table class="table table--order">
                    <thead>
                        <tr>
                            <th>Photo</th>
                            <th class="text--left">Store Info</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Total</th>  
                            <th>Cancel Order</th>
                            

                        </tr>
                    </thead>
                    <tbody>
                        <% for(StoreIBT s : myOrders ){ %>
                        <tr>
                            <td class="order__tr-image"><img src="Images/hange.png" class="order__image" alt="" /></td>
                            <td class="text--left">
                                <p class = "order__name"> JHB </p>
                                    <p class="order__desc">From <b class="order__time"> <%= s.getStoreName() %> </b> to <b class="order__time"><%= s.getOrderDate() %></b></p>
                            </td>
                            <td> <%= s.getQuantity() %> <p class="order__price-cur">units</p></td>
                            <td>
                                <%
                                    i = Double.parseDouble(s.getQuantity());
                                    t = Double.parseDouble(s.getTotalPrice());
                                    double perItem = t/i;
                                %>
                                <div class="order__price">
                                    <%= perItem %> <p class="order__price-cur">per Item</p>
                                </div>
                            </td>
                            
                            <td>  <%= s.getTotalPrice() %> <p class="order__price-cur">Rand value</p></td>
                            <td class="order__tr"><a href="#"><img src="Images/deletH.png" class="order__image" alt="" /></a></td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </section>
        </div>
        <% } %>
    </body>
</html>

