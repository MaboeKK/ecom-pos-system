<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>Report Results</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery"></script>
    <script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@2"></script>

    <!-- ChartDataLabels plugin -->
    <script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@2.0.0/dist/chartjs-plugin-datalabels.min.js"></script>
    <!-- Ensure only one version of the plugin -->

    <style>
        .chart-container {
            width: 45%; /* Adjusted for better side-by-side layout */
            height: 300px;
            display: inline-block; /* Allows the charts to be side by side */
            vertical-align: top; /* Aligns the charts at the top */
            box-sizing: border-box; /* Ensures padding and borders are included in the total width and height */
        }

        #pieChart {
            height: 300px; /* Adjusted height for the pie chart */
            width: 300px;  /* Adjusted width for the pie chart */
        }

        #pdfButton {
            margin-top: 20px; /* Add margin for spacing */
        }
    </style>
</head>
<body>
<h1>Report Results</h1>
<c:set var="reportType" value="${param.reportType}" />

<c:choose>
    <c:when test="${not empty reportData and reportData[0] != null}">
        <div class="chart-container">
            <canvas id="barChart"></canvas>
        </div>
        <div class="chart-container">
            <canvas id="pieChart"></canvas>
        </div>

        <c:choose>
            <c:when test="${reportType eq 'monthlySales'}">
                <script>
                    // Declare JavaScript arrays
                    var monthlyLabels = [];
                    var monthlySalesData = [];

                    // Iterate over reportData and push values to JavaScript arrays
                    <c:forEach var="row" items="${reportData}">
                    // Use JSTL's escapeXml function to escape special characters in strings
                    monthlyLabels.push('${fn:escapeXml(row.month)}');
                    // Ensure that the value is a number; if not, use parseFloat() or a similar method
                    monthlySalesData.push(${row.total_sales});
                    </c:forEach>
                </script>
                <!-- Ensure this script is included after the variables are defined -->
                <script src="reportsJS/monthlySales.js"></script>
                <button id="queueButton" onclick="queueReport('monthlySales')">Add to PDF QUE</button>
            </c:when>

            <c:when test="${reportType eq 'topSellingEmployees'}">
                <script>
                    var topSellingEmployeesLabels = [];
                    var topSellingEmployeesData = [];
                    <c:forEach var="row" items="${reportData}">
                    topSellingEmployeesLabels.push('${fn:escapeXml(row.FIRSTNAME)} ${fn:escapeXml(row.SURNAME)}');
                    topSellingEmployeesData.push(${row.total_sales});
                    </c:forEach>
                </script>
                <script src="reportsJS/topSellingEmployees.js"></script>
                <button id="queueButton" onclick="queueReport('topSellingEmployees')">Add to PDF QUE</button>

            </c:when>
            <c:when test="${reportType eq 'topAchievingStores'}">
                <script>
                    var topAchievingLabels = [];
                    var topAchievingData = [];
                    <c:forEach var="row" items="${reportData}">
                    topAchievingLabels.push('Store Number ' + '${fn:escapeXml(row.store_id)}'); // Add prefix to label
                    topAchievingData.push(${row.total_sales});
                    </c:forEach>
                </script>
                <script src="reportsJS/topAchievingStores.js"></script>
                <button id="queueButton" onclick="queueReport('topAchievingStores')">Add to PDF QUE</button>
            </c:when>
            <c:when test="${reportType eq 'storeTargets'}">
                <script>
                    // Initialize JavaScript arrays for the chart data
                    var storeTargetsLabels = [];
                    var storeTargetsData = [];

                    // Populate the arrays with data from the server-side
                    <c:forEach var="row" items="${reportData}">
                    // Escape labels to handle special characters
                    storeTargetsLabels.push('${fn:escapeXml(row.store_name)}');
                    // Ensure data is treated as a number
                    storeTargetsData.push(${row.total_sales});
                    </c:forEach>
                </script>
                <!-- Include the external script that will use these variables -->
                <script src="reportsJS/storeTargets.js"></script>
                <button id="queueButton" onclick="queueReport('storeTargets')">Add to PDF QUE</button>

            </c:when>
            <c:when test="${reportType eq 'topSellingProducts'}">
                <script>
                    var topSellingProductsLabels = [];
                    var topSellingProductsData = [];
                    <c:forEach var="row" items="${reportData}">
                    topSellingProductsLabels.push('${fn:escapeXml(row.product_name)}');
                    topSellingProductsData.push(${row.total_sales});
                    </c:forEach>
                </script>
                <script src="reportsJS/topSellingProducts.js"></script>
                <button id="queueButton" onclick="queueReport('topSellingProducts')">Add to PDF QUE</button>
            </c:when>
            <c:when test="${reportType eq 'leastPerformingStores'}">
                <script>
                    var leastPerformingStoresLabels = [];
                    var leastPerformingStoresData = [];
                    <c:forEach var="row" items="${reportData}">
                    leastPerformingStoresLabels.push('${fn:escapeXml(row.store_name)}');
                    leastPerformingStoresData.push(${row.average_sales});
                    </c:forEach>
                </script>
                <script src="reportsJS/leastPerformingStores.js"></script>
                <button id="queueButton" onclick="queueReport('leastPerformingStores')">Add to PDF QUE</button>
            </c:when>
            <c:when test="${reportType eq 'productSalesReport'}">
                <script>
                    var productSalesReportLabels = [];
                    var productSalesReportData = [];
                    <c:forEach var="row" items="${reportData}">
                    productSalesReportLabels.push('Store Number ' + '${fn:escapeXml(row.store_id)}'); // Add prefix to label
                    productSalesReportData.push(${row.total_sales});
                    </c:forEach>
                </script>
                <script src="reportsJS/productSalesReport.js"></script>
                <button id="queueButton" onclick="queueReport('productSalesReport')">Add to PDF QUE</button>
            </c:when>
            <c:when test="${reportType eq 'dailySales'}">
                <script>
                    var dailySalesLabels = [];
                    var dailySalesData = [];
                    <c:forEach var="row" items="${reportData}">
                    dailySalesLabels.push('Store Number ' + '${fn:escapeXml(row.store_id)}'); // Add prefix to label
                    dailySalesData.push(${row.total_sales});
                    </c:forEach>
                </script>
                <script src="reportsJS/dailySales.js"></script>
                <button id="queueButton" onclick="queueReport('dailySales')">Add to PDF QUE</button>
            </c:when>
            <c:when test="${reportType eq 'topRatedStores'}">
                <script>
                    // Declare JavaScript arrays
                    var topRatedStoresLabels = [];
                    var topRatedStoresData = [];

                    // Iterate over reportData and push values to JavaScript arrays
                    <c:forEach var="row" items="${reportData}">
                    // Use JSTL's escapeXml function to escape special characters in strings
                    topRatedStoresLabels.push('${fn:escapeXml(row.store_name)}'); // Use store_name as label
                    // Ensure that the value is a number
                    topRatedStoresData.push(${row.average_rating}); // Use average_rating as data
                    </c:forEach>
                </script>
                <!-- Ensure this script is included after the variables are defined -->
                <script src="reportsJS/topRatedStores.js"></script>
                <button id="queueButton" onclick="queueReport('topRatedStores')">Add to PDF QUE</button>
            </c:when>
            <c:otherwise>
                <p>No specific report type selected.</p>
            </c:otherwise>
        </c:choose>

        <!-- Button to generate PDF -->
        <button id="pdfButton">Generate PDF</button>
    </c:when>
    <c:otherwise>
        <p>No data available for the selected report type.</p>
    </c:otherwise>
</c:choose>

<script>
    $(document).ready(function () {
        $('#pdfButton').click(function () {
            // Create a form and submit it to generate PDF
            var form = $('<form>', {
                'method': 'post',
                'action': 'generatePdf'
            }).append($('<input>', {
                'type': 'hidden',
                'name': 'reportType',
                'value': '${reportType}'
            }));

            // Add all chart canvases as hidden fields
            $('canvas').each(function () {
                var canvas = $(this)[0];
                var imgData = canvas.toDataURL('image/png');
                form.append($('<input>', {
                    'type': 'hidden',
                    'name': $(this).attr('id'),
                    'value': imgData
                }));
            });

            // Append the form to the body and submit it
            $('body').append(form);
            form.submit();
        });
    });
</script>

</html>
