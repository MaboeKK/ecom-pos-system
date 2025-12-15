<%--
    Document   : Forgotpassword
    Created on : Jul 6, 2024, 10:16:24 AM
    Author     : Tshiamo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="Stylepages/ForgotPass.css" rel="stylesheet" type="text/css"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password - Ecom POS</title>
        <style>
            h5 {
                text-align: center;
            }
            .message {
                text-align: center;
                padding: 10px;
                margin: 10px auto;
                max-width: 400px;
                border-radius: 4px;
            }
            .success {
                background-color: #d4edda;
                color: #155724;
                border: 1px solid #c3e6cb;
            }
            .error {
                background-color: #f8d7da;
                color: #721c24;
                border: 1px solid #f5c6cb;
            }
        </style>
    </head>
    <body>
        <h5>To the Manager, please make sure that the user is a valid one and that the details are correct</h5>

        <% String msg = (String) request.getAttribute("message"); %>
        <% String msgType = (String) request.getAttribute("messageType"); %>
        <% if (msg != null) { %>
            <div class="message <%= msgType %>"><%= msg %></div>
        <% } %>

        <div class="forgot">
            <div class="form-container">
                <p class="title">Forgot Password</p>
                <form class="form" method="post" action="forgotpassword">
                    <label>Email</label>
                    <input type="email" name="email" class="input" placeholder="Enter user email" required>
                    <label>Managers Code</label>
                    <input type="password" name="managerCode" class="input" placeholder="Enter manager code" required>
                    <label>New Password</label>
                    <input type="password" name="newPassword" class="input" placeholder="Enter new password" required>
                    <label>Confirm Password</label>
                    <input type="password" name="confirmPassword" class="input" placeholder="Confirm new password" required>
                    <input class="form-btn" name="submit" type="submit" value="Reset Password">
                </form>
                <p style="text-align: center; margin-top: 15px;">
                    <a href="index.jsp">Back to Login</a>
                </p>
            </div>
        </div>
    </body>
</html>
