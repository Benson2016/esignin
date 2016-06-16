<%--
  Created by IntelliJ IDEA.
  User: xubs
  Date: 2016/6/2
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
    <title>ESignIn--用户统计</title>

    <link rel="shortcut icon" href="${root}/commons/img/favicon.ico" />

    <meta name="keywords" content="E签到,ESignIn,easy to sign in"/>
    <meta name="description" content="全球最流行最简便的签到系统" />
    <!-- <link rel="shortcut icon" href="" type="image/x-icon"/> -->
    <link href="${root}/resources/css/global.css" rel="stylesheet" type="text/css" />
    <link href="${root}/resources/css/jquery.placeholder.css" rel="stylesheet" type="text/css" />

    <style>
        canvas {
            -moz-user-select: none;
            -webkit-user-select: none;
            -ms-user-select: none;
        }
    </style>
</head>
<body>

<div style="width:100%; height:100%; padding-top: 100px; background-color: white" align="center">
    <div style="width:80%;">
        <canvas id="canvas"></canvas>
    </div>
</div>

<script src="${root}/resources/js/jquery.min.js"></script>
<script src="${root}/resources/chart/Chart.js"></script>
<script src="${root}/resources/chart/Chart.min.js"></script>
<script src="${root}/resources/chart/Chart.bundle.js"></script>
<script src="${root}/resources/chart/Chart.bundle.min.js"></script>
<script>
    var MONTHS = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];

    var randomScalingFactor = function() {
        return Math.round(Math.random() * 50 * (Math.random() > 0.5 ? 1 : 1)) + 50;
    };
    var randomColorFactor = function() {
        return Math.round(Math.random() * 255);
    };
    var randomColor = function(opacity) {
        return 'rgba(' + randomColorFactor() + ',' + randomColorFactor() + ',' + randomColorFactor() + ',' + (opacity || '.3') + ')';
    };

    var data1 = eval(${data1});
    var data2 = eval(${data2});
    var data3 = eval(${data3});
    var config = {
        type: 'line',
        data: {
            labels: MONTHS,
            datasets: [{
                label: "Console Add",
                data: data1,
                fill: false,
                borderDash: [5, 5]
            }, {
                label: "Register By Console",
                data: data2,
                borderDash: [2, 2]
            }, {
                label: "Register By Cellphone",
                data: data3,
                borderDash: [1, 1]
            }]
        },
        options: {
            responsive: true,
            title:{
                display:true,
                text:"User Register Statistics"
            },
            scales: {
                xAxes: [{
                    display: true,
                    ticks: {
                        userCallback: function(dataLabel, index) {
                            return index % 2 === 0 ? dataLabel : '';
                        }
                    }
                }],
                yAxes: [{
                    display: true,
                    beginAtZero: false
                }]
            }
        }
    };

    // 动态修改图形颜色
    $.each(config.data.datasets, function(i, dataset) {
        dataset.borderColor = randomColor(0.4);
        dataset.backgroundColor = randomColor(0.5);
        dataset.pointBorderColor = randomColor(0.7);
        dataset.pointBackgroundColor = randomColor(0.5);
        dataset.pointBorderWidth = 1;
    });

    $(function () {
        var ctx = document.getElementById("canvas").getContext("2d");
        window.myLine = new Chart(ctx, config);

     });

</script>
</body>
</html>
