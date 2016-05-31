<%--
  Created by IntelliJ IDEA.
  User: benson
  Date: 16/5/30
  Time: 22:36
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
    <title>ESignIn--用户管理</title>

    <link rel="shortcut icon" href="${root}/commons/img/favicon.ico" />

</head>
<body>
    <h1 align="center">欢迎来到用户管理界面.</h1>
    <c:choose>
        <c:when test="${userList!=null}">
            <ul>
                <c:forEach var="user" items="${userList}" varStatus="status" >
                    <li>
                            ${status.count}&nbsp;&nbsp;${user.userName}&nbsp;&nbsp;${user.mobile}
                    </li>
                </c:forEach>
            </ul>
        </c:when>
        <c:when test="${userList==null || userList.size()==0}">
            <h3 align="center">暂无数据。</h3>
        </c:when>
    </c:choose>
</body>
</html>
