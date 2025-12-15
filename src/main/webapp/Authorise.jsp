<%--
    Document   : Authorise
    Created on : Jul 7, 2024, 4:22:03 PM
    Author     : Tshiamo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="Stylepages/authorize.css" rel="stylesheet" type="text/css"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Authorize Page</title>
        <style>
            h5 {
                text-align: center;
                color: #333;
                padding: 10px;
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
        <h5>Floor Manager Authorization Required</h5>
        <p style="text-align: center; color: #666; font-size: 14px;">
            Please verify this action is legitimate before authorizing.
        </p>

        <% String msg = (String) request.getAttribute("message"); %>
        <% String msgType = (String) request.getAttribute("messageType"); %>
        <% if (msg != null) { %>
            <div class="message <%= msgType %>"><%= msg %></div>
        <% } %>

        <div class="authorize">
            <div class="form-container">
                <p class="title">Manager Authorization</p>
                <form class="form" method="post" action="AuthorizingServlet">
                    <label>Authorization Code</label>
                    <input type="password" name="authCode" class="input" placeholder="Enter manager code" required>
                    <input type="hidden" name="action" value="authorize">
                    <input class="form-btn" name="submit" type="submit" value="Authorize">
                </form>
                <p style="text-align: center; margin-top: 15px;">
                    <a href="javascript:history.back()">Cancel</a>
                </p>
            </div>
        </div>
    </body>
</html>
