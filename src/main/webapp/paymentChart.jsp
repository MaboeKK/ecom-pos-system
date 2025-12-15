<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Payment Monthly Chart</title>
</head>
<body>
<h1>Monthly Payments Chart</h1>
<img src="paymentChart" alt="Monthly Payments Chart">
<br>
<form action="paymentChart" method="get">
    <label>Year:</label>
    <input type="number" name="year" value="<%= request.getParameter("year") %>">
    <label>Month:</label>
    <input type="number" name="month" value="<%= request.getParameter("month") %>">
    <input type="submit" value="Update Chart">
</form>
</body>
</html>