$(document).ready(function () {
    // Ensure the charts are created only if the canvas elements are available
    if ($('#barChart').length && $('#pieChart').length) {
        // Bar Chart for Product Sales
        var productSalesBarChartCtx = document.getElementById('barChart').getContext('2d');
        new Chart(productSalesBarChartCtx, {
            type: 'bar',
            data: {
                labels: productSalesReportLabels, // Ensure this variable is defined
                datasets: [{
                    label: 'Top Sales',
                    data: productSalesReportData, // Ensure this variable is defined
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    title: {
                        display: true,
                        text: 'Sales Report for a Chosen Product',
                        font: {
                            size: 16,
                            weight: 'bold'
                        },
                        padding: {
                            top: 10,
                            bottom: 30
                        }
                    },
                    datalabels: {
                        anchor: 'end',
                        align: 'top',
                        color: 'black',
                        font: {
                            weight: 'bold'
                        },
                        formatter: function(value) {
                            // Format the value with 'R' prefix
                            return 'R ' + value.toFixed(2); // Adjust number of decimal places as needed
                        }
                    }
                },
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            },
            plugins: [ChartDataLabels]  // Add this line to include the plugin
        });

        // Pie Chart for Product Sales
        var productSalesPieChartCtx = document.getElementById('pieChart').getContext('2d');
        new Chart(productSalesPieChartCtx, {
            type: 'pie',
            data: {
                labels: productSalesReportLabels, // Ensure this variable is defined
                datasets: [{
                    data: productSalesReportData, // Ensure this variable is defined
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
                responsive: true,
                plugins: {
                    title: {
                        display: true,
                        text: 'Sales Report for a Chosen Product',
                        font: {
                            size: 16,
                            weight: 'bold'
                        },
                        padding: {
                            top: 10,
                            bottom: 30
                        }
                    },
                    legend: {
                        position: 'bottom' // Position legend at the bottom of the pie chart
                    },
                    datalabels: {
                        color: 'black', // Label color
                        font: {
                            weight: 'bold'
                        },
                        formatter: function(value, context) {
                            return `R ${value.toFixed(2)}`; // Display label and value
                        },
                        anchor: 'end',
                        align: 'center'
                    }
                }
            },
            plugins: [ChartDataLabels]  // Add this line to include the plugin
        });
    }
});
