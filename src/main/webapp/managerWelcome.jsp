<%-- 
    Document   : managerWelcome
    Created on : Jul 7, 2024, 5:04:56 PM
    Author     : Tshiamo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="Stylepages/manager/managerWel.css" rel="stylesheet" type="text/css"/>
<link href='https://fonts.googleapis.com/css?family=Alumni Sans Pinstripe' rel='stylesheet'>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Managers Welcome Page</title>
    </head>
    <style>
        a
        {
            text-decoration: none;
            font-family: 'Alumni Sans Pinstripe';
        }
        .Report
                {
            width: 40%;
            height: 120px;
            margin-left: 30%;
            background-image: url(Images/manager/business-report.png);margin-top: 20px;
            border-radius: 40%;
            background-size: cover;
            background-repeat: no-repeat;
            background-color: #fff;
        }
        .Figures
        {
            width: 40%;
            height: 120px;
            margin-left: 30%;
            background-image: url(Images/manager/data-analytics.png);margin-top: 20px;
            border-radius: 40%;
            background-size: cover;
            background-repeat: no-repeat;
            background-color: #fff;
        }
        .OrderStock
        {
            width: 40%;
            height: 120px;
            margin-left: 30%;
            background-image: url(Images/manager/replacement.png);margin-top: 20px;
            border-radius: 40%;
            background-size: cover;
            background-repeat: no-repeat;
            background-color: #fff;
        }
        .CreateUser
        {
            width: 40%;
            height: 120px;
            margin-left: 30%;
            background-image: url(Images/manager/user.png);margin-top: 20px;
            border-radius: 40%;
            background-size: cover;
            background-repeat: no-repeat;
            background-color: #fff;
        }
        a
        {
            text-decoration: none;
            font-family: 'Alumni Sans Pinstripe';
        }
    </style>
    <body>
        <h5>What are we doing today?</h5>
        <hr>
        <h3>Managerial Routines</h3>
        <div class="window">
            <div class="container">
                <section class="index-cat">
                    
                    <div class="index-cat-box">
                        <div class="Report">
                           
                        </div>
                        <a href="#"><h2>Get Shop Reports</h2></a>
                        
                    </div>
                    <div class="index-cat-box"> 
                        <div class="Figures">
                            
                        </div>
                        <a href="ReportForm.jsp"><h2>View Monthly figures</h2></a>
                    </div>
                    <div class="index-cat-box">
                        <div class="OrderStock">
                           
                        </div>
                        <a href="#"><h2>Order Item(s)</h2></a>
                    </div>
                    <div class="index-cat-box">
                        <div class="CreateUser">
                            
                        </div>
                        <a href="Register.jsp"><h2>Create New Cashier</h2></a>
                    </div>
                </section>
            </div>
        </div>
    </body>
</html>
