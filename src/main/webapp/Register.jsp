<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="Connection.DbConn" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="User.Model.User" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Page</title>
    <link href="Stylepages/registration.css" rel="stylesheet" type="text/css"/>
</head>
<body>
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
    h5 {
        text-align: center;
        margin-left: 30px;
    }
    select {
        border-radius: 20px;
        border: 1px solid #c0c0c0;
        outline: 0 !important;
        box-sizing: border-box;
        padding: 12px 15px;
    }
    select option.disabled {
        color: #999; /* Grey color */
    }
</style>
<%
    User loggedInUser = (User) session.getAttribute("user");
    if (loggedInUser == null) {
        response.sendRedirect("index.jsp");
        return;
    }
    int loggedInUserStoreId = loggedInUser.getStore();

    // Display any messages
    String msg = (String) request.getAttribute("msg");
    if (msg != null) {
%>
<h1> <%= msg %> </h1>
<%
    }
%>
<h5>To the Manager, please make sure that the user is a valid one and that the details are correct</h5>
<div class="reg">
    <div class="form-container">
        <p class="title">Register New User</p>
        <form class="form" method="post" action="UserController">
            <input type="hidden" name="submit" value="Register" />
            <input type="hidden" name="store" value="<%= loggedInUserStoreId %>" />

            <label>First Name</label>
            <input name="firstname" type="text" class="input" placeholder="">
            <label>Surname</label>
            <input name="surname" type="text" class="input" placeholder="">
            <label>Email</label>
            <input name="email" type="email" class="input" placeholder="">
            <label>Position</label>
            <select name="accessLevel">
                <option disabled selected>Select Position</option>
                <option>Cashier</option>
                <option>Floor Manager</option>
            </select>
            <label>Store</label>
            <select name="store" disabled>
                <option value="<%= loggedInUserStoreId %>"><%= loggedInUserStoreId %></option>
            </select>

            <label>Password</label>
            <input name="password" type="password" class="input" placeholder="">
            <label>Confirm Password</label>
            <input name="confirmPassword" type="password" class="input" placeholder="">

            <input class="form-btn" name="submit" type="submit" value="Register">
        </form>
    </div>
</div>
<footer class="myFoot">
    <h2>© Vought Softwares</h2>
</footer>
</body>
</html>
