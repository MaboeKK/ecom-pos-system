<%-- 
    Document   : accept
    Created on : Aug 5, 2024, 2:45:06 PM
    Author     : Tshiamo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="Stylepages/confirm.css" rel="stylesheet" type="text/css"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accept/Confirm Page</title>
    </head>
    <style>
        .confirm
        {
            margin: auto;
            width: 30%;
            height: 40%;
            
            padding: 50px 50px;
            align-content: center;
            
        }
        form
        {
            margin: auto;
            padding: 50px 50px;
            align-content: center;
            margin-left: 30%;
        }
    </style>
    <body>
        <div class="confirm">
            <div class="card"> 
                <button class="dismiss" type="button">×</button> 
                <div class="header"> 

                    <div class="div_image_v">
                     <div class="image">
                       <svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"> <path d="M20 7L9.00004 18L3.99994 13" stroke="#000000" stroke-width="1.5" stroke-linecap="round" stroke-linejoin="round"></path> </g></svg>
                       </div> 
                   </div> 
                    <div class="content">
                       <span class="title">Order validated</span> 
                       <p class="message">Thank you for your purchase. you package will be delivered within 2 days of your purchase</p> 
                    </div> 
       
                </div> 
                <form action="Recivestock" method="post">
            <input class="form-btn" name="submit" type="submit" value="done">
        </form>
            </div>
        </div>
        
            
    </body>
</html>
