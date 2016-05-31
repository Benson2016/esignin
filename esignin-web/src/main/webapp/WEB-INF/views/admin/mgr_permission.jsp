<%--
  Created by IntelliJ IDEA.
  User: benson
  Date: 16/5/30
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
    <title>ESignIn--权限管理</title>

    <link rel="shortcut icon" href="${root}/commons/img/favicon.ico" />

</head>
<body>
    <h1 align="center">欢迎来到权限管理界面.</h1>
    <c:choose>
        <c:when test="${dataList!=null && dataList.size()>0}">
            <ul>
                <c:forEach var="perm" items="${dataList}" varStatus="status" >
                    <li>
                            ${status.count}&nbsp;&nbsp;${perm.name}&nbsp;&nbsp;${perm.createTime}
                    </li>
                </c:forEach>
            </ul>
        </c:when>
        <c:otherwise>
            <h3 align="center">暂无数据。</h3>
        </c:otherwise>
    </c:choose>
</body>
</html>
