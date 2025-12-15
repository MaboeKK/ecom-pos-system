<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Success</title>
    <meta http-equiv="refresh" content="1;url=index.jsp"> <!-- Redirects after 1 second -->
    <link href="Stylepages/registration.css" rel="stylesheet" type="text/css"/>
    <style>
        h1 {
            text-align: center;
            color: #4CAF50; /* Green color for success */
        }
        p {
            text-align: center;
            font-size: 18px;
            color: #333; /* Dark text color */
        }
        .container {
            width: 50%;
            margin: 0 auto;
            padding: 20px;
            border-radius: 10px;
            border: 1px solid #c0c0c0;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
    </style>
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
<%
    String successMessage = "Registration was successful!";
%>
<div class="container">
    <h1><%= successMessage %></h1>
    <p>Your new user has been successfully registered. You will be redirected to the home page shortly. Alternatively, you can <a href="cashierWel.jsp">return to the previous page</a>.</p>
</div>
<footer class="myFoot">
    <h2>© Vought Softwares</h2>
</footer>
</body>
</html>
