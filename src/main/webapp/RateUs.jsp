<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="Stylepages/rateing.css" rel="stylesheet" type="text/css"/>
    <link href='https://fonts.googleapis.com/css?family=Advent Pro' rel='stylesheet'>
    <title>Rate Us</title>
</head>
<style>
    .star
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
    }
    .myFoot h2
    {
        text-align: right;
        color: #8106EA;
        margin-top: 150px;
        font-family: 'Advent Pro';
    }
</style>
<header>
    <h1>CAROL'S BOUTIQUE</h1>
</header>
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
<div class="star">
    <div class="form-container">
        <h2>Rate Us</h2></p>
<form class="form" method="post" action="submitRating">
    <input type="hidden" name="customer_id" value="${param.customer_id}">
    <input type="hidden" name="product_id" value="${param.product_id}">

    <label for="rating">Rating</label>
    <div class="rating">
        <input value="5" name="rate" id="star5" type="radio">
        <label title="text" for="star5"></label>
        <input value="4" name="rate" id="star4" type="radio">
        <label title="text" for="star4"></label>
        <input value="3" name="rate" id="star3" type="radio" checked="">
        <label title="text" for="star3"></label>
        <input value="2" name="rate" id="star2" type="radio">
        <label title="text" for="star2"></label>
        <input value="1" name="rate" id="star1" type="radio">
        <label title="text" for="star1"></label>
    </div>
    input class="form-btn" name="submit" type="submit" value="Create">
</form>
    <label for="comments">Additional Comments</label>
    <textarea name="comments" placeholder="Enter your comments (max 400 characters)" maxlength="400"></textarea>

        <input class="form-btn" name="submit" type="submit" value="Submit">
    </div>

</div>

</body>
</html>
