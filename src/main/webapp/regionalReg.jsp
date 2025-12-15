<%-- 
    Document   : reginoalReg
    Created on : Jul 15, 2024, 6:55:26 AM
    Author     : Tshiamo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="Stylepages/registration.css" rel="stylesheet" type="text/css"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Page</title>
    </head>
        <header>
        <h1>CAROL'S BOUTIQUE</h1>
    </header>
    <div class="use">
        <div class="paste-button">
        <button class="button">PK </button>
        <div class="dropdown-content">
            <a id="top" href="cashierWel.jsp">Go back to previous page</a>
            <a id="middle" href="index.jsp">Logout</a>
          
        </div>
        </div>
    </div>
    <style>
        h5
        {
            text-align: center;
            margin-left: 30px;
        }
        select
        {
              border-radius: 20px;
            border: 1px solid #c0c0c0;
            outline: 0 !important;
            box-sizing: border-box;
            padding: 12px 15px;
        }
    </style>
    <body>
        <%
            String msg = (String)request.getAttribute("msg");
            if(msg != null){
        %>
        <h1> <%= msg %> </h1>
        <% } %>
        <h5>To the Manager, please make sure that the user is a valid one and that the details are correct</h5>
        <div class="reg">
            <div class="form-container">
                <p class="title">Register New User</p>
                <form class="form" method="post" action="UserController">
                  <label>Employee ID</label>
                  <input name="employeeID" type="text" class="input" placeholder="">
                  <label>First Name</label>
                  <input name="firstname" type="text" class="input" placeholder="">
                  <label>Surname</label>
                  <input name="surname" type="text" class="input" placeholder="">
                  <label>Email</label>
                  <input name="email" type="email" class="input" placeholder="">
                  <label>Position</label>
                  <select name="accessLevel">
                      <option></option>
                      <option>Store Manager</option>
                      <option>Floor Manager</option>
                      <option>Standard worker</option>
                  </select>
                  <label>Password</label>
                  <input name="password" type="password" class="input" placeholder="">
                  <label>Confirm Password</label>
                  <input name="confirmPassword" type="password" class="input" placeholder="">
                  
                  <input class="form-btn" name="submit" type="submit" value="registration">
                </form>

             </div>
        </div>
    </body>
    
    <footer class="myFoot">
        <h2>© Vought Softwares</h2>
    </footer>
