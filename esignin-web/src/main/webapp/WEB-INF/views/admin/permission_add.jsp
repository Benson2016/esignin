<%--
  Created by IntelliJ IDEA.
  User: Benson Xu
  Date: 2016/6/2
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
  <title>ESignIn--添加角色信息</title>

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
      height:560px;
    }
    .mainContent .field {
      margin-left:130px;
    }
  </style>
</head>
<body>
<div class="mainContent" align="center">

  <form name="addForm" action="/" method="post">

    <div class="blank30"></div>
    <div class="field">
      <label class="g-label l">权限名称 &nbsp;</label>
      <input type="text" name="name" id="name" class="g-input l w-250" value="">
    </div>

    <div class="blank30"></div>
    <div class="field">
      <label class="g-label l">权限标识 &nbsp;</label>
      <input type="text" name="flag" id="flag" class="g-input l w-250" value="">
    </div>

    <div class="blank30"></div>
    <div class="field">
      <label class="g-label l">权限描述 &nbsp;</label>
      <input type="text" name="description" id="description" class="g-input l w-250" value="">
    </div>

  </form>
</div>


<script src="${root}/resources/js/jquery.min.js"></script>
<script src="${root}/resources/js/plus/placeholder.min.js"></script>
<script src="${root}/resources/js/hyxt.js"></script>
<script src="${root}/resources/plugins/jquery-datetimepicker/jquery.datetimepicker.js"></script>

<script>
  /*$(function () {
   hyxt.init();

   // init datetime picker
   $('#dEndTime').datetimepicker({lang:'ch',yearEnd:3060,format:"Y-m-d H:i:s"});
   });*/
</script>
</body>
</html>
