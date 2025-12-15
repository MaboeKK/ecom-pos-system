<%-- 
    Document   : lookupItem
    Created on : Jul 4, 2024, 7:14:50 PM
    Author     : Tshiamo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="Stylepages/customCode.css" rel="stylesheet" type="text/css"/>
<link href='https://fonts.googleapis.com/css?family=Advent Pro' rel='stylesheet'>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Custom Code</title>
    </head>
    <style>

.lookUp
{
    width: 40%;
    height: 700px;
    margin-top: 50px;
    margin-left: 35%;
}
.form-container {
  width: 550px;
  height: 650px;
  background-color: #fff;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
  border-radius: 10px;
  box-sizing: border-box;
  padding: 20px 30px;
}
h5
{
    margin-bottom: 20px;
    text-align: center;
    margin-left: 90px;
    margin-top: 40px;
}
.myFoot h2
{
    text-align: right;
    color: #8106EA;
    margin-top: 80px;
    font-family: 'Advent Pro';
}
.form-btn {
  padding: 10px 15px;
  font-family: "Lucida Sans", "Lucida Sans Regular", "Lucida Grande",
        "Lucida Sans Unicode", Geneva, Verdana, sans-serif;
  border-radius: 20px;
  border: 0 !important;
  outline: 0 !important;
  background: #8106EA;
  color: white;
  cursor: pointer;
  box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
  
}
option
{
        border-radius: 20px;
        border: 1px solid #c0c0c0;
        outline: 0 !important;
        box-sizing: border-box;
        padding: 12px 15px;
}
select
{
            border-radius: 20px;
            border: 1px solid #c0c0c0;
            outline: 0 !important;
            box-sizing: border-box;
            padding: 12px 15px;
            font-family: "Lucida Sans", "Lucida Sans Regular", "Lucida Grande",
        "Lucida Sans Unicode", Geneva, Verdana, sans-serif;
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
}
.whou
{
    margin-top: 20px;
    width: 100%;
    height: 10%;
    background-color: #fff;
}

.use
{
    margin-top: 20px;
    width: 5%;
    
    text-align: center;
}
.use h1
{
    background-color: #8106EA;
    border-radius: 50%;
    padding: 10px 10px;
}
/**/
.paste-button {
  position: relative;
  display: block;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.button {
  background-color:#8106EA;
  color: #fff;
  padding: 10px 15px;
  font-size: 15px;
  font-weight: bold;
  border: 2px solid transparent;
  border-radius: 60%;
  cursor: pointer;
  
  
}

.dropdown-content {
  display: none;
  font-size: 13px;
  position: absolute;
  z-index: 1;
  min-width: 200px;
  background-color: #fff;
  border: 2px solid #8106EA;
  border-radius: 0px 15px 15px 15px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
}

.dropdown-content a {
  color: #8106EA;
  padding: 8px 10px;
  text-decoration: none;
  display: block;
  transition: 0.1s;
}

.dropdown-content a:hover {
  background-color: #8106EA;
  color: #212121;
}

.dropdown-content a:focus {
  background-color: #212121;
  color: #8106EA;
}

.dropdown-content #top:hover {
  border-radius: 0px 13px 0px 0px;
}

.dropdown-content #bottom:hover {
  border-radius: 0px 0px 13px 13px;
}

.paste-button:hover button {
  border-radius: 15px 15px 0px 0px;
}

.paste-button:hover .dropdown-content {
  display: block;
}

    </style>

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
        String msg = (String)request.getAttribute("msg");
        if(msg != null){
    %>
    <body>
        <h1>
           <%= msg %> 
        </h1>
        <% } %>
        <h5>Create a custom store barcode that can be used to label items using the stores Inventory system</h5>
        
        <div class="lookUp">
            <div class="form-container">
                <p class="title">Generate Inhouse Code</p>
                <form class="form" method="post" action="Customcode">
                  <label>Item Name</label>
                  <input name="itemName"type="text" class="input" placeholder="">
                  <label>Batch Barcode</label>
                  <input name="batchNum" type="text" class="input" placeholder="">
                  <label>Gender</label>
                  <select class="Gender">
                     <option>UNI-SEX</option>
                     <option>MALE</option>
                     <option>FEMALE</option>
                  </select>
                  <label>Quantity</label>
                  <input name="Quantity" type="text" class="input" placeholder="">
                  <label>Department</label>
                  <select class="Department">
                      <option></option>
                     <option>TOPS</option> 
                     <option>BOTTOMS</option>
                     <option>SHOES</option>
                  </select>
                  <input class="form-btn" name="submit" type="submit" value="Create">
                </form>

             </div>
        </div>
    </body>
    <footer class="myFoot">
        <h2>Vought Softwares</h2>
    </footer>
</html>
