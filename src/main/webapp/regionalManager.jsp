<%-- 
    Document   : rejonalManager
    Created on : Jul 8, 2024, 9:44:22 AM
    Author     : Tshiamo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="Stylepages/manager/regionalMan.css" rel="stylesheet" type="text/css"/>
<link href='https://fonts.googleapis.com/css?family=Alumni Sans Pinstripe' rel='stylesheet'>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Regional Manager Page</title>
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
            background-image: url(Images/manager/box.png);margin-top: 20px;
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
        .Stats
        {
            width: 40%;
            height: 120px;
            margin-left: 30%;
            background-image: url(Images/manager/business-rep.png);margin-top: 20px;
            border-radius: 40%;
            background-size: cover;
            background-repeat: no-repeat;
            background-color: #fff;
        }
        .wholesale
        {
            width: 40%;
            height: 120px;
            margin-left: 30%;
            background-image: url(Images/manager/warehouse.png);margin-top: 20px;
            border-radius: 40%;
            background-size: cover;
            background-repeat: no-repeat;
            background-color: #fff;
        }
        .CreateStore
        {
            width: 40%;
            height: 120px;
            margin-left: 30%;
            background-image: url(Images/manager/store.png);margin-top: 20px;
            border-radius: 40%;
            background-size: cover;
            background-repeat: no-repeat;
            background-color: #fff;
        }
        .AFigures
        {
            width: 40%;
            height: 120px;
            margin-left: 30%;
            background-image: url(Images/manager/progress.png);margin-top: 20px;
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
        <h3>Regional Manager Routines</h3>
        <div class="window">
            <div class="container">
                <section class="index-cat">
                    
                    <div class="index-cat-box">
                        <div class="Report">
                           
                        </div>
                        <a href="getSummaryReport.jsp"><h2>Get Summary Report</h2></a>
                        
                    </div>
                    <div class="index-cat-box"> 
                        <div class="Figures">
                            
                        </div>
                        <a href="getSummaryFigures.jsp"><h2>Get Summary figures</h2></a>
                    </div>
                    <div class="index-cat-box">
                        <div class="OrderStock">
                           
                        </div>
                        <a href="ReciveItem.jsp"><h2>View Shops Order(s)</h2></a>
                    </div>
                    <div class="index-cat-box">
                        <div class="CreateUser">
                            
                        </div>
                        <a href="regionalReg.jsp"><h2>Create New Manager</h2></a>
                    </div>
                </section>
                
                 <section class="index-cat">
                    
                    <div class="index-cat-box">
                        <div class="Stats">
                           
                        </div>
                        <a href="getAllShopReports.jsp"><h2>Get All Shops Reports</h2></a>
                        
                    </div>
                    <div class="index-cat-box"> 
                        <div class="AFigures">
                            
                        </div>
                        <a href="getAllShopFigures.jsp"><h2>View All Shops figures</h2></a>
                    </div>
                    <div class="index-cat-box">
                        <div class="wholesale">
                           
                        </div>
                        <a href="ReciveItem.jsp"><h2>View IBTs Item(s)</h2></a>
                    </div>
                    <div class="index-cat-box">
                        <div class="CreateStore">
                            
                        </div>
                        <a href="CreateStore.jsp"><h2>Create New Store</h2></a>
                    </div>
                </section>
            </div>
        </div>
    </body>
</html>
