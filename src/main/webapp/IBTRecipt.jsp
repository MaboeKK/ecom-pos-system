<%-- 
    Document   : ProductIBT
    Created on : Jul 27, 2024, 10:23:34 AM
    Author     : Tshiamo
--%>

<%@page import="Store.Model.StoreProduct"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="Stylepages/productIBT.css" rel="stylesheet" type="text/css"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product IBT Page</title>
    </head>


<style>
        .IBT
        {
              margin: auto;
            width: 25%;
            /*border: 3px solid #73AD21;*/
            padding: 10px;
        }
    </style>

    <%
        
        StoreProduct IBTItem = (StoreProduct) session.getAttribute("ibtProduct");
        StoreProduct Item = (StoreProduct) request.getAttribute("ibtProduct");
        String size = (String) session.getAttribute("Size");
        String colour = (String) session.getAttribute("colour");
        double VAT =  Double.valueOf(IBTItem.getPrice()) * 0.15;
        double subTotal = Double.valueOf(IBTItem.getPrice()) - VAT;
        double ItemAmount = Double.valueOf(IBTItem.getPrice()); 

        if(IBTItem != null){
    %>
    <body>
        <div class="IBT">
            <form action="IBT" method="post">
        <div class="master-container">
         <div class="card cart">
           <label class="title">Your cart</label>
           <div class="products">
             <div class="product">
               <svg fill="none" viewBox="0 0 60 60" height="60" width="60" xmlns="http://www.w3.org/2000/svg">
       <rect fill="#FFF6EE" rx="8.25" height="60" width="60"></rect>
      <path stroke-linejoin="round" stroke-linecap="round" stroke-width="2.25" stroke="#FF8413" d="M27.75 21.75L26.25 23.25"></path>
       </svg>
       
               <div>
                   <h3> <%= IBTItem.getProductName() %> </h3> 
                 <p><%= size %></p>
                 <p><%= colour %></p>
               </div>
               <div class="quantity">
                 
                 <input type="number" name="quantity" class="input" pattern="\d+" placeholder="Quantity">
                 
               </div>
               <label class="price small">R <%= IBTItem.getPrice() %> </label>
             </div>
           </div>
         </div>

         <div class="card coupons">
           <label class="title">Special Request</label>
           <form class="form">
               <input type="text" placeholder="Add Reques" class="input_field">
               
           </form>
         </div>

         <div class="card checkout">
           <label class="title">Checkout</label>
           <div class="details">
             <span>Your cart subtotal:</span>
             <span>R<%= subTotal %></span>
             <span>Discount applied :</span>
             <span>R0.00</span>
             <span>Shipping fees:</span>
             <span>R0.00</span>
             <span>VAT:</span>
             <span>R<%= VAT %></span>
           </div>
           <div class="checkout--footer">
             <label class="price"> <sup> R <%= IBTItem.getPrice() %> </sup>  </label>
             <input class="form-btn" name="submit" type="submit" value="checkout">
           </div>
         </div>
       </div>
            </form>
      <% } %>
        </div>
    </body>
</html>
