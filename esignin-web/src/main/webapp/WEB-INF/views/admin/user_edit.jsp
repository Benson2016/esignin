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
    <title>ESignIn--编辑用户信息</title>

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
            <label class="g-label l">用户名 &nbsp;</label>
            <input type="text" name="userName" id="userName" class="g-input l w-250" value="">
        </div>

        <div class="blank30"></div>
        <div class="field">
            <label class="g-label l">姓  &nbsp;&nbsp;名 &nbsp;</label>
            <input type="text" name="fullName" id="fullName" class="g-input l w-250" value="">
        </div>

        <div class="blank30"></div>
        <div class="field">
            <label class="g-label l">性  &nbsp;&nbsp;别 &nbsp;</label>
            <select name="sex" id="sex" class="g-input l w-250" style="margin-right:30px">
                <option value="3">未知</option>
                <option value="1">男</option>
                <option value="2">女</option>
            </select>
        </div>

        <div class="blank30"></div>
        <div class="field">
            <label class="g-label l">年  &nbsp;&nbsp;龄 &nbsp;</label>
            <input type="text" name="age" id="age" class="g-input l w-250" value="0">
        </div>

        <div class="blank30"></div>
        <div class="field">
            <label class="g-label l">邮  &nbsp;&nbsp;箱 &nbsp;</label>
            <input type="text" name="email" id="email" class="g-input l w-250" value="">
        </div>

        <div class="blank30"></div>
        <div class="field">
            <label class="g-label l">手机号码 &nbsp;</label>
            <input type="text" name="mobile" id="mobile" class="g-input l w-250" value="">
        </div>

        <div class="blank30"></div>
        <div class="field">
            <label class="g-label l">用户标识 &nbsp;</label>
            <select name="flag" id="flag" class="g-input l w-250" style="margin-right:30px">
                <option value="1">普通用户</option>
                <option value="2">管理员</option>
                <option value="3">超级管理员</option>
            </select>
        </div>

        <!-- HIDDEN AREA START -->
        <input type="hidden" name="id" id="id">
        <input type="hidden" name="password" id="password">
        <input type="hidden" name="isValid" id="isValid">
        <input type="hidden" name="createTime" id="createTime">
        <!-- HIDDEN AREA END -->
    </form>
</div>


<script src="${root}/resources/js/jquery.min.js"></script>
<script src="${root}/resources/js/plus/placeholder.min.js"></script>
<script src="${root}/resources/js/hyxt.js"></script>
<script src="${root}/resources/plugins/jquery-datetimepicker/jquery.datetimepicker.js"></script>

<script>
    $(function () {
        hyxt.init();

        var user = eval(${user});
        // 初始化控件值
        if(''!= user && null!=user){
            $("#id").val(user.id);
            $("#userName").val(user.userName);
            $("#fullName").val(user.fullName);
            $("#password").val(user.password);
            $("#mobile").val(user.mobile);
            $("#age").val(user.age);
            $("#sex").val(user.sex);
            $("#email").val(user.email);
            $("#isValid").val(user.isValid);
            $("#flag").val(user.flag);

            $('#createTime').val(user.createTimeStr);
        }

        $("#userName").attr("disabled", true);
     });
</script>
</body>
</html>
