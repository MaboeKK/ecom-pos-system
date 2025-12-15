<%-- 
    Document   : successfulRefund
    Created on : 04 Aug 2024, 1:54:32 PM
    Author     : User
--%>

<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="Item.Model.Item"%>
<%@page import="java.util.List"%>
<%@page import="Customer.Model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Customer customer = (Customer)request.getAttribute("customer");
    List<Item> items = (List<Item>)request.getAttribute("items");
    Double amount = (Double)request.getAttribute("amount"); // Use Double instead of double
    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "ZA"));
%>    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body {
                padding: 0;
                margin: 0;
                box-sizing: border-box;
                background-color: #7405d3;
            }
            
            .proof {
                max-width: 500px;
                margin: 60px auto;
                box-shadow: rgba(0, 0, 0, 0.16) 0px 3px 6px, rgba(0, 0, 0, 0.23) 0px 3px 6px; 
                padding: 20px;
                height: 80vh;
                position: relative;
                background-color: #f2e6fd;
            }
            
            .message {
                font-size: 20px;
                letter-spacing: 1.5px;
                padding-bottom: 50px; 
                border-bottom: 1px solid #000;
            }
            
            .item {
                padding-top: 20px;
            }
            
            .items {
                height: 45vh;
                margin-bottom: 20px;
            }
            
            .customer {
                padding-bottom: 16px;
                padding-top: 16px;
                font-weight: 600;
            }
            
            .total {
                padding-bottom: 10px;
                font-weight: 600;
            }
            
            .close {
                position: absolute;
                text-decoration: none;
                top: 20px;
                right: 20px;
            }
        </style>
    </head>
    <body>
        <div class="proof">
            <div class="message">Refund Successful</div>
            <div class="customer">Customer: <span><%= customer.getName() + " " + customer.getSurname() %></span></div>
            <div class="declare">Items:</div>
            <div class="items">
                <% if (items != null) { %>
                    <% for (Item item : items) { %>
                        <div class="item">
                            <%= item.getName() %>
                        </div>
                    <% } %>
                <% } else { %>
                    <div class="item">No items available.</div>
                <% } %>
            </div>
            <div class="total">Total: <%= currencyFormat.format(amount) %></div> 
            <div class="email">An email has been sent to this customer.</div>
            <a class="close" href="cashierRefund.jsp"><div>Back</div></a>
        </div>
    </body>
</html>