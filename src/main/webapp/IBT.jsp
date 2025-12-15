<%-- 
    Document   : IBT
    Created on : Jul 25, 2024, 8:22:20 PM
    Author     : Tshiamo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="Stylepages/recive.css" rel="stylesheet" type="text/css"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IBT Home Page</title>
    </head>
    <style>
        .form-container {
  width: 550px;
  height: 400px;
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
    <header>
        <h1>CAROL'S BOUTIQUE</h1>
    </header>
    <body>
       
        <div class="recive">
            <div class="form-container">
                <p class="title"><h2>Customer Feedback</h2></p>

    <form class="form" method="post" action="IBT">
                <input class="form-btn" name="submit" type="submit" value="View Stores IBT(s)">
                <input class="form-btn" name="submit" type="submit" value="Make IBT(s)">

            </form>
             </div>
            
        </div>
    </body>
    <footer class="myFoot">
        <h2>© Vought Softwares</h2>
    </footer>
</html>
