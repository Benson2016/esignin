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
  <div id="canvas-holder" style="width: 300px;">
    <canvas id="chart-area" width="300" height="300" />
  </div>

  <div id="chartjs-tooltip"></div>

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

    var position = $(this._chart.canvas)[0].getBoundingClientRect();

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
    });
  };

  var config = {
    type: 'pie',
    data: {
      datasets: [{
        data: [300, 50, 100, 40, 10, 90],
        backgroundColor: [
          "#F7464A",
          "#46BFBD",
          "#FDB45C",
          "#949FB1",
          "#4D5360",
          "blue"
        ]
      }],
      labels: [
        "Red",
        "Green",
        "Yellow",
        "Grey",
        "Dark Grey",
        "Blue"
      ]
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
