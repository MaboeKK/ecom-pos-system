<%-- 
    Document   : cashierWel
    Created on : Jul 1, 2024, 9:39:54 PM
    Author     : Tshiamo
--%>

<%@page import="ItemCreation.Model.Product"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="Stylepages/cashSale.css" rel="stylesheet" type="text/css"/>
<%
    List<Product> products = (List<Product>)request.getAttribute("products");
    
%>    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>POS Page</title>
    </head>
    <style>
        
        .body{
            position: relative;
            text-align: center;
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
    
        .msg
        {
            width: 70%;
            height: 10vh;
            background-color: blueviolet;
            margin-left: 15%;
            text-align: center;
            padding-top: 28px;
            font-size: 24px;
            letter-spacing: 1.6px;
            color: #fff;
        }
        
        .contain{
            background-color: #fff;
        }
        
        .contain .itemList{
            background-color: #fff;
            
        }
        
        .Total {
            display: flex;
            flex-direction: column;
            justify-content: flex-start;
            padding-top: 30px;
            height: 360px;
            background-color: #f5f5f5;
        }
        
        .count,.add,.minus{
            font-weight: 600;
            width: 24px;
            height: 24px;
            border-radius: 100%;
            position: absolute;
            bottom: 6px;
            text-align: center;
            display: flex;
            align-items: center;
            justify-content: center;            
        }
        
        .count {
            left: 8px;
            background-color: white;
        }
        
        .add{
            right: 30px;
            background-color: #8106EA;
            cursor: pointer;
        }
        
        .minus {
            right: 70px;
            background-color: red;
            cursor: pointer;
        }
        
        .minus,.add {
            color: #fff;
        }
        
        .product {
            position: relative;  
        }
        
        .count_visible{
            display: none;
        }
        
        .hide {
            display: none;
        }
        
        .overlay {
            width: 100%;
            height: 100%;
            background-color: #222;
            position: fixed;
            top: 0;
            left: 0;
            z-index: 1000; /* Ensure this is higher than other elements */
            opacity: 0.8;
            backdrop-filter: blur(10px);
            cursor: pointer;

        }
        
        .non_visible {
            display: none; 
        }
        
        .form_container,.cash_container,.card_container {
            background-color: white;
            width: 50%;
            height: 70%;
            position: fixed;
            top: 50%; /* Adjust as per your layout needs */
            left: 50%; /* Adjust as per your layout needs */
            transform: translate(-50%, -50%);
            z-index: 1100; /* Ensure this is higher than overlay */
            padding: 20px;
            padding-top: 60px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
       
            
        }
        
        .Discount{
            width: 90%;
            height: 10%;
            color: white;
            background-color: #8106EA;
            padding: 20px 20px;
            margin-left: 10px;
        }
        
        .recipt{
            padding: 20px 20px;
            background-color: white;
        }
        
        .customer_heading{
            width: 100%;
            text-align: center;
            padding: 10px 0;
        }
        
        .results,.outcomes,.card_outcomes{
            width: 100%;
            height: 260px;
            padding-top: 40px;
        }
        
        .name-span{
            margin-right: 6px;
        }
        
        button {
            cursor: pointer;
        }
        
        .count {
            color: #8106EA;
            font-weight: 700;
        }
        
        .payment {
            margin-bottom: 20px;
        }

        .laybuy {
            margin-bottom: 20px;
        }

        .card {
            margin-bottom: 20px;
        }        
        
        .payment,.laybuy,.card,.cancel {
            padding-bottom: 20px;
            padding-top: 20px;
            text-align: left;
            padding-left: 140px;
            display: inline-block;
            font-size: 20px;
            border: none;
            letter-spacing: 1.4px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            background-color: white;
            color: #8106EA;
        }
        
        .scan:link {
            text-decoration: none;
            text-align: center;
            font-size: 24px;
            letter-spacing: 1.8px;
            display: flex;
            align-items: center;
            
        }
        
        .product-img{
            width: 100%;
        }
        
        .cancel {
            text-decoration: none;
            font-family: inherit;
        }
        
        .div,.Divide,.Divider{
            padding: 60px;
            height: 200px;
        }
        
        .search-txt,.register_customer,.search_txt,.card_search_txt {
            border: 0;
            outline: 0 !important;
            box-sizing: border-box;
            padding: 12px 30px;
            padding-left: 10px;
            display: inline-block;

        }
        
        .search-txt,.search_txt,.card_search_txt{
            height: 100%;
            flex: 1;
        }
        
        .register_customer {
            box-shadow: rgba(0, 0, 0, 0.35) 0px 3px 8px; 
            width: 100%;
        }
        
        .search_form ,.search-form,.card-search-form{
            height: 40px;
            display: flex;
            background-color: #fff;
            box-shadow: rgba(0, 0, 0, 0.35) 0px 3px 8px;            
        }
        
        .tabs ,.Tablets,.Choices{
            height: 40px;
            display: flex;
            align-items: center;
            justify-content: center;
            border-bottom: 2px solid #8106EA;
            font-size: 18px;
            font-weight: 600;
            outline: none;
         
        }
        
        .tab, .Tablet ,.Choice{
            background: none;
            border: none;
            font-size: 18px;
            font-weight: 600;
            color: #919191;
        }
        
        .tab_1 {
            margin-right: 100px;
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
        
        .search-btn,.search_btn,.card_search_btn{
            height: 100%;
        }
        
        .flex_container {
            display: flex;
            flex-direction: column;
            width: 100%;
            height: 100%;
        }
        
        .names {
            padding: 20px 20px 20px 0;
            letter-spacing: 1.2px;
            font-weight: 600;
            font-size: 20px;

        }
        
        .email {
            margin-bottom: 10px;   
        }
        
        .phone ,.email,.laybuy-btn{
            letter-spacing: 1.6px;
        }
        
        .phone {
            margin-bottom: 20px;
        }
        
        .laybuy-btn {
            text-decoration: none;
            color: white;
            font-weight: 600;
            background-color: #8106EA;
            padding: 10px 20px;
            display: inline-block;
            font-family: "Lucida Sans", "Lucida Sans Regular", "Lucida Grande",
                  "Lucida Sans Unicode", Geneva, Verdana, sans-serif;            
        }
        
        .not_customer {
            color: red;
            letter-spacing: 2px;
            padding-top: 30px;
        }
        
        .register_field {
            margin-bottom: 18px;
        }
        
        .link{
            display: flex;
            justify-content: flex-end;
        } 

        .toast {
            display: none;
            min-width: 300px;
            margin: 10px;
            padding: 10px;
            border-radius: 5px;
            color: #fff;
            background-color: #ef233c;
            position: relative;
            font-size: 14px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
            position: fixed;
            left: 240px;
                
        }

        .toast.show {
            display: block;
        }       

        .flex-container {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-right: 20px;
        }
        
        .item-select {
            font-size: 22px;
            font-weight: 500;
            flex: 1;
            text-align: center;
            align-items: center;
            padding-top: 20px;
        }
    </style>
    <body>

        <div class="overlay non_visible">
         
        </div>
        <div class="form_container non_visible">
            <div class="flex_container">
                <div class="tabs">
                    <button class="tab tab_1" data-num="1" required="true">Search</button>
                    <button class="tab" data-num="2">New</button>
                </div>
            <div class="div div_1">
                <form class="search_form">
                    <input type="text" placeholder="Phone Number or Email" class="search-txt" name="customer"/>
                    <input type="button" placeholder="Phone Number or Email" class="search-btn" value="Search"/>
                </form>
                <div class="results">
                
                </div>
            </div>
            <div class="div div_2 non_visible">
                <form method="post" action="CreateOrderServlet" class="newuser">
                    <div class="register_field">
                         <input type="text" placeholder="Name" name="name" class="register_customer"/>
                    </div> 
                    <div class="register_field">
                        <input type="text" placeholder="Surname" name="surname" class="register_customer"/>
                    </div> 
                    <div class="register_field">
                        <input type="email" placeholder="Email" name="email" class="register_customer"/>  
                    </div>
                    <div class="register_field">
                        <input type="tel" name="phone" placeholder="Phone number" class="register_customer"/>
                    </div>                    
                    <input class="add_customer" type="submit" value="Add Customer"/>
                </form>                
            </div>
                
            </div>

        </div>   

        <div class="cash_container non_visible"> 
            <div class="flex_container">
                <div class="Tablets">
                    <button class="Tablet tab_1" data-num="1">Search</button>
                    <button class="Tablet" data-num="2">New</button>
                </div>
                <div class="Divide Divide_1">
                    <form class="search-form">
                        <input type="text" placeholder="Phone Number or Email" class="search_txt" name="customer"/>
                        <input type="button" placeholder="Phone Number or Email" class="search_btn" value="Find"/>
                    </form>
                    <div class="outcomes">
                
                    </div>
                </div>
                <div class="Divide Divide_2 non_visible">
                    <form method="post" action="CreateSaleServlet" class="neweruser">
                        <div class="register_field">
                            <input type="text" placeholder="name" name="name" class="register_customer"/>                            
                        </div>
                        <div class="register_field">
                            <input type="text" placeholder="surname" name="surname" class="register_customer"/>                            
                        </div>
                        <div class="register_field">
                            <input type="email" placeholder="email" name="email" class="register_customer"/>                            
                        </div>
                        <div class="register_field">
                            <input type="tel" name="phone" placeholder="Phone number" class="register_customer"/>                            
                        </div>                        
                        <input type="hidden" name="method" value="cash"/>
                        <input class="add_customer" type="submit" value="Add Customer"/>
                    </form>                    
                </div>
            </div>
            
        </div>   

        <div class="card_container non_visible"> 
            <div class="flex_container">
                <div class="Choices">
                    <button class="Choice tab_1" data-num="1">Search</button>
                    <button class="Choice" data-num="2">New</button>                    
                </div>
            <div class="Divider Divider_1">
                <form class="card-search-form">
                    <input type="text" placeholder="Phone Number or Email" class="card_search_txt" name="customer"/>
                    <input type="button" placeholder="Phone Number or Email" class="card_search_btn" value="Find"/> 
                </form>
            <div class="card_outcomes">
                
            </div>                
            </div>
            <div class="Divider Divider_2 non_visible">
                <form method="post" action="CreateSaleServlet" class="card_neweruser">
                    <div class="register_field">
                        <input type="text" placeholder="Name" name="name" class="register_customer"/>                    
                    </div>
                    <div class="register_field">
                        <input type="text" placeholder="Surname" name="surname" class="register_customer"/>                    
                    </div>
                    <div class="register_field">
                        <input type="email" placeholder="Email" name="email" class="register_customer"/>                    
                    </div>    
                    <div class="register_field">
                        <input type="tel" name="phone" placeholder="Phone number" class="register_customer"/>                    
                    </div>                    
                    <input type="hidden" name="method" value="card"/>
                    <input class="add_customer" type="submit" value="Add Customer"/>
                </form>                
            </div>                  
            </div>  
            
        </div>           
            
        <header>
            <h1>CAROL'S BOUTIQUE</h1>
        </header>
        <div class="flex-container">
            <div class="use">
                <div class="paste-button">
                <button class="button">PK </button>
                <div class="dropdown-content">
                    <a id="top" href="cashierWel.jsp">Go back to previous page</a>
                    <a id="middle" href="index.jsp">Logout</a>

                </div>
                </div>
            </div>
            <div class="item-select">Select items of your choice</div>
        </div>

        <div class="window">
            <div class="contain" id="id">
                <div class="itemList">
                    <table>
                        <%for(int i=0;i<products.size();++i){%>
                            <%if(i%5==0){%>
                            <tr>
                                <%for(int j=i;j<i+5;++j){%>
                                    <%if(j<products.size()){%>
                                        <td class="product">
                                            <img class="product-img" src="Images/products/text.jpg"/>
                                            <div class="count count_visible" id="<%=products.get(j).getId()%>"></div>
                                            <div class="add add_<%=products.get(j).getId()%>" data-product="<%=products.get(j).getId()%>" data-price="<%=products.get(j).getPrice()%>">+</div>
                                            <div class="minus minus_<%=products.get(j).getId()%>" data-product="<%=products.get(j).getId()%>" data-price="<%=products.get(j).getPrice()%>">-</div>
                                        </td>
                            <div></div>
                                    <%}else{%>
                                    <%}%>
                                <%}%>  
                            </tr>
                            
                        <%}}%>
                    </table>
                </div>
                <div class="recipt">
                    <a href="scanItem.jsp" class="scan"><div class="Discount">
                        Scan items
                    </div></a>
                    <div class="Total">
                        <button class="payment">Cash</button>    
                        <button class="card">Card</button>
                        <button class="laybuy sale">Laybuy</button>
                        <a href="ProductsServlet" class="cancel">Cancel</a>
                    </div>
                </div>
            </div>
            <div class="msg">
                R 0.00
            </div>
            
        </div>
                    <script src="scripts/plusminus.js"></script>
                    <script src="scripts/laybuy.js"></script>
                    <script src="scripts/customers.js"></script>
    </body>
</html>
