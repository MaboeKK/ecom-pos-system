<!DOCTYPE html>
<html>
<head>
    <title>Generate Reports</title>
</head>
<body>
<h1>Select Report Type and Enter Details</h1>
<form action="ReportServlet" method="post">
    <label for="reportType">Report Type:</label>
    <select id="reportType" name="reportType" onchange="showFields()">
        <option value="topAchievingStores">Top Achieving Stores</option>
        <option value="topRatedStores">Top Rated Stores</option>
        <option value="monthlySales">Monthly Sales Report</option>
        <option value="topSellingEmployees">Top Selling Employees</option>
        <option value="storeTargets">Stores Achieving Targets</option>
        <option value="topSellingProducts">Top Selling Products</option>
        <option value="leastPerformingStores">Least Performing Stores</option>
        <option value="productSalesReport">Product Sales Report</option>
        <option value="dailySales">Daily Sales</option>
    </select><br>

    <div id="extraFields">
        <!-- Extra fields that change based on the report type will appear here -->
    </div>

    <input type="submit" value="Generate Report">
</form>

<script>
    function showFields() {
        var reportType = document.getElementById("reportType").value;
        var extraFields = document.getElementById("extraFields");

        // Clear extra fields
        extraFields.innerHTML = '';

        if (reportType === 'topAchievingStores') {
            extraFields.innerHTML += '<label for="limit">Number of Results:</label><input type="number" id="limit" name="limit" min="1" max="10"><br>';
        } else if (reportType === 'topRatedStores') {

            extraFields.innerHTML += `
    <label for="month">Month:</label>
    <select id="month" name="month">
        <option value="01">January</option>
        <option value="02">February</option>
        <option value="03">March</option>
        <option value="04">April</option>
        <option value="05">May</option>
        <option value="06">June</option>
        <option value="07">July</option>
        <option value="08">August</option>
        <option value="09">September</option>
        <option value="10">October</option>
        <option value="11">November</option>
        <option value="12">December</option>
    </select>
    <br>
`;

            extraFields.innerHTML += '<label for="limit">Number of Results:</label><input type="number" id="limit" name="limit" min="1" max="10"><br>';
        } else if (reportType === 'monthlySales') {
            extraFields.innerHTML += `
                <label for="storeId">Select Store:</label>
                <select id="storeId" name="storeId">
                    <option value="1">Carol's Boutique - Nelson Mandela Ave, Johannesburg</option>
                    <option value="2">Carol's Boutique - Long Street, Cape Town</option>
                    <option value="3">Carol's Boutique - Marine Parade, Durban</option>
                    <option value="4">Carol's Boutique - Church Square, Pretoria</option>
                    <option value="5">Carol's Boutique - Govan Mbeki Ave, Port Elizabeth</option>
                    <option value="6">Carol's Boutique - President Brand St, Bloemfontein</option>
                    <option value="7">Carol's Boutique - Thabo Mbeki St, Polokwane</option>
                    <option value="8">Carol's Boutique - Samora Machel Dr, Nelspruit</option>
                    <option value="9">Carol's Boutique - Bultfontein Rd, Kimberley</option>
                    <option value="10">Carol's Boutique - Nelson Mandela Dr, Rustenburg</option>
                    <option value="11">Carol's Boutique - Andringa St, Stellenbosch</option>
                    <option value="12">Carol's Boutique - Langalibalele St, Pietermaritzburg</option>
                    <option value="13">Carol's Boutique - Rivonia Rd, Sandton</option>
                    <option value="14">Carol's Boutique - Oxford St, East London</option>
                    <option value="15">Carol's Boutique - Stateway, Welkom</option>
                    <option value="16">Carol's Boutique - Mphephu St, Thohoyandou</option>
                    <option value="17">Carol's Boutique - Mandela St, Witbank</option>
                    <option value="18">Carol's Boutique - Schroder St, Upington</option>
                    <option value="19">Carol's Boutique - Nelson Mandela Dr, Mafikeng</option>
                    <option value="20">Carol's Boutique - York St, George</option>
                    <option value="21">Carol's Boutique - Bullion Blvd, Richards Bay</option>
                    <option value="22">Carol's Boutique - Allandale Rd, Midrand</option>
                    <option value="23">Carol's Boutique - York Rd, Mthatha</option>
                    <option value="24">Carol's Boutique - John Voster Ave, Sasolburg</option>
                    <option value="25">Carol's Boutique - Main Rd, Giyani</option>
                    <option value="26">Carol's Boutique - Trichardt St, Secunda</option>
                    <option value="27">Carol's Boutique - Voortrekker St, Springbok</option>
                    <option value="28">Carol's Boutique - Govan Mbeki Dr, Potchefstroom</option>
                    <option value="29">Carol's Boutique - Main St, Paarl</option>
                    <option value="30">Carol's Boutique - Compensation Beach Rd, Ballito</option>
                    <option value="31">Carol's Boutique - Voortrekker Rd, Alberton</option>
                    <option value="32">Carol's Boutique - High St, Grahamstown</option>
                    <option value="33">Carol's Boutique - Muller St, Bethlehem</option>
                    <option value="34">Carol's Boutique - Mandela Dr, Lebowakgomo</option>
                    <option value="35">Carol's Boutique - Voortrekker St, Ermelo</option>
                    <option value="36">Carol's Boutique - Main Rd, Kuruman</option>
                    <option value="37">Carol's Boutique - President St, Klerksdorp</option>
                    <option value="38">Carol's Boutique - Main Rd, Knysna</option>
                    <option value="39">Carol's Boutique - Beach Rd, Amanzimtoti</option>
                    <option value="40">Carol's Boutique - Main Rd, Roodepoort</option>
                    <option value="41">Carol's Boutique - Cathcart Rd, Queenstown</option>
                </select>
                <br>
            `;
            extraFields.innerHTML += '<label for="year">Year:</label><input type="number" id="year" name="year" min="2023" max="2024"><br>';
        } else if (reportType === 'topSellingEmployees') {
            extraFields.innerHTML += '<label for="limit">Number of Results:</label><input type="number" id="limit" name="limit" min="1" max="10"><br>';
        } else if (reportType === 'storeTargets') {
            extraFields.innerHTML +=  extraFields.innerHTML += `
    <label for="month">Month:</label>
    <select id="month" name="month">
        <option value="1">January</option>
        <option value="2">February</option>
        <option value="3">March</option>
        <option value="4">April</option>
        <option value="5">May</option>
        <option value="6">June</option>
        <option value="7">July</option>
        <option value="8">August</option>
        <option value="9">September</option>
        <option value="10">October</option>
        <option value="11">November</option>
        <option value="12">December</option>
    </select>
    <br>
`;
            extraFields.innerHTML += '<label for="year">Year:</label><input type="number" id="year" name="year" min="2023" max="2024"><br>';
            extraFields.innerHTML += '<label for="target">Target Amount:</label><input type="number" id="target" name="target" step="any"><br>';
        } else if (reportType === 'topSellingProducts') {
            extraFields.innerHTML += '<label for="limit">Number of Results:</label><input type="number" id="limit" name="limit" min="1" max="10"><br>';
        } else if (reportType === 'leastPerformingStores') {
            // No additional fields for this report type
        } else if (reportType === 'productSalesReport') {
            extraFields.innerHTML += `
                <label for="product_id">Select Product:</label>
                <select id="product_id" name="product_id">
                    <option value="1">HyperFlex Performance T-Shirt</option>
                    <option value="2">Energize Active Tank</option>
                    <option value="3">Sprint Tech Running Shorts</option>
                    <option value="4">FlexFit Jogger Pants</option>
                    <option value="5">UltraBoost Running Sneakers</option>
                    <option value="6">ProActive Cross-Trainers</option>
                    <option value="7">Comfy Cotton T-Shirt</option>
                    <option value="8">Casual Sleeveless Vest</option>
                    <option value="9">RelaxFit Lounge Shorts</option>
                    <option value="10">Classic Fleece Sweat Pants</option>
                    <option value="11">Everyday Canvas Sneakers</option>
                    <option value="12">Retro Style Casual Trainers</option>
                </select>
                <br>
            `;
        } else if (reportType === 'dailySales') {
            extraFields.innerHTML += `
                <label for="storeId">Select Store:</label>
                <select id="storeId" name="storeId">
                    <option value="1">Carol's Boutique - Nelson Mandela Ave, Johannesburg</option>
                    <option value="2">Carol's Boutique - Long Street, Cape Town</option>
                    <option value="3">Carol's Boutique - Marine Parade, Durban</option>
                    <option value="4">Carol's Boutique - Church Square, Pretoria</option>
                    <option value="5">Carol's Boutique - Govan Mbeki Ave, Port Elizabeth</option>
                    <option value="6">Carol's Boutique - President Brand St, Bloemfontein</option>
                    <option value="7">Carol's Boutique - Thabo Mbeki St, Polokwane</option>
                    <option value="8">Carol's Boutique - Samora Machel Dr, Nelspruit</option>
                    <option value="9">Carol's Boutique - Bultfontein Rd, Kimberley</option>
                    <option value="10">Carol's Boutique - Nelson Mandela Dr, Rustenburg</option>
                    <option value="11">Carol's Boutique - Andringa St, Stellenbosch</option>
                    <option value="12">Carol's Boutique - Langalibalele St, Pietermaritzburg</option>
                    <option value="13">Carol's Boutique - Rivonia Rd, Sandton</option>
                    <option value="14">Carol's Boutique - Oxford St, East London</option>
                    <option value="15">Carol's Boutique - Stateway, Welkom</option>
                    <option value="16">Carol's Boutique - Mphephu St, Thohoyandou</option>
                    <option value="17">Carol's Boutique - Mandela St, Witbank</option>
                    <option value="18">Carol's Boutique - Schroder St, Upington</option>
                    <option value="19">Carol's Boutique - Nelson Mandela Dr, Mafikeng</option>
                    <option value="20">Carol's Boutique - York St, George</option>
                    <option value="21">Carol's Boutique - Bullion Blvd, Richards Bay</option>
                    <option value="22">Carol's Boutique - Allandale Rd, Midrand</option>
                    <option value="23">Carol's Boutique - York Rd, Mthatha</option>
                    <option value="24">Carol's Boutique - John Voster Ave, Sasolburg</option>
                    <option value="25">Carol's Boutique - Main Rd, Giyani</option>
                    <option value="26">Carol's Boutique - Trichardt St, Secunda</option>
                    <option value="27">Carol's Boutique - Voortrekker St, Springbok</option>
                    <option value="28">Carol's Boutique - Govan Mbeki Dr, Potchefstroom</option>
                    <option value="29">Carol's Boutique - Main St, Paarl</option>
                    <option value="30">Carol's Boutique - Compensation Beach Rd, Ballito</option>
                    <option value="31">Carol's Boutique - Voortrekker Rd, Alberton</option>
                    <option value="32">Carol's Boutique - High St, Grahamstown</option>
                    <option value="33">Carol's Boutique - Muller St, Bethlehem</option>
                    <option value="34">Carol's Boutique - Mandela Dr, Lebowakgomo</option>
                    <option value="35">Carol's Boutique - Voortrekker St, Ermelo</option>
                    <option value="36">Carol's Boutique - Main Rd, Kuruman</option>
                    <option value="37">Carol's Boutique - President St, Klerksdorp</option>
                    <option value="38">Carol's Boutique - Main Rd, Knysna</option>
                    <option value="39">Carol's Boutique - Beach Rd, Amanzimtoti</option>
                    <option value="40">Carol's Boutique - Main Rd, Roodepoort</option>
                    <option value="41">Carol's Boutique - Cathcart Rd, Queenstown</option>
                </select>
                <br>
            `;
            extraFields.innerHTML += '<label for="target">Target Amount:</label><input type="number" id="target" name="target" step="any"><br>';
        }
    }
</script>

</body>
</html>
