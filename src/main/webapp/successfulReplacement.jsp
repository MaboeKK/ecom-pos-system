<%@page import="Customer.Model.Customer"%>
<%
  Customer customer = (Customer)request.getAttribute("customer");
  String oldItem = (String)request.getAttribute("old");
  String currentItem = (String)request.getAttribute("new");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Successful Exchange</title>
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
                height: 40vh;
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
                height: 15vh;
                margin-bottom: 20px;
            }
            
            .customer {
                padding-bottom: 16px;
                padding-top: 10px;
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
            
            .email {
                font-size: 14px;
                padding-top: 5px;
            }
            
            .declare {
                 
            }
            
    </style>
</head>
<body>
    <div class="proof">
        <div class="message">Exchange Successful</div>
        <div class="customer">Customer: <span><%= customer.getName() + " " + customer.getSurname() %></span></div>
        <div class="items">
            <div class="declare">previous item:</div>
            <div class="item">
                <div><%= oldItem %></div>
            </div>
            <div class="declare">current item:</div>
            <div class="item">
                <div><%= currentItem %></div>
            </div>
        </div>
        <div class="email">An email has been sent to this customer.</div>
        <a class="close" href="cashierRefund.jsp"><div>Back</div></a>
    </div>
</body>
</html>
