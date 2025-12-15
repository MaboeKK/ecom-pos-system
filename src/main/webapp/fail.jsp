<%--
    Document   : fail
    Created on : 02 Aug 2024, 11:22:59 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error - Ecom POS</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f5f5f5;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }
            .error-container {
                background: white;
                padding: 40px;
                border-radius: 8px;
                box-shadow: 0 2px 10px rgba(0,0,0,0.1);
                text-align: center;
                max-width: 400px;
            }
            .error-icon {
                font-size: 64px;
                color: #e74c3c;
                margin-bottom: 20px;
            }
            h1 {
                color: #333;
                margin-bottom: 10px;
            }
            p {
                color: #666;
                margin-bottom: 20px;
            }
            .btn {
                background-color: #3498db;
                color: white;
                padding: 12px 24px;
                border: none;
                border-radius: 4px;
                text-decoration: none;
                display: inline-block;
                cursor: pointer;
            }
            .btn:hover {
                background-color: #2980b9;
            }
        </style>
    </head>
    <body>
        <div class="error-container">
            <div class="error-icon">&#9888;</div>
            <h1>Operation Failed</h1>
            <p>Sorry, something went wrong. Please try again or contact your system administrator.</p>
            <a href="javascript:history.back()" class="btn">Go Back</a>
        </div>
    </body>
</html>
