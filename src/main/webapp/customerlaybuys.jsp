<%-- 
    Document   : customerlaybuys
    Created on : 29 Jul 2024, 7:26:44 PM
    Author     : User
--%>

<%@page import="Customer.Model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    
    Customer user = (Customer)request.getAttribute("customer");
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

                height: 70vh;
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
        
        .purchase {
            text-decoration: none;
            
        }
        
        .flex-container {
            display: flex;
            align-items: center;
            justify-content: space-between;       
        }
        
        .customer_laybuys {
            flex: 1;
            text-align: center;
            letter-spacing: 1.6px;
            font-size: 20px;
        }
        
        .transact {
            flex: 1;
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
                <a id="middle" href="index.jsp">Logout</a>
            </div>
            </div>
        </div>
            <div class="customer_laybuys">Customers Laybuys</div>
        </div>

    <div>
        <div class="search_container">
            <form class="search_form" method="GET" action="LaybuyServlet">
                <input type="text" placeholder="Provide phone number or email" class="search-txt" name="detail"/>
                <input type="submit" placeholder="Phone Number or Email" class="search-btn" value="Search Laybuys"/>
            </form>
        </div>
        <div class="dataContainer invisible-scrollbar">
            <!-- Data will be dynamically updated here -->
            

        </div>        
    </div>

    <script >
        //type="text/javascript"
        const container = document.querySelector(".dataContainer");

        function fetchData(){
            const parameter = window.location.search;
            const urlParams = new URLSearchParams(parameter);
            const detail = urlParams.get('detail');
            
            const sentdata = { customer: detail};
            fetch('UpdateUserServlet',{
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(sentdata)                
            
            }).then(response => {
                    
                if(!response.ok){
                    throw new Error('Network response was not ok');
                }
                return response.json();
            }).then(data=>{
                container.innerHTML = '';
                data.forEach((item)=>{
                let element ='<div class="order">'
                +'<div class="transact">R '+item.total+'.00</div>'+
                '<div class="transact">'+item.status+'</div>'+
                '<a class="purchase" href=CustomerByOrderServlet?order='+item.id+'>Payment</a></div>';
                    container.insertAdjacentHTML('beforeend',element);
                });
            }).catch(error => {
                console.error('There was a problem',error);
            });
        }
        
        setInterval(fetchData,1000);
        fetchData();

    </script>           
    </body>
 
</html>
