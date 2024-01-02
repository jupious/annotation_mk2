'use strict';
var invChart = (function(){
    
    function barChart(categories,amounts) {
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
                data: amounts
            }],
            xaxis: {
                categories: categories,
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
    };

    function makeHexCode(){
        const candidate = [0,1,2,3,4,5,6,7,8,9,'a','b','c','d','e','f'];
        const palette = Array(6).fill().map((v)=>candidate[Math.floor(Math.random()*candidate.length)]);

        return palette.join('');
    }

    function getHexCodeList(count){
        var codeArray = [];
        for(let i = 0; i < count; i++){
            codeArray.push('#'+makeHexCode());
        }
        return codeArray;
    }

 
    function pieChart(categories,amounts) {
        var options = {
            chart: {
                height: 320,
                type: 'pie',
            },
            labels: categories,
            series: amounts,
            colors: getHexCodeList(categories.length),
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
    };

    return{pieChart:pieChart, barChart:barChart};
})();


