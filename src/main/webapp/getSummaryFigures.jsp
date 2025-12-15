<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Get Summary Figures</title>
    <style>
        body {
            font-family: 'Source Sans Pro', sans-serif;
            background-color: #f5f5f5;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
        }

        form {
            background: #6a1b9a; /* Purple background color */
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.2);
            padding: 20px;
            max-width: 600px;
            width: 100%;
            color: #fff; /* White text color for better readability */
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #fff; /* White text color for labels */
        }

        select, input[type="number"] {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #ab47bc; /* Lighter purple color */
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            width: 100%;
        }

        input[type="submit"]:hover {
            background-color: #8e24aa; /* Darker purple color on hover */
        }
    </style>
</head>
<body>
<div>
    <h1>Select Report Type and Enter Details</h1>
    <form action="ReportServlet" method="post">
        <label for="reportType">Report Type:</label>
        <select id="reportType" name="reportType" onchange="showFields()">
            <option value="topRatedStores">Top Rated Stores</option>
            <option value="topSellingProducts">Top Selling Products</option>
            <option value="leastPerformingStores">Least Performing Stores</option>
        </select><br>

        <div id="extraFields">
            <!-- Extra fields that change based on the report type will appear here -->
        </div>

        <input type="submit" value="Generate Report">
    </form>
</div>

<script>
    function showFields() {
        var reportType = document.getElementById("reportType").value;
        var extraFields = document.getElementById("extraFields");

        // Clear extra fields
        extraFields.innerHTML = '';

        if (reportType === 'topRatedStores') {
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
        } else if (reportType === 'topSellingProducts') {
            extraFields.innerHTML += '<label for="limit">Number of Results:</label><input type="number" id="limit" name="limit" min="1" max="10"><br>';
        } else if (reportType === 'leastPerformingStores') {
            // No additional fields for this report type
        }
    }
</script>
</body>
</html>
