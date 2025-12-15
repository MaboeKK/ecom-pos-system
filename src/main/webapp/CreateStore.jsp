<%-- 
    Document   : CreateStore
    Created on : Jul 9, 2024, 11:50:00 AM
    Author     : Tshiamo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="Stylepages/newStore.css" rel="stylesheet" type="text/css"/>
<link href='https://fonts.googleapis.com/css?family=Advent Pro' rel='stylesheet'>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Store Page</title>
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
    <body>
        <style>
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
        .myFoot h2
        {
            text-align: right;
            color: #8106EA;
            margin-top: 150px;
            font-family: 'Advent Pro';
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

        </style>
        <%
            String msg = (String)request.getAttribute("msg");
            if(msg != null)
            {
        %>
        <h1><%= msg %></h1>
        <% } %>
        <div class="newStore">
            <div class="form-container">
                <p class="title">Create New Store</p>
                <form class="form" method="post" action="StoreServlet">
                  <label>STORE_LOCATION_PROVINCE</label>
                  <input name="STORE_LOCATION_PROVINCE" type="text" class="input" placeholder="">
                  <label>STORE_LOCATION_CITY</label>
                  <input name="STORE_LOCATION_CITY" type="text" class="input" placeholder="">
                  <label>STORE_LOCATION_ADDRESS</label>
                  <input name="STORE_LOCATION_ADDRESS" type="text" class="input" placeholder="">
                  <label>MANAGER_ID</label>
                  <input name="MANAGER_ID" type="text" class="input" placeholder="IF APPLICABLE">
                  
                  <input class="form-btn" name="submit" type="submit" value="create">
                </form>

             </div>
        </div>
    </body>
    <footer class="myFoot">
        <h2>© Vought Softwares</h2>
    </footer>
</html>
