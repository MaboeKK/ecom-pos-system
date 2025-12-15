<%-- 
    Document   : GetInventory
    Created on : Jul 5, 2024, 1:01:04 PM
    Author     : Tshiamo
--%>

<%@page import="java.util.Date"%>
<%@page import="User.Model.User"%>
<%@page import="User.Model.User"%>
<%@page import="Store.Model.StoreProduct"%>
<%@page import="java.util.List"%>
<%@page import="ItemCreation.Model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="Stylepages/recive.css" rel="stylesheet" type="text/css"/>
<link href="Stylepages/makeIBT.css" rel="stylesheet" type="text/css"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory Page</title>
    </head>
    
    <style>
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
    <div class="use">
        <div class="paste-button">
        <button class="button">PK </button>
        <div class="dropdown-content">
            <a id="top" href="cashierWel.jsp">Go back to Home page</a>
            <a id="middle" href="index.jsp">Logout</a>
          
        </div>
        </div>
    </div>
    
    <body>
        <%
            
            
            List<StoreProduct> productList = (List<StoreProduct>) session.getAttribute("myProductListShortage");
            User ur = (User) session.getAttribute("user");
            
            int stName = ur.getStore();
            Date now = new Date();
            String currentTime = String.valueOf(now.getTime());
            
            if(productList != null){}
    %>
    <body>
        <div>
            <section class="order">
                <h3 class="order__title">Shortage
                    <span class="order__title-count"> <%= stName %></span>
                </h3>

                <table class="table table--order">
                    <thead>
                        <tr>
                          <th>SUB CATEGORY</th>
                          <th>NAME</th>
                          <th>DISCRIPTION</th> 
                          <th>GENDER</th>
                          <th>PRICE</th> 
                          <th>BARCODE</th>
                          <th>STOCK QUANTITY</th>
                          <th>CREATED</th>
                          <th>UPDATED</th>
                        </tr>
                    </thead>
                    <tbody>
                 
                    </tbody>
                </table>
            </section>
        </div>
    </body>
    
    <footer class="myFoot">
        <h2>© Vought Softwares</h2>
    </footer>
</html>
