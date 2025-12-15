<%-- 
    Document   : makeIBT
    Created on : Jul 25, 2024, 8:49:15 PM
    Author     : Tshiamo
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="IBTOrders.Model.IBTRecived"%>
<%@page import="Store.Model.StoreProduct"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="Stylepages/makeIBT.css" rel="stylesheet" type="text/css"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Make IBTs Page</title>
    </head>
    <style>
        
.button {
    margin-right: 10px;
  width: 50px;
  height: 50px;
  border-radius: 50%;
  background-color: rgb(20, 20, 20);
  border: none;
  font-weight: 600;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.164);
  cursor: pointer;
  transition-duration: 0.3s;
  overflow: hidden;
  position: relative;
  gap: 2px;
}

.svgIcon {
  width: 12px;
  transition-duration: 0.3s;
}

.svgIcon path {
  fill: white;
}

.button:hover {
  transition-duration: 0.3s;
  background-color: rgb(255, 69, 69);
  align-items: center;
  gap: 0;
}

.bin-top {
  transform-origin: bottom right;
}
.button:hover .bin-top {
  transition-duration: 0.5s;
  transform: rotate(160deg);
}

.order__tr img:hover
{
    background-color: #ee0d0d;
}
/**/
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
                padding: 10px 160px;
                overflow-y: scroll;
            }

            
            .invisible-scrollbar::-webkit-scrollbar {
                display: none;
            }
            
            .search-btn {
                padding: 12px 30px;
                font-family: "Lucida Sans", "Lucida Sans Regular", "Lucida Grande",
                      "Lucida Sans Unicode", Geneva, Verdana, sans-serif;
                margin-left: -4px;
                border: 0;
                outline: 0 !important;
                background: #8106EA;
                color: white;
                cursor: pointer;
                display: inline-block;
                height: 100%;
            }

        .search_form {
            height: 40px;
            display: flex;
            background-color: #fff;
            justify-content: space-between;
                      
        } 

        .search-txt{
            border: 0;
            outline: 0 !important;
            box-sizing: border-box;
            padding: 12px 30px;
            padding-left: 10px;
            display: inline-block;
            height: 100%;
            background-color: #F1EBFF;
            flex: 1;
        }        
        
        .search_container {
            max-width: 460px;
            margin: 50px auto;
        }
        
        
    </style>
   <%
        
        int count = 0;
        List<StoreProduct> storeInvent = (List<StoreProduct>) session.getAttribute("is");
        if(storeInvent != null){
    %>
    <header>
        <div class="use">
            <div class="paste-button">
            <button class="button">PK </button>
            <div class="dropdown-content">
                <a id="top" href="cashierWel.jsp">Home</a>
                <a id="middle" href="index.jsp">Logout</a>
            </div>
            </div>
        </div>
        <div class="search_container">
            <form class="search_form" method="post" action="IBT">
                <input type="text" placeholder="Enter Item number" class="search-txt" name="itemSelect"/>
                <input class="form-btn" name="submit" type="submit" value="order">
            </form>
        </div>
        
    </header>
    <body>
        <div class="viewIBTs">
            <section class="order">
                <h3 class="order__title">Make IBTs 
                    <span class="order__title-count">store Name</span>
                </h3>

                <table class="table table--order">
                    <thead>
                        <tr>
                        <tr>
                            <th>Item number</th>
                            <th>Image</th>
                            <th>Name Of Store</th>                                                    
                            <th>Item Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Recived</th>
                            
                          </tr>
                            

                        </tr>
                    </thead>
                    <tbody>
                       <% for(StoreProduct s : storeInvent){ %>
                        <tr>
                            <% count++; %>
                             <td>   <%= count %> <p class="order__price-cur">Item No.</p></td>
                            <td class="order__tr-image"><img src="Images/hange.png" class="order__image" alt="" /></td>
                            <td class="text--left">
                                <pclass = "order__name"> JHB </p>
                                    <p class="order__desc">From <b class="order__time">  <%= s.getStoreName() %>  </b> to <b class="order__time">2</b></p>
                            </td>
                           
                            <td> <%= s.getProductName() %> <p class="order__price-cur">units</p></td>
                            <td>
                                
                                <div class="order__price">
                                   <%= s.getQuantity() %> <p class="order__price-cur">Item Name</p>
                                </div>
                            </td>
                            
                            <td>   <%= s.getPrice() %>  <p class="order__price-cur">Rand value</p></td>
                            <td>   <%= s.getLastUpdate() %>  <p class="order__price-cur">Last Update</p></td>
                            
                           
                        </tr>
                        <% } %>
                    </tbody>
                </table>
                <% } %>
            </section>
            <%
                List<IBTRecived> myIBTList = new ArrayList<IBTRecived>();
                myIBTList = (List<IBTRecived>) session.getAttribute("myIBTs");
                int count2 = 0;
                if(myIBTList != null){
            %>
                        <section class="order">
                <h3 class="order__title">IBTs Made by Store  
                    <span class="order__title-count">store Name</span>
                </h3>

                <table class="table table--order">
                    <thead>
                        <tr>
                        <tr>
                                <th>Item number</th>
                                <th>Ordered by </th>
                                <th>Item Name</th>
                                <th>Store ID</th>
                                <th>Price</th>
                                
                          </tr>
                            

                        </tr>
                    </thead>
                    <tbody>
                       <% for(IBTRecived s : myIBTList){ %>
                        <tr>
                            <% count2++; %>
                             <td>   <%= count2 %> <p class="order__price-cur">Item No.</p></td>
                            <td>
                                
                                <div class="order__price">
                                   <%= s.getEmployeeID() %> <p class="order__price-cur">Emp Number</p>
                                </div>
                            </td>
                            <td class="text--left">
                                <pclass = "order__name"> JHB </p>
                                    <p class="order__desc">From <b class="order__time">  <%= s.getStoreName() %>  </b> to <b class="order__time">2</b></p>
                            </td>
                           
                            <td> <%= s.getStoreID() %> <p class="order__price-cur">Store ID</p></td>
                            <td>
                                
                                <div class="order__price">
                                   <%= s.getPrice() %>  <p class="order__price-cur">Rand value</p>
                                </div>
                            </td>

                        </tr>
                        <% } %>
                    </tbody>
                </table>
                <% } %>
            </section>
        </div>
    </body>
</html>