<%--
  Created by IntelliJ IDEA.
  User: Benson Xu
  Date: 2016/6/6
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
  <title>ESignIn--系统日志管理</title>

  <link rel="shortcut icon" href="${root}/commons/img/favicon.ico" />

  <meta name="keywords" content="E签到,ESignIn,easy to sign in"/>
  <meta name="description" content="全球最流行最简便的签到系统" />
  <!-- <link rel="shortcut icon" href="" type="image/x-icon"/> -->
  <link href="${root}/resources/css/global.css" rel="stylesheet" type="text/css" />
  <link href="${root}/resources/css/jquery.placeholder.css" rel="stylesheet" type="text/css" />
  <link href="${root}/resources/css/discount.css" rel="stylesheet" type="text/css" />
  <link href="${root}/resources/css/page.css" rel="stylesheet" type="text/css" />
  <link href="${root}/resources/plugins/jquery-datetimepicker/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />

  <%@ include file="/commons/style.jsp" %>
</head>
<body>
<div class="screen">
  <div class="headBox">
    <div class="topBox">
      <span class="childicon"><img src="${root}/commons/img/icon.jpg"></span>
      <span class="childtetle">系统操作日志列表</span>
    </div>
  </div>
  <div class="contentBox">
    <div class="blank3"></div>
    <div class="configureBox"  >
      <!-- 查询与导出 -->
      <form id="searchForm" action="${root}/log/exportSysLogData.bs" method="post">
        <div style="margin:13px 0;">
          <label class="g-label l">用户名称:&nbsp;</label>
          <input type="text" name="userName" id="sUserName" class="g-input l w-180" value="" style="margin-right:10px">
          <label class="g-label l">模块名称:&nbsp;</label>
          <input type="text" name="moduleName" id="sModuleName" class="g-input l w-180" value="" style="margin-right:10px">
          <label class="g-label l">操作内容:&nbsp;</label>
          <input type="text" name="operContent" id="sOperContent" class="g-input l w-180" value="" style="margin-right:10px">
          <div class="blank5"></div>
          <label class="g-label l">操作时间:&nbsp;</label>
          <input type="text" name="startTime" id="sStartTime" class="g-input l w-180" value=""  style="margin-right:10px" >
          <label class="g-label l">至 &nbsp;</label>
          <input type="text" name="endTime" id="sEndTime" class="g-input l w-180" value=""  style="margin-right:30px" >

          <a href="javascript:;" class="g-searchBtn r" id="searchBtn">查询</a>
          <a href="javascript:;" class="g-searchBtn r" id="clearBtn">清除</a>&nbsp;&nbsp;
          <div class="clear"></div>
          <input type="hidden" name="logType" value="1">
        </div>
      </form>

      <div class="tableTopBtn">
        <shiro:hasPermission name="deleteSysLog">
          <a class="delSelectBtn g-searchBtn" href="javascript:void(0)">删除选中</a>&nbsp;&nbsp;
        </shiro:hasPermission>
        <a class="exportDataBtn" href="javascript:void(0)" title="当无查询条件时，则导出所有数据">导出数据</a>
      </div>
      <input id="orderBy" type="hidden" name="orderBy" value="" />
      <input id="direction" type="hidden" name="direction" value="desc" />
      <!-- 列表开始-->
      <div class="listBox">
        <table class="g-tableList">
          <thead>
          <tr>
            <th width="3%">序号</th>
            <th width="7%"><input type="checkbox" name="checkAllOrNot" id="checkAllOrNot" onclick="checkAllEvent()">选项</th>
            <th width="10%">IP地址</th>
            <th width="15%">用户名称</th>
            <th width="10%">模块名称</th>
            <th width="30%">操作内容</th>
            <th width="10%">操作的时间</th>
            <th width="10%">消耗时间</th>
            <th width="5%">操 作</th>
          </tr>
          </thead>
          <tbody id="data_body"></tbody>
        </table>
        <div class="pagebar" id="pagebar"></div>
      </div>

    </div>

  </div>
</div>
<div id="loading"></div>

<script src="${root}/resources/js/jquery.min.js"></script>
<script src="${root}/resources/js/plus/placeholder.min.js"></script>
<script src="${root}/resources/js/plus/graphic/highcharts.js"></script>
<script src="${root}/resources/js/hyxt.js"></script>
<script src="${root}/resources/js/dialog.js"></script>
<script src="${root}/resources/js/PageBarNumList.js"></script>
<script src="${root}/resources/plugins/jquery-datetimepicker/jquery.datetimepicker.js"></script>
<script src="${root}/commons/js/esignInDialog.js"></script>


