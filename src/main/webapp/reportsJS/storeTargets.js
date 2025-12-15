$(document).ready(function () {
    // Ensure the charts are created only if the canvas elements are available
    if ($('#barChart').length && $('#pieChart').length) {
        // Bar Chart for Store Targets
        var storeTargetsBarChartCtx = document.getElementById('barChart').getContext('2d');
        new Chart(storeTargetsBarChartCtx, {
            type: 'bar',
            data: {
                labels: storeTargetsLabels,
                datasets: [{
                    label: 'Targets Reached',
                    data: storeTargetsData,
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
                        text: 'Stores That Achieved Target for a Particular Month',
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
                            return 'R ' + value.toFixed(2);
                        }
                    }
                },
                scales: {
                    y: {
                        beginAtZero: true,
                        title: {
                            display: true,
                            text: 'Sales Amount' // Y-axis label
                        }
                    },
                    x: {
                        title: {
                            display: true,
                            text: 'Stores' // X-axis label
                        }
                    }
                }
            },
            plugins: [ChartDataLabels]  // Add this line to include the plugin
        });

        // Pie Chart for Store Targets
        var storeTargetsPieChartCtx = document.getElementById('pieChart').getContext('2d');
        new Chart(storeTargetsPieChartCtx, {
            type: 'pie',
            data: {
                labels: storeTargetsLabels,
                datasets: [{
                    data: storeTargetsData,
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
                        text: 'Sales Distribution by Store',
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
                        formatter: function(value) {
                            return 'R ' + value.toFixed(2); // Format the value with 'R' prefix
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
