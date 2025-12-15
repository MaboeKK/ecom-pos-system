<%-- 
    Document   : exchange
    Created on : 04 Aug 2024, 3:59:20 PM
    Author     : User
--%>

<%@page import="Item.Model.Item"%>
<%@page import="java.util.List"%>
<%@page import="Customer.Model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Customer customer = (Customer)request.getAttribute("customer");
    List<Item> items = (List<Item>)request.getAttribute("items");
    double price = (Double)request.getAttribute("price");
    String order = (String)request.getAttribute("order");

%>    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <h1>CAROL'S BOUTIQUE</h1>  
            <style>
                body{
                    margin: 0;
                    padding: 0;
                    box-sizing: border-box;
                    background-color: #d9b4f9;
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
                    margin-bottom: 40px;
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
                
                .item_availability {
                    text-align: center;
                    flex: 1;
                    font-size: 30px;
                    letter-spacing: 3px;
                }
                
                .items_list {
                    padding-left: 300px;
                    padding-right: 300px;
                    height: 70vh;
                }
                
                .flex-div {
                    display: flex;
                    align-items: center;
                    
                }
        .item {
            padding: 20px;
            background-color: #E3D8FF;
            box-shadow: 0px 3px 6px 0px rgba(0,0,0,0.2);
            cursor: pointer;
            padding-bottom: 10px;
        }
        
        .order {
          
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 10px;
        }
        
        .item:not(:last-child){
            margin-bottom: 16px;
            
        } 
        
        .replace {
            text-decoration: none;
            color: #5a04a4;
        }
            </style>            
        </header> 
        <div class="flex-div">
            <div class="use">
                <div class="paste-button">
                <button class="button">PK </button>
                <div class="dropdown-content">
                    <a id="top" href="cashierWel.jsp">Home</a>
                    <a id="middle" href="ItemsServlet?sale=<%=order%>&customer=<%=customer.getId()%>">Go back to previous page</a>
                    <a id="middle" href="index.jsp">Logout</a>
                </div>
                </div>

            </div>
        <div class="item_availability">You can exchange any of these items</div>            
        </div>

        
        <div class="items_list">
                    <%for(Item item:items){%>
                    <div class="item">
                        <div class="order" >
                            <div class="flex_item"><%="R "+item.getPrice()+"0"%></div>
                            
                            <div class="flex_item"><%=item.getName()%></div>
                        </div>
                        <a class="replace" href="AmountServlet?order=<%=item.getOrderid()%>&product=<%=item.getProduct()%>&item=<%=item.getItemid()%>&amount=<%=item.getPrice()%>&customer=<%=customer.getId()%>">New items to choose</a>    
                    </div>
                   
                    <%}%>           
        </div>
        
    </body>
</html>
