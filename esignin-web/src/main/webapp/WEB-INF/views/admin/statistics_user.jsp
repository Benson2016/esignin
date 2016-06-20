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
    <link href="${root}/resources/plugins/jquery-datetimepicker/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />
    <style>
        canvas {
            -moz-user-select: none;
            -webkit-user-select: none;
            -ms-user-select: none;
        }
    </style>
</head>
<body>
<div style="width:100%; height:100%; padding-top: 50px; background-color: #FFFFFF;" align="center">
    <div style="width:100%; height:30px; margin-top: 10px;" align="right">
        <label class="g-label l" style="display: inline-block;">查询年份:&nbsp;</label>
        <input type="text" name="year" id="sYear" class="g-input l w-180" value="">
    </div>
    <div style="width:80%;">
        <canvas id="canvas"></canvas>
    </div>
</div>

<script src="${root}/resources/js/jquery.min.js"></script>
<script src="${root}/resources/chart/Chart.bundle.js"></script>
<script src="${root}/resources/plugins/jquery-datetimepicker/jquery.datetimepicker.js"></script>
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

    var config = {
        type: 'line',
        data: {
            labels: MONTHS,
            datasets: [{
                label: "Console Add",
                data: [],
                fill: false,
                borderDash: [5, 5]
            }, {
                label: "Register By Console",
                data: [],
                borderDash: [2, 2]
            }, {
                label: "Register By Cellphone",
                data: [],
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

    var currSearchYear = 0;
    $(function () {
        var ctx = document.getElementById("canvas").getContext("2d");
        window.myLine = new Chart(ctx, config);
        // init datetime picker
        $('#sYear').datetimepicker({lang:'ch',yearEnd:3060,format:"Y",timepicker:false, onChangeDateTime:function(dp,$input){
                searchByYear($input.val());
            }
        });
        var myDate = new Date();
        currSearchYear = myDate.getFullYear();
        $('#sYear').val(currSearchYear);
        getData(); //loading data
     });

    function searchByYear(y) {
        //alert("查询年份：" + y);
        if(currSearchYear==y){
            currSearchYear!=y;
            console.log("Current search condition is " + y + "and don't need searching.");
            return;
        }
        currSearchYear=y;
        //console.log("search year is " + y);
        $("#sYear").blur();
        getData(); //loading data
    }
    // 获取数据
    function getData() {
        $.ajax({
            url: "${root}/statistics/getUserData.bs",
            dataType: "json",
            type: "POST",
            data: {year: currSearchYear},
            cache: false,
            success: function(data){
                if(data.rspCode==100){ //success
                    reloadData(data.data1, data.data2, data.data3); // 后重新加载数据
                } else{
                    alert(data.rspMsg);
                }
            },
            error: function(e) {
                alert("系统错误！");
            }
        });
    }

    function reloadData(datas1, datas2, datas3) {
        config.data.datasets[0].data = datas1;
        config.data.datasets[1].data = datas2;
        config.data.datasets[2].data = datas3;
        window.myLine.update();
    }
</script>
</body>
</html>
