<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href='https://fonts.googleapis.com/css?family=Advent+Pro' rel='stylesheet'>
<link href='https://fonts.googleapis.com/css?family=Cinzel+Decorative' rel='stylesheet'>
<!DOCTYPE html>
<html>
<head>
    <title>Start Page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="Stylepages/rateing.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        function showSuccessMessage() {
            document.getElementById("successMessage").style.display = "block";
            setTimeout(function() {
                window.location.href = "cashierWel.jsp";
            }, 1000); // 1 second delay
        }
    </script>
</head>
<style>
    .form-container {
        width: 550px;
        height: 450px;
        background-color: #fff;
        box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
        border-radius: 10px;
        box-sizing: border-box;
        padding: 20px 30px;
        margin-left: 38%;
        margin-top: 10%;
    }

    .star {
        align-content: center;
        align-items: center;
    }

    .rating {
        align-self: center;
        align-content: center;
        align-items: center;
    }

    header {
        background-color: #8106EA;
        width: 100%;
        height: 10%;
        align-content: center;
    }

    header h1 {
        text-align: center;
        color: #fff;
        font-family: 'Cinzel Decorative';
    }

    .myFoot h2 {
        text-align: right;
        color: #8106EA;
        margin-top: 150px;
        font-family: 'Advent Pro';
    }

    .error {
        color: red;
        text-align: center;
        margin-top: 10px;
    }

    .success {
        color: green;
        text-align: center;
        margin-top: 10px;
        display: none;
    }
</style>
<header>
    <h1>CAROL'S BOUTIQUE</h1>
</header>
<body>
<div class="star">
    <div class="form-container">
        <h2>Employee Login</h2>

        <form class="form" method="post" action="EmployeeController">
            <label>Email or Employee ID</label>
            <input name="identifier" type="text" class="input" placeholder="">
            <label>Password</label>
            <input name="password" type="password" class="input" placeholder="">
            <label>Till Printer</label>
            <input type="text" class="input" placeholder="">
            <p class="page-link">
                <a href="Forgotpassword.jsp"><span class="page-link-label">Forgot Password?</span></a>
            </p>
            <input class="form-btn" name="submit" type="submit" value="login">
        </form>

        <!-- Error message display -->
        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
        %>
        <p class="error"><%= errorMessage %></p>
        <%
            }
        %>

        <!-- Success message display -->
        <%
            Boolean success = (Boolean) request.getAttribute("success");
            if (success != null && success) {
        %>
        <p id="successMessage" class="success">Login Success</p>
        <script type="text/javascript">
            showSuccessMessage();
        </script>
        <%
            }
        %>
    </div>
</div>
</body>
<footer class="myFoot">
    <h2>© Vought Softwares</h2>
</footer>
</html>
