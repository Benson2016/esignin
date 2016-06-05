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
    <title>ESignIn--编辑签到类别</title>

    <link rel="shortcut icon" href="${root}/commons/img/favicon.ico" />

    <meta name="keywords" content="E签到,ESignIn,easy to sign in"/>
    <meta name="description" content="全球最流行最简便的签到系统" />
    <!-- <link rel="shortcut icon" href="" type="image/x-icon"/> -->
    <link href="${root}/resources/css/global.css" rel="stylesheet" type="text/css" />

    <style>
        .mainContent {
            background-color: #FFFFFF;
            text-align: center;
            width: 610px;
            height:300px;
        }
        .mainContent .field {
            margin-left:130px;
        }
    </style>
</head>
<body>
<div class="mainContent" align="center">

    <form name="addForm" action="${root}/type/saveType.bs" method="post">

        <div class="blank30"></div>

        <div class="field">
            <label class="g-label l">类别名称 &nbsp;</label>
            <input type="text" name="typeName" id="typeName" class="g-input l w-250" value="">
        </div>

        <div class="blank30"></div>

        <!-- HIDDEN AREA START -->
        <input type="hidden" name="id" id="id">
        <input type="hidden" id="operType" value="${operType}">
        <!-- HIDDEN AREA END -->
    </form>
</div>

<script src="${root}/resources/js/jquery.min.js"></script>

<script>

    $(function () {
        var siType = eval(${siType});
        if (null!=siType && ''!=siType) {
            $("#id").val(siType.id);
            $("#typeName").val(siType.typeName);
        }
    });

</script>
</body>
</html>
