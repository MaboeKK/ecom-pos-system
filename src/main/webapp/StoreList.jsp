<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="Connection.DbConn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Store List</title>
    <link href="Stylepages/storeList.css" rel="stylesheet" type="text/css"/>
    <style>
        .store-list {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-around;
        }
        .index-cat-box {
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin: 10px;
            padding: 20px;
            width: calc(25% - 40px);
            box-sizing: border-box;
            text-align: center;
        }
        .index-cat-box .Znumber {
            font-weight: bold;
        }
        .index-cat-box h2 {
            font-size: 1.5em;
            margin: 10px 0;
        }
        .index-cat-box p {
            margin: 5px 0;
        }
        .index-cat-box img {
            max-width: 100px;
            margin-bottom: 20px;
        }
        .index-cat-box:hover {
            letter-spacing: 3px;
            background-color: #fff;
            color: hsl(0, 0%, 100%);
            box-shadow: rgb(93 24 220) 0px 7px 29px 0px;
        }
        .button-group {
            margin-top: 15px;
        }
        .button-group button {
            padding: 10px 15px;
            margin: 5px;
            border: none;
            border-radius: 5px;
            color: #fff;
            cursor: pointer;
        }
        .button-group .update-button {
            background-color: #007bff;
        }
        .button-group .delete-button {
            background-color: #dc3545;
        }
    </style>
    <script>
        function confirmDelete(form) {
            if (confirm("Are you sure you want to delete this store?")) {
                form.submit(); // Submit the form if user confirms
            } else {
                alert("No action was taken.");
            }
        }
    </script>
</head>
<body>
<header>
    <h1>CAROL'S BOUTIQUE - Store List</h1>
</header>
<div class="store-list">
    <%
        try {
            Connection conn = DbConn.getConnection();
            String query = "SELECT s.store_id, s.province, s.city, s.address, " +
                    "(SELECT COUNT(*) FROM user u WHERE u.store_id = s.store_id) AS totalEmployees, " +
                    "(SELECT COUNT(*) FROM user u WHERE u.store_id = s.store_id AND u.ACCESS_LEVEL = 1) AS cashiers, " +
                    "(SELECT COUNT(*) FROM user u WHERE u.store_id = s.store_id AND u.ACCESS_LEVEL = 2) AS floorManagers, " +
                    "(SELECT CONCAT(u.FIRSTNAME, ' ', u.SURNAME) FROM user u WHERE u.store_id = s.store_id AND u.ACCESS_LEVEL = 3) AS storeManager " +
                    "FROM stores s";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int storeId = rs.getInt("store_id");
                String province = rs.getString("province");
                String city = rs.getString("city");
                String address = rs.getString("address");
                int totalEmployees = rs.getInt("totalEmployees");
                int cashiers = rs.getInt("cashiers");
                int floorManagers = rs.getInt("floorManagers");
                String storeManager = rs.getString("storeManager");
    %>
    <div class="index-cat-box">
        <img src="Images/manager/store.png" alt="Store Image">
        <div class="Znumber">Store No. <%= storeId %></div>
        <h2>Store Information</h2>
        <p>Province: <%= province %></p>
        <p>City: <%= city %></p>
        <p>Address: <%= address %></p>
        <p>No of Employees: <%= totalEmployees %></p>
        <p>Cashiers: <%= cashiers %></p>
        <p>Floor Managers: <%= floorManagers %></p>
        <p>Store Manager: <%= storeManager != null ? storeManager : "None" %></p>
        <div class="button-group">
            <form action="UpdateStoreServlet" method="get" style="display:inline;">
                <input type="hidden" name="store_id" value="<%= storeId %>"/>
                <button type="submit" class="update-button">Update</button>
            </form>
            <form action="DeleteStoreServlet" method="post" style="display:inline;" onsubmit="confirmDelete(this); return false;">
                <input type="hidden" name="store_id" value="<%= storeId %>"/>
                <button type="submit" class="delete-button">Delete</button>
            </form>
        </div>
    </div>
    <%
        }
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    %>
    <p>Error retrieving store data.</p>
    <%
        }
    %>
</div>
<footer>
    <h2>© Vought Softwares</h2>
</footer>
</body>
</html>
