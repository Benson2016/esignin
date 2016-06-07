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
                <a href="${root}/getSignInList.bs?businessId=${businessId}">点击查看签到情况!</a>
            </div>
        </div>
    </c:when>
</c:choose>

</body>
</html>
