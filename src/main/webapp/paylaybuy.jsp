<%-- 
    Document   : paylaybuy
    Created on : 25 Jul 2024, 2:58:36 PM
    Author     : Tsheno
--%>

<%@page import="Order.Model.Order"%>
<%@page import="Customer.Model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Customer customer = (Customer)request.getAttribute("customer");
    Order order = (Order)request.getAttribute("order");
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
            
            .detail:not(:last-child) {
                margin-bottom: 10px;
            }
            
            .detail {
                font-size: 20px;
                letter-spacing: 2px;
                
            }
            
        .search-btn ,.add_customer,.search_btn,.card_search_btn{
            padding: 12px 70px;
            font-family: "Lucida Sans", "Lucida Sans Regular", "Lucida Grande",
                  "Lucida Sans Unicode", Geneva, Verdana, sans-serif;
            margin-left: -4px;
            border: 0;
            outline: 0 !important;
            background: #8106EA;
            color: white;
            cursor: pointer;
            display: inline-block;
        } 
        
        .center {
            max-width: 600px;
            margin: 80px auto;
        }
        
        .detail_container {
            margin-bottom: 20px;
        }
        
        .select {
            margin-bottom: 20px;
        }
        
        .flex-container {
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding-right: 40px;

        }
        
        .customer {
            padding-top: 20px;
            font-size: 20px;
        }
        
        .sub-container {
            max-width: 500px;
            margin: 60px auto;
            padding: 10px;
            box-shadow: rgba(50, 50, 93, 0.25) 0px 2px 5px -1px, rgba(0, 0, 0, 0.3) 0px 1px 3px -1px;
        }
        
        .method {
            width: 100%;
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
                    <a id="top" href="LaybuyServlet?detail=<%=customer.getEmail()%>">Go back to previous page</a>
                    <a id="middle" href="index.jsp">Logout</a>
                </div>
                </div>
            </div>
            <div class="customer"><%=customer.getName()+" "+customer.getSurname()+"'s laybuy"%></div>        
        </div>
        <div class="sub-container">
            <div class="detail">Amount : R<%=order.getTotal()+"0"%> </div>
            <div class="status"><%=order.getStatus()%> </div>
                <form class="newuser" method="post" action="PaymentServlet">
                    <input type="hidden" name="order" value="<%=order.getId()%>"/>
                    <input type="hidden" name="amount" value="<%=order.getTotal()%>"/>
                    <input type="hidden" name="customer" value="<%=customer.getId()%>"/>
                    <div class="select">
                        <select name="method">
                            <option value="card">Card</option>
                            <option value="cash">Cash</option>
                        </select>
                    </div>    

                    <input type="submit" class="add_customer" value="Pay"/>
                </form>            
        </div>
                    
    </body>
</html>
