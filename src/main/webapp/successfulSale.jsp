<%-- 
    Document   : succesfulsale
    Created on : 02 Aug 2024, 12:01:48 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .container {
                background-color: red;
                max-width: 600px;
                margin: 100px auto;
                
            }
            
            .message {
                padding: 20px;

            }
            
            .flex-container {
                display: flex;
                align-items: center;
                justify-content: space-between;
                border-bottom: 1px solid #000;                
            }
            
            .prev {
                padding-right: 20px;
                text-decoration: none;
                
            }
            
            .items {
                padding: 20px;
                background-color: purple;
                
            }
            
            .item {
                margin-bottom: 10px;
            }
            
            .total {
                padding: 12px;
                padding-left: 20px;
            }
            
        </style>
    </head>
    <body>
        <div class="container">
            <div class="flex-container">
                <div class="message">Sale Successful</div>
                <a class="prev" href="ProductsServlet">Back</a>
            </div>
            <div class="items">
                <div class="item">Energy Active Tank</div>
                <div class="item">Comfy Cotton T-Shirt</div>
                <div class="item">RelaxFit Lounge Shorts</div>
            </div>
            <div class="total">
                Total : <span>R 200.00</span>
            </div>
        </div>
    </body>
</html>
