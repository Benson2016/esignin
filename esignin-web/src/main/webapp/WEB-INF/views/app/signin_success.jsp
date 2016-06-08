<%--
  Created by IntelliJ IDEA.
  User: benson
  Date: 16/5/28
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
    <title>E SIGN IN</title>

    <link rel="shortcut icon" href="${root}/commons/img/favicon.ico"/>

    <style>
        .content {
            background-color: #FFFFFF;
            text-align: center;
        }
        #countdownDiv { display: none; position: fixed; font-size: 38px; color: #FFFFFF; top:0; left:0; width:360px; height:58px; z-index:9999; background:rgba(0, 0, 0, .2) }
    </style>
</head>
<body>

<c:choose>
    <c:when test="${alreadySign}">
        <script>
            window.location.href = "${root}/getSignInList.bs?businessId=${businessId}";
        </script>
    </c:when>
    <c:when test="${!alreadySign}">
        <div class="content">
            <h1>&nbsp;</h1>
            <h3 align="center">恭喜${userName},您已签到成功!</h3>
            <h1>&nbsp;</h1>
            <div align="center">
                <img src="${root}/commons/img/success_face.gif" alt="success face"/>
            </div>
            <div align="center">
                <a href="javascript:void(0);" onclick="gotoSignInList()">点击查看签到情况!</a>
            </div>
        </div>
    </c:when>
</c:choose>
<div id="countdownDiv"><span id="countdownNum">3</span>秒后页面将跳转</div>

<script src="${root}/resources/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
<script>
    $(function() {
        // initial something
        $('#countdownDiv').show();
        setTimeout("countdown()", 1000);
    });

    function gotoSignInList() {
        window.location.href = "${root}/getSignInList.bs?businessId=${businessId}";
    }

    var i = 3;
    function countdown() {
        --i;
        $('#countdownNum').html(i);
        if(i>0) {
            setTimeout("countdown()", 1000);
        }
        if(i<=0) {
            $('#countdownDiv').hide();
            gotoSignInList(); // 页面跳转
        }
    }
</script>
</body>
</html>
