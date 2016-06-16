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
  <title>ESignIn--签到统计</title>

  <link rel="shortcut icon" href="${root}/commons/img/favicon.ico" />

  <meta name="keywords" content="E签到,ESignIn,easy to sign in"/>
  <meta name="description" content="全球最流行最简便的签到系统" />
  <script src="${root}/resources/js/jquery.min.js"></script>
  <script src="${root}/resources/chart/Chart.bundle.js"></script>
  <script src="${root}/resources/chart/Chart.bundle.min.js"></script>
  <style>
    #canvas-holder {
      width: 100%;
      margin-top: 50px;
      text-align: center;
    }
    #chartjs-tooltip {
      opacity: 1;
      position: absolute;
      background: rgba(0, 0, 0, .7);
      color: white;
      border-radius: 3px;
      -webkit-transition: all .1s ease;
      transition: all .1s ease;
      pointer-events: none;
      -webkit-transform: translate(-50%, 0);
      transform: translate(-50%, 0);
    }

    .chartjs-tooltip-key {
      display: inline-block;
      width: 10px;
      height: 10px;
    }
  </style>
</head>
<body>
<div style="width:100%; height:100%; padding-top: 80px; background-color: white" align="center">
  <div id="canvas-holder" style="width: 500px;">
    <canvas id="chart-area" width="500" height="500" />
  </div>

  <div id="chartjs-tooltip"></div>
</div>
<script>
  Chart.defaults.global.tooltips.custom = function(tooltip) {

    // Tooltip Element
    var tooltipEl = $('#chartjs-tooltip');

    if (!tooltipEl[0]) {
      $('body').append('<div id="chartjs-tooltip"></div>');
      tooltipEl = $('#chartjs-tooltip');
    }

    // Hide if no tooltip
    if (!tooltip.opacity) {
      tooltipEl.css({
        opacity: 0
      });
      $('.chartjs-wrap canvas')
              .each(function(index, el) {
                $(el).css('cursor', 'default');
              });
      return;
    }

    $(this._chart.canvas).css('cursor', 'pointer');

    // Set caret Position
    tooltipEl.removeClass('above below no-transform');
    if (tooltip.yAlign) {
      tooltipEl.addClass(tooltip.yAlign);
    } else {
      tooltipEl.addClass('no-transform');
    }

    // Set Text
    if (tooltip.body) {
      var innerHtml = [
        (tooltip.beforeTitle || []).join('\n'), (tooltip.title || []).join('\n'), (tooltip.afterTitle || []).join('\n'), (tooltip.beforeBody || []).join('\n'), (tooltip.body || []).join('\n'), (tooltip.afterBody || []).join('\n'), (tooltip.beforeFooter || [])
                .join('\n'), (tooltip.footer || []).join('\n'), (tooltip.afterFooter || []).join('\n')
      ];
      tooltipEl.html(innerHtml.join('\n'));
    }

    // Find Y Location on page
    var top = 0;
    if (tooltip.yAlign) {
      if (tooltip.yAlign == 'above') {
        top = tooltip.y - tooltip.caretHeight - tooltip.caretPadding;
      } else {
        top = tooltip.y + tooltip.caretHeight + tooltip.caretPadding;
      }
    }

    /*var position = $(this._chart.canvas)[0].getBoundingClientRect();

    // Display, position, and set styles for font
    tooltipEl.css({
      opacity: 1,
      width: tooltip.width ? (tooltip.width + 'px') : 'auto',
      left: position.left + tooltip.x + 'px',
      top: position.top + top + 'px',
      fontFamily: tooltip._fontFamily,
      fontSize: tooltip.fontSize,
      fontStyle: tooltip._fontStyle,
      padding: tooltip.yPadding + 'px ' + tooltip.xPadding + 'px'
    });*/
  };


  var randomColorFactor = function() {
    return Math.round(Math.random() * 255);
  };
  var randomColor = function(opacity) {
    return 'rgba(' + randomColorFactor() + ',' + randomColorFactor() + ',' + randomColorFactor() + ',' + (opacity || '.3') + ')';
  };

  var data1 = eval(${data1});
  var names = eval(${names});
  var config = {
    type: 'pie',
    data: {
      datasets: [{
        data: data1,
        backgroundColor: [
          "#F7464A",
          "#46BFBD",
          "#FDB45C",
          "#3BE2E2",
          "rgb(232, 67, 205)"
        ]
      }],
      labels: names
    },
    options: {
      responsive: true,
      legend: {
        display: true
      },
      tooltips: {
        enabled: true
      }
    }
  };

  $(function () {
    var ctx = document.getElementById("chart-area").getContext("2d");
    window.myPie = new Chart(ctx, config);

  });
</script>
</body>

</html>
