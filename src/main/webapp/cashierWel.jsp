<%-- 
    Document   : cashierWel
    Created on : Jul 2, 2024, 6:51:01 AM
    Author     : Tshiamo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="Stylepages/CashierWe.css" rel="stylesheet" type="text/css"/>
<link href='https://fonts.googleapis.com/css?family=Alumni Sans Pinstripe' rel='stylesheet'>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cashier Welcome Page</title>
    </head>
    <style>       
        .optionContainer
        {
            text-align: center;
        }
        .index-cat
        {
            margin-top: 50px;
            align-content: center;
            
        }
        .Sale
        {
            width: 40%;
            height: 120px;
            margin-left: 30%;
            background-image: url(Images/Cashier/direct-marketing.png);margin-top: 20px;
            border-radius: 40%;
                        background-size: cover;
            background-repeat: no-repeat;
            background-color: #fff;
        }
        .Refund
        {
            width: 40%;
            height: 120px;
            margin-left: 30%;
            background-image: url(Images/Cashier/cash-payment.png);margin-top: 20px;
            border-radius: 40%;
                        background-size: cover;
            background-repeat: no-repeat;
            background-color: #fff;
        }
        .Lookup
        { 
            width: 40%;
            height: 120px;
            margin-left: 30%;
            background-image: url(Images/Cashier/search.png);margin-top: 20px;
            border-radius: 40%;
                        background-size: cover;
            background-repeat: no-repeat;
            background-color: #fff;
        }
        .Znumber
        {
            width: 40%;
            height: 120px;
            margin-left: 30%;
            background-image: url(Images/Cashier/invoice.png);margin-top: 20px;
            border-radius: 40%;
                        background-size: cover;
            background-repeat: no-repeat;
            background-color: #fff;
        }
        a
        {
            text-decoration: none;
            font-family: 'Alumni Sans Pinstripe';
        }.index-cat-box
        {
            box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
        }
        .index-cat-box:hover
        {
            letter-spacing: 3px;
            background-color: #fff;
            color: hsl(0, 0%, 100%);
            box-shadow: rgb(93 24 220) 0px 7px 29px 0px;
        }
        h5
        {
            font-size: 30px;
            font-family: 'Alumni Sans Pinstripe';
        }
        .Sale2
        {
            width: 40%;
            height: 120px;
            margin-left: 30%;
            background-image: url(Images/Reciver/bar.png);margin-top: 20px;
            border-radius: 40%;
                        background-size: cover;
            background-repeat: no-repeat;
            background-color: #fff;
        }
        .Refund2
        {
            width: 40%;
            height: 120px;
            margin-left: 30%;
            background-image: url(Images/Reciver/ba.png);margin-top: 20px;
            
                        background-size: cover;
            background-repeat: no-repeat;
            background-color: #fff;
        }
        .Lookup2
        { 
            width: 40%;
            height: 120px;
            margin-left: 30%;
            background-image: url(Images/Reciver/inventory-management.png);margin-top: 20px;
            border-radius: 40%;
                        background-size: cover;
            background-repeat: no-repeat;
            background-color: #fff;
        }
        .Znumber2
        {
            width: 40%;
            height: 120px;
            margin-left: 30%;
            background-image: url(Images/Reciver/out-of-stock.png);margin-top: 20px;
            border-radius: 40%;
                        background-size: cover;
            background-repeat: no-repeat;
            background-color: #fff;
        }
        hr
        {
            /*
            border: 3px solid #8106EA;
            border-radius: 5px;*/
        }
       
    </style>
    <body>

        <div class="window">
            
            <div class="optionContainer">
                
                <h5>What are we doing today?</h5>
                <hr>
                <h3>Sell stock</h3>
                <section class="index-cat">
                    <div class="index-cat-box">
                        <div class="Sale">
                           
                        </div>
                        <a href="ProductsServlet"><h2>Make A Sale(s)</h2></a>
                        
                    </div>                    
                    
                    <div class="index-cat-box"> 
                        <div class="Refund">
                            
                        </div>
                        <a href="cashierRefund.jsp"><h2>Refund Item(s)</h2></a>
                    </div>
                    <div class="index-cat-box">
                        <div class="Lookup">
                           
                        </div>
                        <a href="laybuys.jsp"><h2>Lookup Laybuy(s)</h2></a>
                    </div>
                    <div class="index-cat-box">
                        <div class="Znumber">
                            
                        </div>
                        <a href="#"><h2>Get Z Number</h2></a>
                    </div>
                </section>
                <h3>Receive stock</h3>
                <section class="index-cat">
                    
                    <div class="index-cat-box">
                        <div class="Sale2">
                           
                        </div>
                        <a href="ReciveItem.jsp"><h2>Receive Items(s)</h2></a>
                        
                    </div>
                    <div class="index-cat-box"> 
                        <div class="Refund2">
                            
                        </div>
                        <a href="IBT.jsp"><h2>IBT(s)</h2></a>
                    </div>
                    <div class="index-cat-box">
                        <div class="Lookup2">
                           
                        </div>
                        <a href="GetInventory.jsp"><h2>Get Inventory</h2></a>
                    </div>
                    <div class="index-cat-box">
                        <div class="Znumber2">
                            
                        </div>
                        <a href="shortage.jsp"><h2>Get Shortage</h2></a>
                    </div>
                </section>
            </div>
            
        </div>
    </body>
</html>
