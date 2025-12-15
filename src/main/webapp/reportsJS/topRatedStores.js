$(document).ready(function () {
    // Ensure the charts are created only if the canvas elements are available
    if ($('#barChart').length && $('#pieChart').length) {
        // Bar Chart for Top Rated Stores
        var topRatedStoresBarChartCtx = document.getElementById('barChart').getContext('2d');
        new Chart(topRatedStoresBarChartCtx, {
            type: 'bar',
            data: {
                labels: topRatedStoresLabels,
                datasets: [{
                    label: 'Average Rating',
                    data: topRatedStoresData,
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
                        text: 'Top Rated Stores',
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
                            // Format the value as a number with one decimal place
                            return value.toFixed(1); // Adjust decimal places as needed
                        }
                    }
                },
                scales: {
                    y: {
                        beginAtZero: true,
                        ticks: {
                            callback: function(value) {
                                return value; // Ensure y-axis labels are not prefixed
                            }
                        }
                    }
                }
            },
            plugins: [ChartDataLabels]  // Include the ChartDataLabels plugin
        });

        // Pie Chart for Top Rated Stores
        var topRatedStoresPieChartCtx = document.getElementById('pieChart').getContext('2d');
        new Chart(topRatedStoresPieChartCtx, {
            type: 'pie',
            data: {
                labels: topRatedStoresLabels,
                datasets: [{
                    data: topRatedStoresData,
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
                        text: 'Rating Distribution by Store',
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
                            return value.toFixed(1); // Display label and value
                        },
                        anchor: 'end',
                        align: 'center'
                    }
                }
            },
            plugins: [ChartDataLabels]  // Include the ChartDataLabels plugin
        });
    }
});
