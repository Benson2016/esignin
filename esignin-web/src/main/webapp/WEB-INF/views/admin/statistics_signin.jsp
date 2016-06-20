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
  <link href="${root}/resources/css/global.css" rel="stylesheet" type="text/css" />
  <link href="${root}/resources/css/jquery.placeholder.css" rel="stylesheet" type="text/css" />
  <link href="${root}/resources/plugins/jquery-datetimepicker/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />

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
<div style="width:100%; height:100%; padding-top: 50px; background-color: #FFFFFF;" align="center">
  <div style="width:100%; height:30px; margin-top: 10px;" align="right">
    <label class="g-label l" style="display: inline-block;">查询年份:&nbsp;</label>
    <input type="text" name="year" id="sYear" class="g-input l w-180" value="">
  </div>
  <div id="canvas-holder" style="width: 500px;">
    <canvas id="chart-area" width="500" height="500" />
  </div>

  <div id="chartjs-tooltip"></div>
  <div id="tips" style="display:none;">暂无数据</div>
</div>

<script src="${root}/resources/js/jquery.min.js"></script>
<script src="${root}/resources/chart/Chart.bundle.js"></script>
<script src="${root}/resources/plugins/jquery-datetimepicker/jquery.datetimepicker.js"></script>
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
  };


  var randomColorFactor = function() {
    return Math.round(Math.random() * 255);
  };
  var randomColor = function(opacity) {
    return 'rgba(' + randomColorFactor() + ',' + randomColorFactor() + ',' + randomColorFactor() + ',' + (opacity || '.3') + ')';
  };

  var config = {
    type: 'pie',
    data: {
      datasets: [{
        data: [],
        backgroundColor: []
      }],
      labels: []
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

  var currSearchYear = 0;
  $(function () {
    var ctx = document.getElementById("chart-area").getContext("2d");
    window.myPie = new Chart(ctx, config);
    // init datetime picker
    $('#sYear').datetimepicker({lang:'ch',yearEnd:3060,format:"Y",timepicker:false, onChangeDateTime:function(dp,$input){
      searchByYear($input.val());
    }});
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
      url: "${root}/statistics/getSignInStaticData.bs",
      dataType: "json",
      type: "POST",
      data: {year: currSearchYear},
      cache: false,
      success: function(data){
        if(data.rspCode==100){ //success
          reloadData(data.data, data.names); // 后重新加载数据
          if (data.size==0) {
            $("#tips").show();
          } else {
            $("#tips").hide();
          }
        } else{
          alert(data.rspMsg);
        }
      },
      error: function(e) {
        alert("系统错误！");
      }
    });
  }

  function reloadData(datas, names) {
    config.data.datasets[0].data = datas;
    config.data.datasets[0].backgroundColor = getBGColors(names.length);
    config.data.labels = names;
    window.myPie.update();
  }

  var colorArr = ["#F7464A","#46BFBD","#FDB45C","#3BE2E2","rgb(232, 67, 205)"];
  // 动态获取背景颜色
  function getBGColors(size) {
    var colors = colorArr;
    if (size>colorArr.length) {
      var len = colorArr.length;
      for (var i=len; i<size; i++) {
        colors[i] = randomColor(0.8);
      }
    }
    return colors;
  }
</script>
</body>

</html>
