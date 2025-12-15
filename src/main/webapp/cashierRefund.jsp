<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Display Updated Data List with AJAX</title>
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
                padding: 10px 160px;
                overflow-y: scroll;
                
            }

            
            .invisible-scrollbar::-webkit-scrollbar {
                display: none;
            }
            
            .search-btn {
                padding: 12px 20px;
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
            box-shadow: rgba(0, 0, 0, 0.05) 0px 0px 0px 1px;
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
            max-width: 480px;
            margin: 50px auto;
        }
        
        .order {
            
            padding: 20px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            box-shadow: 0px 3px 6px 0px rgba(0,0,0,0.2); 
            background-color: #e6cdfb;
            color: #0d0117;
            font-weight: 700;
        }
        
        .order:hover {
            background-color: #0d0117;
            color: #f2e6fd;            
        }
        
        .order:hover .purchase{
            color: #ffffff;
            animation: blinkAnimation 1s infinite;
        }
        
        .order:not(:last-child){
            margin-bottom: 16px;
            
        }
       
        
        .flex_item {
           
            justify-content: flex-start;
        }
        
        .flex-total,.flex-create {
            flex: 1;
        }
        
        .link{
            display: flex;
            justify-content: flex-end;
          
        }
        
        .opening_text{
            color: #777;
            text-align: center;
            padding-top: 40px;
            font-size: 30px;
            font-weight: 600;
            letter-spacing: 2px;
            opacity: 0.6;
        }
        
        .customer {
            margin-right: 20px;
            color: #0d0117;
        }
        
        .success{
            text-align: center;
            font-weight: 600;
            font-size: 18px;
            letter-spacing: 1.4px;
        }
        
        .hide{
            display: none;
        }
        
        .purchase {
            text-decoration: none;
            flex-basis: 80px;
            align-self: stretch;
            text-align: end;
            color: #0d0117;
        }
        
        .span {

            font-size: 18px;
            font-weight: 600;
            letter-spacing: 1.2px;
        }
        
        .flex-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        @keyframes blinkAnimation {
            0% {
                opacity: 1;
                filter: brightness(1);
                transform: scale(1);
                color: #fff;
            }
            50% {
                opacity: 0.5;
                filter: brightness(1.8);
                transform: scale(1.1);
                color: #fff; /* Change text color */
            }
            100% {
                opacity: 1;
                filter: brightness(1);
                transform: scale(1);
                color: #fff;
            }
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
    <div class='customer hide'>
          
    </div>        
    </div>

    <div class='success'>Customer's successful transactions</div>
    

    <div class>
        <div class="search_container">
            <form class="search_form" method="GET" action="LaybuyServlet">
                <input type="text" required placeholder="Provide phone number or email" class="search-txt" name="detail"/>
                <input type="submit" placeholder="Phone Number or Email" class="search-btn" value="Search Customer"/>
            </form>
        </div>
        <div class="dataContainer invisible-scrollbar">
            <!-- Data will be dynamically updated here -->
            <div class="opening_text">
                Transactions displayed here
            </div>

        </div>        
    </div>

    <script >
        //type="text/javascript"
        const container = document.querySelector(".dataContainer");
        const search = document.querySelector(".search-btn");
        const searchTxt = document.querySelector(".search-txt");
        const customer = document.querySelector(".customer");
        const formatter = new Intl.NumberFormat('en-ZA', {
            style: 'currency',
            currency: 'ZAR',
        });
        
        function fetchData(){
            const sentdata = { search: searchTxt.value};
            fetch('SaleServlet',{
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

                    data.sales.forEach((item)=>{
                    let element ='<div class="order">'
                    +'<div class="flex_item flex-total">Total : '+formatter.format(item.total)+'</div>'+
                    '<div class="flex_item flex-create">'+item.create+'</div>'+
                    
                    '<a class="purchase flex_item" href=ItemsServlet?sale='+item.id+'&customer='+data.customer.id+'>item/s</a></div></div>';
                        container.insertAdjacentHTML('beforeend',element);
                    });
                    
                    let buyer = '<span class="span">'+data.customer.name+' '+data.customer.surname+'</span>';
                    customer.innerHTML = '';
                    customer.insertAdjacentHTML('beforeend',buyer);
                    customer.classList.remove('hide');
                    
                    
                    if(data.sales.length===0){
                        
                        container.innerHTML = '';

                        let not = '<div class="opening_text">'+
                        'Customer has no successful transactions with us</div>';
                        container.insertAdjacentHTML('beforeend',not);
                        
                        if(!customer.classList.contains('hide')){
                            customer.classList.add('hide');
                        }
                    }
                


            }).catch(error => {
                console.error('There was a problem',error);
            });
        }
        
        search.addEventListener("click",(e)=>{
            e.preventDefault();
            fetchData();
            
        });

    </script>    
</body>
</html>
