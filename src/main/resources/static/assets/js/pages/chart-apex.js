'use strict';
$(document).ready(function() {
    setTimeout(function() {
        $(function() {
            var options = {
                chart: {
                    height: 350,
                    type: 'bar',
                },
                plotOptions: {
                    bar: {
                        horizontal: false,
                        columnWidth: '55%',
                        endingShape: 'rounded'
                    },
                },
                dataLabels: {
                    enabled: false
                },
                colors: ["#1abc9c"],
                stroke: {
                    show: true,
                    width: 2,
                    colors: ['transparent']
                },
                series: [{
                    name: '재고금액',
                    data: [430, 250, 350, 450, 170, 315, 635, 273, 383]
                }],
                xaxis: {
                    categories: ['관제부', '압착부', '용접부', '장착부','절단부','정렬부','제어부','주입부','충전부'],
                },
                yaxis: {
                    title: {
                        text: '재고금액(단위: 만원)'
                    }
                },
                fill: {
                    opacity: 1

                },
                tooltip: {
                    y: {
                        formatter: function(val) {
                            return val + " 만원"
                        }
                    }
                }
            }
            var chart = new ApexCharts(
                document.querySelector("#bar-chart-1"),
                options
            );
            chart.render();
        });

     
		$(function() {
            var options = {
                chart: {
                    height: 320,
                    type: 'pie',
                },
                labels: ['관제부', '압착부', '용접부', '장착부','절단부','정렬부','제어부','주입부','충전부'],
                series: [430, 250, 350, 450, 170, 315, 635, 273, 383],
                colors: ["#1abc9c", "#0e9e4a", "#00acc1", "#f1c40f", "#e74c3c", "#36DAE3", "#366DE3", "#36A3E3", "#7E7EE6"],
                legend: {
                    show: true,
                    position: 'bottom',
                },
                dataLabels: {
                    enabled: true,
                    dropShadow: {
                        enabled: false,
                    }
                },
                responsive: [{
                    breakpoint: 480,
                    options: {
                        legend: {
                            position: 'bottom'
                        }
                    }
                }]
            }
            var chart = new ApexCharts(
                document.querySelector("#pie-chart-1"),
                options
            );
            chart.render();
        });
    
	}, 700);
});
