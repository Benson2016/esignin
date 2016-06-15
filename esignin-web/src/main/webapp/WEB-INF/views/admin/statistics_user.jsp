<%--
  Created by IntelliJ IDEA.
  User: xubs
  Date: 2016/6/2
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
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
        .mainContent {
            background-color: #FFFFFF;
            text-align: center;
            width: 100%;
            height:100%;
        }
    </style>
</head>
<body>
<div class="mainContent" align="center">

    <canvas id="myChart" width="400" height="400"></canvas>
</div>


<script src="${root}/resources/js/jquery.min.js"></script>
<script src="${root}/resources/chart/Chart.js"></script>
<script src="${root}/resources/chart/Chart.min.js"></script>
<script src="${root}/resources/chart/Chart.bundle.js"></script>
<script src="${root}/resources/chart/Chart.bundle.min.js"></script>
<script>
    // show data
    var data = {
        labels : ["January","February","March","April","May","June","July"],
        datasets : [
            {
                fillColor : "rgba(220,220,220,0.5)",
                strokeColor : "rgba(220,220,220,1)",
                pointColor : "rgba(220,220,220,1)",
                pointStrokeColor : "#fff",
                data : [65,59,90,81,56,55,40]
            },
            {
                fillColor : "rgba(151,187,205,0.5)",
                strokeColor : "rgba(151,187,205,1)",
                pointColor : "rgba(151,187,205,1)",
                pointStrokeColor : "#fff",
                data : [28,48,40,19,96,27,100]
            }
        ]
    };

    Line.defaults = {

        //Boolean - If we show the scale above the chart data
        scaleOverlay : false,

        //Boolean - If we want to override with a hard coded scale
        scaleOverride : false,

        //** Required if scaleOverride is true **
        //Number - The number of steps in a hard coded scale
        scaleSteps : null,
        //Number - The value jump in the hard coded scale
        scaleStepWidth : null,
        //Number - The scale starting value
        scaleStartValue : null,

        //String - Colour of the scale line
        scaleLineColor : "rgba(0,0,0,.1)",

        //Number - Pixel width of the scale line
        scaleLineWidth : 1,

        //Boolean - Whether to show labels on the scale
        scaleShowLabels : false,

        //Interpolated JS string - can access value
        scaleLabel : "<%=value%>",

        //String - Scale label font declaration for the scale label
        scaleFontFamily : "'Arial'",

        //Number - Scale label font size in pixels
        scaleFontSize : 12,

        //String - Scale label font weight style
        scaleFontStyle : "normal",

        //String - Scale label font colour
        scaleFontColor : "#666",

        ///Boolean - Whether grid lines are shown across the chart
        scaleShowGridLines : true,

        //String - Colour of the grid lines
        scaleGridLineColor : "rgba(0,0,0,.05)",

        //Number - Width of the grid lines
        scaleGridLineWidth : 1,

        //Boolean - Whether the line is curved between points
        bezierCurve : true,

        //Boolean - Whether to show a dot for each point
        pointDot : true,

        //Number - Radius of each point dot in pixels
        pointDotRadius : 3,

        //Number - Pixel width of point dot stroke
        pointDotStrokeWidth : 1,

        //Boolean - Whether to show a stroke for datasets
        datasetStroke : true,

        //Number - Pixel width of dataset stroke
        datasetStrokeWidth : 2,

        //Boolean - Whether to fill the dataset with a colour
        datasetFill : true,

        //Boolean - Whether to animate the chart
        animation : true,

        //Number - Number of animation steps
        animationSteps : 60,

        //String - Animation easing effect
        animationEasing : "easeOutQuart",

        //Function - Fires when the animation is complete
        onAnimationComplete : null

    };

    $(function () {

        //Get context with jQuery - using jQuery's .get() method.
        var ctx = $("#myChart").get(0).getContext("2d");
        //This will get the first returned node in the jQuery collection.
        var myNewChart = new Chart(ctx);

        myNewChart.polarArea(data, Line.defaults);

     });

</script>
</body>
</html>
