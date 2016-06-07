<%--
  Created by IntelliJ IDEA.
  User: benson
  Date: 16/5/29
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
    <title>E SIGN IN</title>
    <link href="${root}/resources/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" href="${root}/commons/img/favicon.ico"/>
    <style>
        .list-group {
            width: 610px; height: auto; text-align: left;
        }
        .col_space {
            margin-left: 80px;
            display: inline-block;
        }
    </style>
</head>
<body>
    <div class="signin-list" align="center">
        <h1 align="center">签到情况</h1>
        <ul class="list-group" >
        <c:choose>
            <c:when test="${records.size()>0}">
                <c:forEach var="record" items="${records}" varStatus="status">
                    <li class="list-group-item">
                        <span class="badge">${status.count}</span>
                        <span class="glyphicon glyphicon-user"></span>
                        <span class="col_space">${record.mobile}</span>
                        <span class="col_space">${record.createTimeStr}</span>
                    </li>
                </c:forEach>
            </c:when>
            <c:when test="${records.size()<=0}">
                <li class="list-group-item" >暂无数据</li>
            </c:when>
        </c:choose>
        </ul>
    </div>
</body>
</html>
