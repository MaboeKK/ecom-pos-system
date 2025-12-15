<%-- 
    Document   : OTP
    Created on : Aug 2, 2024, 8:45:01 AM
    Author     : Tshiamo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OTP Page</title>
    </head>
    <style>
        .form {
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: space-around;
  width: 300px;
  background-color: white;
  border-radius: 12px;
  padding: 20px;
}

.title {
  font-size: 20px;
  font-weight: bold;
  color: black
}

.message {
  color: #a3a3a3;
  font-size: 14px;
  margin-top: 4px;
  text-align: center
}

.inputs {
  margin-top: 10px
}

.inputs input {
  width: 32px;
  height: 32px;
  text-align: center;
  border: none;
  border-bottom: 1.5px solid #d2d2d2;
  margin: 0 10px;
}

.inputs input:focus {
  border-bottom: 1.5px solid royalblue;
  outline: none;
}

.action {
  margin-top: 24px;
  padding: 12px 16px;
  border-radius: 8px;
  border: none;
  background-color: royalblue;
  color: white;
  cursor: pointer;
  align-self: end;
}
    </style>
    <body>
        <h1>Hello World!</h1>
        <div>
            <form class="form" action="forgotpassword" method="post"> 
                <div class="title">OTP</div>
                <div class="title">Verification Code</div>
                <p class="message">We have sent a verification code to your mobile number</p> 
                <div class="inputs"> <input id="input1" type="text" maxlength="1">
                    <input id="input2" type="text" maxlength="1">
                    <input id="input3" type="text" maxlength="1">
                    <input id="input4" type="text" maxlength="1"> 
                </div>
                <input class="form-btn" name="submit" type="submit" value="login">
            </form>
        </div>
    </body>
</html>
