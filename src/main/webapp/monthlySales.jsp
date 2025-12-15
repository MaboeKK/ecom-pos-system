<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Report Results</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery"></script>
    <style>
        .chart-container {
            width: 100%;
            height: 400px;
            margin: 0 auto;
        }
    </style>
</head>
<body>
<h1>Report Results</h1>
<c:choose>
    <c:when test="${not empty reportData and reportData[0] != null}">
        <table border="1">
            <thead>
            <tr>
                <c:forEach var="key" items="${reportData[0].keySet()}">
                    <th>${key}</th>
                </c:forEach>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="row" items="${reportData}">
                <tr>
                    <c:forEach var="value" items="${row.values()}">
                        <td>${value}</td>
                    </c:forEach>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="chart-container">
            <canvas id="barChart"></canvas>
        </div>
        <div class="chart-container">
            <canvas id="pieChart"></canvas>
        </div>

        <script>
            $(document).ready(function() {
                // Prepare data for charts
                var labels = [];
                var totalSalesData = [];

                <c:forEach var="row" items="${reportData}">
                labels.push('${row.month}');
                totalSalesData.push(${row.total_sales});
                </c:forEach>

                // Bar Chart
                var barChartCtx = document.getElementById('barChart').getContext('2d');
                new Chart(barChartCtx, {
                    type: 'bar',
                    data: {
                        labels: labels,
                        datasets: [{
                            label: 'Total Sales',
                            data: totalSalesData,
                            backgroundColor: 'rgba(54, 162, 235, 0.2)',
                            borderColor: 'rgba(54, 162, 235, 1)',
                            borderWidth: 1
                        }]
                    },
                    options: {
                        responsive: true,
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                });

                // Pie Chart
                var pieChartCtx = document.getElementById('pieChart').getContext('2d');
                new Chart(pieChartCtx, {
                    type: 'pie',
                    data: {
                        labels: labels,
                        datasets: [{
                            data: totalSalesData,
                            backgroundColor: [
                                'rgba(255, 99, 132, 0.2)',
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(255, 206, 86, 0.2)',
                                'rgba(75, 192, 192, 0.2)',
                                'rgba(153, 102, 255, 0.2)',
                                'rgba(255, 159, 64, 0.2)'
                            ],
                            borderColor: [
                                'rgba(255, 99, 132, 1)',
                                'rgba(54, 162, 235, 1)',
                                'rgba(255, 206, 86, 1)',
                                'rgba(75, 192, 192, 1)',
                                'rgba(153, 102, 255, 1)',
                                'rgba(255, 159, 64, 1)'
                            ],
                            borderWidth: 1
                        }]
                    },
                    options: {
                        responsive: true
                    }
                });
            });
        </script>
    </c:when>
    <c:otherwise>
        <p>No data available for the selected report type.</p>
    </c:otherwise>
</c:choose>
</body>
</html>
