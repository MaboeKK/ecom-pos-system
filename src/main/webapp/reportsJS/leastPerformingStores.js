$(document).ready(function () {
    // Ensure the charts are created only if the canvas elements are available
    if ($('#barChart').length && $('#pieChart').length) {
        // Bar Chart for Least Performing Stores
        var leastPerformingStoresBarCtx = document.getElementById('barChart').getContext('2d');
        new Chart(leastPerformingStoresBarCtx, {
            type: 'bar',
            data: {
                labels: leastPerformingStoresLabels,
                datasets: [{
                    label: 'Average Sales',
                    data: leastPerformingStoresData,
                    backgroundColor: 'rgba(255, 99, 132, 0.2)',
                    borderColor: 'rgba(255, 99, 132, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    title: {
                        display: true,
                        text: 'Least Performing Stores (Bar Chart)',
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
                            return 'R ' + value.toFixed(2);
                        }
                    }
                },
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            },
            plugins: [ChartDataLabels]
        });

        // Pie Chart for Least Performing Stores
        var leastPerformingStoresPieCtx = document.getElementById('pieChart').getContext('2d');
        new Chart(leastPerformingStoresPieCtx, {
            type: 'pie',
            data: {
                labels: leastPerformingStoresLabels,
                datasets: [{
                    data: leastPerformingStoresData,
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
                        text: 'Least Performing Stores (Pie Chart)',
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
                        position: 'bottom'
                    },
                    datalabels: {
                        color: 'black',
                        font: {
                            weight: 'bold'
                        },
                        formatter: function(value) {
                            return 'R ' + value.toFixed(2);
                        },
                        anchor: 'end',
                        align: 'center'
                    }
                }
            },
            plugins: [ChartDataLabels]
        });
    }
});
