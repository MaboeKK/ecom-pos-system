<%-- 
    Document   : replacements
    Created on : 31 Jul 2024, 10:46:32 PM
    Author     : User
--%>

<%@page import="ItemCreation.Model.Product"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
        Integer sale = (Integer)request.getAttribute("order");
        Integer customer = (Integer)request.getAttribute("customer");
        Integer product = (Integer)request.getAttribute("product");
        Integer item = (Integer)request.getAttribute("item");
        Integer order = (Integer)request.getAttribute("order");
        List<Product> products = (List<Product>)request.getAttribute("products");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body{
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                
            }

            header
            {
                background-color: #8106EA;
                width: 100%;
                height: 10%;
                align-content: center;
                padding-bottom: 17px;
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
            
            .dataContainer {

                height: 60vh;
                display: flex;
                flex-direction: column;
                padding: 10px 300px;
                overflow-y: scroll;
            }

            
            .invisible-scrollbar::-webkit-scrollbar {
                display: none;
            } 
            .order {
            
                padding: 20px;
                display: flex;
                align-items: center;
                justify-content: space-between;
                background-color: #E3D8FF;
                box-shadow: 0px 3px 6px 0px rgba(0,0,0,0.2);            
            }
        
            .order:not(:last-child){
                margin-bottom: 16px;
            
            }

            .select {
                text-decoration: none;
            }
            
            .flex-container {
                display: flex;
                align-items: center;
                margin-top: 20px;
  
            }
            
            .option {
                margin-left: 450px;
                font-size: 30px;
                letter-spacing: 3px;
            }
            
        </style>
    </head>
    <body>
        <header>
            <h1>CAROL'S BOUTIQUE</h1>
        </header>
                <div class="flex-container">
                    <div class="use">
                        <div class="paste-button">
                        <button class="button">PK </button>
                        <div class="dropdown-content">
                            <a id="top" href="cashierWel.jsp">Home</a>
                            <a id="middle" href="OrderItemServlet?customer=<%=customer%>&order=<%=order%>">Go back to previous page</a>
                            <a id="middle" href="index.jsp">Logout</a>
                        </div>
                        </div>
                    </div>
                            <div class="option">Choose a new item</div>                      
                </div>        
        <div class="dataContainer">
            <%for(Product prod:products){%>
                <div class="order"><div><%=prod.getName()%></div><a class="select" href="ReplacementServlet?sale=<%=sale%>&product=<%=product%>&item=<%=item%>&order=<%=order%>&prodId=<%=prod.getId()%>&amount=<%=prod.getPrice()%>&customer=<%=customer%>">select</a></div>
            <%}%>            
        </div>

    </body>
</html>
