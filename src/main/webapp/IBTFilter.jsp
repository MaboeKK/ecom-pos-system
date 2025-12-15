<%-- 
    Document   : IBTFilter
    Created on : Jul 27, 2024, 2:27:18 PM
    Author     : Tshiamo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="Stylepages/recive.css" rel="stylesheet" type="text/css"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
     <style>
        .form-container {
  width: 550px;
  height: 400px;
  background-color: #fff;
  box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
  border-radius: 10px;
  box-sizing: border-box;
  padding: 20px 30px;
  margin-left: 38%;
  margin-top: 10%;
}
        .recive
        {
            align-content: center;
            align-items: center;
            
            
        }
        .rating
        {
            align-self: center;
            align-content: center;
            align-items: center;
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
             font-family: 'Cinzel Decorative';
        }
        .myFoot h2
        {
            text-align: right;
            color: #8106EA;
            margin-top: 150px;
            font-family: 'Advent Pro';
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
    <header>
        <h1>CAROL'S BOUTIQUE</h1>
    </header>
   <!-- /*<div class="use">
        <div class="paste-button">
        <button class="button">PK </button>
        <div class="dropdown-content">
            <a id="top" href="cashierWel.jsp">Go back to previous page</a>
            <a id="middle" href="index.jsp">Logout</a>
          
        </div>
        </div>
    </div>*/-->
       <div class="use">
        <div class="paste-button">
        <button class="button">PK </button>
        <div class="dropdown-content">
            <a id="top" href="cashierWel.jsp">Go back to previous page</a>
            <a id="middle" href="index.jsp">Logout</a>
          
        </div>
        </div>
    </div>
    <body>
       
        <div class="recive">
            <div class="form-container">
                <p class="title"><h2>Customer Feedback</h2></p>

            <form class="form" method="post" action="IBT">
                        <div class="search_container">
                            <form class="search_form" method="post" action="IBT">
                                <input type="text" placeholder="Enter Item number" class="search-txt" name="ItemName"/>
                                <input class="form-btn" name="submit" type="submit" value="next">
                            </form>
                        </div>
            </form>
             </div>
            
        </div>
    </body>
    <footer class="myFoot">
        <h2>© Vought Softwares</h2>
    </footer>

</html>
