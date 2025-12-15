<%-- 
    Document   : shortage
    Created on : Jul 18, 2024, 1:53:57 PM
    Author     : Tshiamo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="Stylepages/recive.css" rel="stylesheet" type="text/css"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shortage Page</title>
    </head>
    <style>
        .form-container {
            width: 550px;
            height: 300px;
            background-color: #fff;
            box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
            border-radius: 10px;
            box-sizing: border-box;
            padding: 20px 30px;
            margin-left: 38%;
            margin-top: 10%;
          }
        .recive
        {
            align-content: center;
            align-items: center;
            
            
        }
        .rating
        {
            align-self: center;
            align-content: center;
            align-items: center;
        }
        header
        {
            background-color: #8106EA;
            width: 100%;
            height: 10%;
            align-content: center;
        }
        header h1
        {
            text-align: center;
            color: #fff;
             font-family: 'Cinzel Decorative';
        }
        .myFoot h2
        {
            text-align: right;
            color: #8106EA;
            margin-top: 150px;
            font-family: 'Advent Pro';
        }
    </style>
    <body>
        <div class="recive">
            <div class="form-container">
                <p class="title"><h2>Shortage Report</h2></p>

            <form class="form" method="post" action="ProductServlet">
                <label>Get Current Shortages</label>
                <input class="form-btn" name="submit" type="submit" value="shortage">

            </form>
             </div>
            
        </div>
    </body>
</html>
