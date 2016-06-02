<%--
  Created by IntelliJ IDEA.
  User: xubs
  Date: 2016/6/2
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
    <title>ESignIn--添加 QR Code</title>

  <link rel="shortcut icon" href="${root}/commons/img/favicon.ico" />

  <meta name="keywords" content="E签到,ESignIn,easy to sign in"/>
  <meta name="description" content="全球最流行最简便的签到系统" />
  <!-- <link rel="shortcut icon" href="" type="image/x-icon"/> -->
  <link href="${root}/resources/css/global.css" rel="stylesheet" type="text/css" />
  <link href="${root}/resources/css/jquery.placeholder.css" rel="stylesheet" type="text/css" />
  <link href="${root}/resources/css/discount.css" rel="stylesheet" type="text/css" />
  <link href="${root}/resources/css/page.css" rel="stylesheet" type="text/css" />
  <link href="${root}/resources/plugins/jquery-datetimepicker/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />

  <style>
    .mainContent {
      background-color: #FFFFFF;
      text-align: center;
      width: 610px;
      height:710px;
    }
    .mainContent .field {
      margin-left:170px;
    }
  </style>
</head>
<body>
  <div class="mainContent" align="center">

    <form name="addForm" action="${root}/code/addQrCode.bs" >

      <div class="blank30"></div>

      <div class="field">
        <label class="g-label l">业务主题 &nbsp;</label>
        <input type="text" name="title" id="title" class="g-input l w-180" value="">
      </div>

      <div class="blank20"></div>

      <div class="field">
        <label class="g-label l">签到类型 &nbsp;</label>
        <select name="signInType" id="siTypeSelect" class="g-input l w-180" style="margin-right:30px">
          <option value="">不限</option>
          <c:if test="${null!=signInTypeList}">
            <c:forEach var="sitype" items="${signInTypeList}">
              <option value="${sitype.id}">${sitype.typeName}</option>
            </c:forEach>
          </c:if>
        </select>
      </div>


      <div class="blank20"></div>

      <div class="field">
        <label class="g-label l">开始时间 &nbsp;</label>
        <input type="text" name="effectiveTimeStart" id="dStartTime" class="g-input l w-180" value="" >
      </div>

      <div class="blank20"></div>
      <div class="field">
        <label class="g-label l">结束时间 &nbsp;</label>
        <input type="text" name="effectiveTimeEnd" id="dEndTime" class="g-input l w-180" value="" >
      </div>

      <div class="blank20"></div>

      <div class="field">
        <label class="g-label l">图片内容 &nbsp;</label>
        <input type="text" name="image" id="image" class="g-input l w-180" value="">
      </div>

      <div class="blank20"></div>

      <div class="field">
        <label class="g-label l">业务描述 &nbsp;</label>
        <input type="text" name="description" id="description" class="g-input l w-180" value="">
      </div>

      <div class="blank30"></div>
      <div class="blank30"></div>

      <div class="field">
        <a href="javascript:;" class="g-searchBtn r" id="saveBtn">保 存</a>
      </div>

    </form>
  </div>


  <script src="${root}/resources/js/jquery.min.js"></script>
  <script src="${root}/resources/js/plus/placeholder.min.js"></script>
  <script src="${root}/resources/js/hyxt.js"></script>
  <script src="${root}/resources/plugins/jquery-datetimepicker/jquery.datetimepicker.js"></script>

<script>
  $(function () {
    hyxt.init();

    // init datetime picker
    $('#dStartTime').datetimepicker({lang:'ch',yearEnd:3060,format:"Y-m-d H:i:s"});
    $('#dEndTime').datetimepicker({lang:'ch',yearEnd:3060,format:"Y-m-d H:i:s"});

    $('#saveBtn').click(function(){
      saveFunc();
    });
  });

  function saveFunc() {
    //window.opener.document.all.sTitle.value = "英雄";
    window.close();
  }
</script>
</body>
</html>
