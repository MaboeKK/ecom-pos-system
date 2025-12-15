<%-- 
    Document   : scan
    Created on : 14 Jul 2024, 10:08:50 AM
    Author     : User
--%>
<%@page import="User.Model.User"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Scan Items</title>
        <style>
            
            body{
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                background-color: #E3D8FF;
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
            

            .scan_container{
                width: 100%;
                height: calc(100vh - 110px);               
                display: flex;
                
            }
            
            .scanner_div{

                flex: 1;
                padding: 20px;
                display: flex;
                align-items: center;
                justify-content: center;
            }
            
            .items_div{
                background-color: #FFF;;
                flex: 1;
                padding: 20px;                
            }
            
            .camera{
                width: 100%;
                height: 300px;
                overflow: hidden;
            
            }
            
            .items{
                width: 100%;
                height: 80vh;
                position: relative;
                padding: 5px;
            }
            
            .total{
                width: 360px;
                height: 60px;
                background-color: #E3D8FF;
                position: absolute;
                left: 0;
                bottom: 30px;
                display: flex;
                align-items: center;
                padding-left: 5px;
                justify-content: space-between;
                padding-right: 5px;
                color: #010003;
            }
            
            .price{
                font-size: 34px;
                font-weight: 600;
                letter-spacing: 1px;
            }
            
            .item_price {
                font-weight: bold;
            }
            
            .sale{
                position: absolute;
                bottom: 30px;
                right: 4px;
                background-color: hotpink;
                padding: 16px 20px;
                display: inline-block;
                border: none;
                font-size: 25px;
                color: #000;
                cursor: pointer;
                
            }
            
            .item_list{
                list-style: none;
                display: flex;
                flex-direction: column;
                align-items: stretch;
                padding: 5px 0;
                height: 360px;
                overflow-y: scroll;
                
            }
            .item{
                background-color: #9759F2;
                padding: 24px 12px;
                display: flex;
                justify-content: space-between;
                color: #FFF;
                letter-spacing: 1.5px;
                
            }
            
            .item:hover {
                background-color: #a2d2ff;
                color: #0d1b2a;
            }
            
            .item:not(:last-child){
                margin-bottom: 10px;
            }
            
            .invisible-scrollbar::-webkit-scrollbar {
                display: none;
            }
            
            .name{
                margin-bottom: 10px;
            }
            
            .remove_container{
                width: 40px;
                display: flex;
                align-items: center;
                justify-content: center;
            }
            
            .name_container{
                flex: 1;
            }
            
            .remove{
                background-color: red;
                height: 4px;
                width: 20px;
                border-radius: 40px;
                cursor: pointer;
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
        .container {
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
            padding: 10px 26px;
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
        button {
            cursor: pointer;
        }
        
        .laybuy-btn:not(:last-child){
            margin-right: 10px;
        }
   
        .toast {
            display: none;
            background-color: #ef233c;
            margin: 10px;
            padding: 10px;
            border-radius: 5px;
            color: #fff;
            background-color: #333;
            font-size: 14px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
            position: fixed;
            z-index: 700;
            right: 120px;
            top: 80px;
            min-width: 300px;
        }

        .toast.show {
            display: block;
        }
        
        .flex-container{
            display: flex;
            align-items: center;
            justify-content: space-between;
            
        }

        .instruct {
            flex: 1;
            margin-left: 160px;
            font-size: 20px;
            padding-top: 20px;
            letter-spacing: 1.4px;
            font-weight: 500;
        }
         
        </style>          
    </head>
    <body>
        
        <div class="overlay non_visible">
         
        </div>
       
        <div class="container non_visible">
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
                <form method="post" action="CreateSaleServlet" class="newuser">
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
                    <div class="register_field">
                        <select name="method" class="register_customer">
                            <option value="cash">Cash</option>
                            <option value="card">Card</option>
                            <option value="laybuy">Laybuy</option>
                        </select>
                    </div>                      
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
                    <a id="top" href="cashierWel.jsp">Home</a>
                    <a id="top" href="ProductsServlet">Go back to previous page</a>
                    <a id="middle" href="index.jsp">Logout</a>
                </div>
                </div>
            </div>
            <div class="instruct">Scanning item/s barcode</div>
        </div>

        <main class="scan_container">
            <div class="scanner_div">
                <div id="camera" class="camera">
                    
                </div>                
            </div>
            <div class="items_div">
                <div class="items">
                    <div class="total"><span>Total :</span><span class="price"></span></div>
          
                    <button class="sale">Make Purchase</button>
                    <ul class="item_list invisible-scrollbar">                        
                         
                    </ul>
                   
                </div>

            </div>
        </main>
        <script src="scripts/quagga.min.js"></script>
        <script src="scripts/script.js"></script>
        <script src="scripts/itemremove.js"></script>
        <script src="scripts/scansales.js"></script>
    </body>
</html>