<script>

  // 是否已回调
  var isCallbacked = true;

  $(function () {
    hyxt.init();

    // 查询记录
    $("#searchBtn").click(function(){
      getDataHtml(1, 10);
    });
    // 清除查询条件
    $("#clearBtn").click(function(){
      $('#sStartTime').val("");
      $('#sEndTime').val("");
      $('#sUserName').val("");
      $('#sModuleName').val("");
      $('#sOperContent').val("");
    });

    //导出数据
    $('.tableTopBtn').delegate('.exportDataBtn', 'click', function(){
      $('#searchForm').submit();
    });


    //删除选中
    $('.tableTopBtn').delegate('.delSelectBtn', 'click', function(){
      var arr = getCheckedValues();
      if(arr.toString()==null || arr.toString()=='') {
        showMsg("您尚未选中记录！");
        return;
      }
      if(isCallbacked) {
        isCallbacked = false;
        //弹窗确认
        showConfirm("确定删除选中记录？", function() {
          $('#loading').show();
          $.ajax({
            url: "${root}/log/delSysLog.bs",
            dataType: "json",
            type: "POST",
            data: {ids: arr.toString()},
            cache: false,
            success: function(data){
              isCallbacked = true;
              $('#loading').hide();
              if(data.rspCode==100){ //success
                showMsg(data.rspMsg);
                getDataHtml(1); //重新加载数据
              } else{
                showMsg(data.rspMsg);
              }
            },
            error: function(e) {
              $('#loading').hide();
              isCallbacked = true;
              showMsg("系统错误！");
            }
          });
        });
        isCallbacked = true;
      }
    });

    // init datetime picker
    $('#sStartTime').datetimepicker({lang:'ch',yearEnd:3060,format:"Y-m-d H:i:s"});
    $('#sEndTime').datetimepicker({lang:'ch',yearEnd:3060,format:"Y-m-d H:i:s"});
  });


  var isCallBack = true;

  function getDataHtml(pageNo,pagesize) {
    if (isCallBack) {
      $('#loading').show();
      isCallBack = false;
      $.ajax({
        url: "${root}/log/sysLogListData.bs",
        dataType: "json",
        type: "POST",
        cache: false,
        data: {userName: $("#sUserName").val(), moduleName: $("#sModuleName").val(), operContent: $("#sOperContent").val(), startTime:$('#sStartTime').val(), endTime:$('#sEndTime').val(), page: pageNo, size: pagesize || 10},
        success: function (data) {
          var html = "";
          $.each(data.content, function (i, v) {
            html += '<tr class="' + ((i % 2 == 0) ? 'even' : '') + '">' +
            '<td>' + (i+1) + '</td>' +
            '<td><input type="checkbox" name="checkbox" value="' + v.id + '"/></td>' +
            '<td>' + v.ip + '</td>' +
            '<td>' + v.userName + '</td>' +
            '<td>' + v.moduleName + '</td>' +
            '<td>' + v.operContent + '</td>' +
            '<td>' + v.operTimeStr + '</td>' +
            '<td>' + v.consumeTime + '</td>' +
            '<td><a class="showViewBtn" href="javascript:void(0)" onclick="showView(\'' + v.id + '\')">查看详情</a></td>' +
            '</tr>';
          })

          $('#loading').hide();
          $("#data_body").html(html);
          document.getElementById('pagebar').innerHTML = PageBarNumList.getPageBar(pageNo, data.totalPages, 3, 'getDataHtml', pagesize || 10, true);
          isCallBack = true;
        },
        error: function (e) {
          $('#loading').hide();
          console.log(e);
          showMsg("系统错误,请重试!");
        }
      });
    }
  }
  getDataHtml(1);

  //获取多个checkbox选中项
  function getCheckedValues() {
    var arr = new Array();
    $("[name='checkbox']").each(function(){
      if ($(this).is(':checked')) {
        arr.push($(this).val());
      }
    });
    return arr;
  }

  function checkAllEvent() {
    if($("#checkAllOrNot").is(':checked')) {
      $("[name='checkbox']").prop("checked",'true');//全选
    } else {
      $("[name='checkbox']").removeAttr("checked");//取消全选
    }
  }

  // 显示日志详情
  function showView(id) {
    showMsg("功能研发中。。。尽请期待！！！");
    //showFormDialog("${root}/log/showView.bs?logId="+id, "showLog", "日志详情", 610, 560);
  }

</script>
</body>

</html>
