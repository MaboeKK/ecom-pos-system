<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Get Summary Reports</title>
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
            <option value="topAchievingStores">Top Achieving Stores</option>
            <option value="topSellingEmployees">Top Selling Employees</option>
            <option value="storeTargets">Stores Achieving Targets</option>
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

        if (reportType === 'topAchievingStores') {
            extraFields.innerHTML += '<label for="limit">Number of Results:</label><input type="number" id="limit" name="limit" min="1" max="10"><br>';
        } else if (reportType === 'topSellingEmployees') {
            extraFields.innerHTML += '<label for="limit">Number of Results:</label><input type="number" id="limit" name="limit" min="1" max="10"><br>';
        } else if (reportType === 'storeTargets') {
            extraFields.innerHTML += `
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
        }
    }
</script>
</body>
</html>
