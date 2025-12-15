<%-- 
    Document   : customerSale
    Created on : 31 Jul 2024, 11:15:53 AM
    Author     : User
--%>

<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="Item.Model.Item"%>
<%@page import="java.util.List"%>
<%@page import="Customer.Model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Customer customer = (Customer)request.getAttribute("customer");
    List<Item> items = (List<Item>)request.getAttribute("items");
    double price = (Double)request.getAttribute("price");
    String order = (String)request.getAttribute("order");
    NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "ZA"));
    String formattedAmount = formatter.format(price);
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

            .dataContainer,.refundContainer {

                height: 60vh;
                display: flex;
                flex-direction: column;
                padding: 10px 25px;
                overflow-y: scroll;
                padding-bottom: 40px;
            }
            
            .refundContainer {
                padding-top: 30px;
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
        
        .item ,.refund-item{
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
        
        .purchase {
            text-decoration: none;
            display: inline-block;
            
        }
        
        .flex_item {
            flex: 1;
            justify-content: flex-start;
        }
        
        .link_item {
            display: flex;
            justify-content: flex-end;  
            background-color: grey; 
        }
        
        .container {
            display: flex;
            
        }
        
        .refund_section {
           
            flex: 2;
            margin-right: 30px;
            box-shadow: 0px 3px 6px 0px rgba(0,0,0,0.2);
            margin-left: 10px;
            background-color: #a751f0;
        }
        
        .receipt {
            flex: 3;
            position: relative;
            margin-right: 30px;
            box-shadow: 0px 3px 6px 0px rgba(0,0,0,0.2);
            margin-left: 10px;
            background-color: #cd9bf7;
        }
        
        .receipt_text {
            padding-left: 24px;
            margin-top: 20px;
            margin-bottom: 10px;
        }
        
        .instruct {
            padding-left: 24px;
            font-size: 14px;
            color: #888;
            margin-bottom: 20px;
        
        }
        
        .instruct_1 {
            opacity: 0;
        }
        
        .total ,.process{
            margin-left: 20px;
            margin-bottom: 20px;
        }
        
        .total {
            font-weight: 700;
            font-size: 20px;            
        }
        
        .process {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-right: 20px;
        }
        
        .refund_flag {
            border-top: 0.5px solid #000;
            padding-top: 5px;
            font-size: 12px;
            
        }
        
        .hide {
            display: none;
        }
        
        .refund-item:not(:last-child) {
            margin-bottom: 10px;
        }
        
        .flex-div{
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        
        .item_exchange {
            margin-right: 30px;
            text-decoration: none;
            color: #410375;
            background-color: #E3D8FF;
            padding: 12px 20px;
            box-shadow: rgba(60, 64, 67, 0.3) 0px 1px 2px 0px, rgba(60, 64, 67, 0.15) 0px 1px 3px 1px; 
            animation: glowAnimation 1.5s ease-in-out infinite;
        }
        
        .item_exchange:hover {
            color: #1a012f;
        }
        
        .heading {
            font-size: 30px;
            letter-spacing: 3px;
        }

        .refund_total {
            font-weight: 700;
            font-size: 20px;   
        }
        
        .refund_btn {
            background-color: #ffccd5;
            text-decoration: none;
            color: #0d0117;
            padding: 10px 20px;
        }
        
        @keyframes glowAnimation {
            0% {
                box-shadow: 0 0 5px #a751f0, 0 0 5px #00ff00, 0 0 15px #d9b4f9, 0 0 20px #d9b4f9;
            }
            50% {
                box-shadow: 0 0 10px #a751f0, 0 0 10px #00ff00, 0 0 30px #d9b4f9, 0 0 40px #d9b4f9;
            }
            100% {
                box-shadow: 0 0 5px #a751f0, 0 0 5px #00ff00, 0 0 15px #d9b4f9, 0 0 20px #d9b4f9;
            }
        }

            </style>        
    </head>
    <body>
        <header>
            <h1>CAROL'S BOUTIQUE</h1>   
        </header>
        <div class="flex-div">
            <div class="use">
                <div class="paste-button">
                <button class="button">PK </button>
                <div class="dropdown-content">
                    <a id="top" href="cashierWel.jsp">Home</a>
                    <a id="middle" href="cashierRefund.jsp">Go back to previous page</a>
                    <a id="middle" href="index.jsp">Logout</a>
                </div>
                </div>

            </div>
            <div class="heading">Refund items</div>
            <a class="item_exchange" href="OrderItemServlet?customer=<%=customer.getId()%>&order=<%=order%>">Item/s Exchange</a>            
        </div>
        <div class='container'>
            <div class='receipt'>
                <div class='receipt_text'>Items</div>
                <div class='instruct'>Tap item to refund/remove</div>
                <div class="dataContainer invisible-scrollbar">  
                    <%for(Item item:items){%>
                    <div class="item" data-name="<%=item.getName()%>" data-price="<%=item.getPrice()%>" data-product="<%=item.getProduct()%>" data-id=<%=item.getItemid()%>>
                        <div class="order" >
                            <div class="flex_item"><%=formatter.format(item.getPrice())%></div>
                            <div class="flex_item"><%=item.getName()%></div>
                        </div>
                        <div class="refund_flag refund_<%=item.getItemid()%> hide">refund</div>    
                    </div>
                   
                    <%}%>
                </div> 
                <div class='total current_total'><%="Total: "+formatter.format(price)%></div>
            </div>
            <div class='refund_section'>
                <div class='receipt_text'>Refund Items</div>
                <div class='instruct_1'>Tap item to cancel refund</div>
                <div class="refundContainer invisible-scrollbar">
                    <!-- Data will be dynamically updated here -->
                    

                </div>
                <div class="process">
                    <div class='refund_total'>Refund Total: <span class="refund_value">R 0,00</span></div>
                    <a href="RefundServlet" class="refund_btn hide">Refund</a>
                </div> 
            </div>
        </div>        
    <script>
        
        const container = document.querySelector(".dataContainer");
        const total = document.querySelector(".current_total");
        const refund = document.querySelector(".refund_total");
        const refund_value = document.querySelector(".refund_value");
        const refundContainer = document.querySelector(".refundContainer");
        const refund_btn = document.querySelector(".refund_btn");
        const formatter = new Intl.NumberFormat('en-ZA', {
            style: 'currency',
            currency: 'ZAR',
            minimumFractionDigits: 2, // Always show two decimal places
            maximumFractionDigits: 2, // No more than two decimal places
        });
        
        let flag;
        let product;
        let price;
        
        container.addEventListener("click",(e)=>{
            let target = e.target.closest('.item');
            if(target){
               
            flag = target.dataset.id;
            product = target.dataset.product;
            price = target.dataset.price;
            name = target.dataset.name;
            const sentdata = { product: product ,item: flag,price : price,name : name};
            fetch('ReverseServlet', {
                method: 'POST',
                    headers: {
                                'Content-Type': 'application/json'
                            },
                            body: JSON.stringify(sentdata)
                        })
                        .then(response => {
                            if (!response.ok) {
                                throw new Error('Network response was not ok');
                            }
                                return response.json(); 
                            })
                        .then(data => {
                                
                            console.log(data.price);
                            refund_value.innerHTML = formatter.format(data.price);
                            if(data.remove==="false"){
                                let newitem = '<div class="refund-item refund-'+data.id+'">'+
                                                '<div class="order" >'+
                                                   '<div class="flex_item">'+formatter.format(data.priceresponse)+'</div>'+
                                                '<div class="flex_item">'+data.name+'</div></div></div>';
                                refundContainer.insertAdjacentHTML('beforeend',newitem);
                                console.log(refundContainer);                                
                            }
                            if(data.remove==="true"){
                                let remove_item = document.querySelector('.refund-'+data.id).remove();
                            }
                            
                            if(data.count === 0){
                                refund_btn.classList.add('hide');
                            }
                            
                            if(data.count>0){
                                refund_btn.classList.remove('hide');
                            }

                            let child = target.querySelector(".refund_flag");
                            child.classList.toggle('hide');
                            
                        })
                        .catch(error => {
                            console.error('Error:', error);
                        });                 
                


            }
        });
        

    </script>        
    </body>
</html>
