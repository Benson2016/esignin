<%--
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
    <head>
        <base href="<${root}">
        <meta charset="UTF-8">
        <title></title>
        <link rel="stylesheet" type="text/css" href="<${root}/resources/plugins/bootstrap/css/bootstrap.css"/>
    </head>
    <body>

        <script src="<${root}/resources/plugins/jquery/jquery-1.11.1.js" type="text/javascript" charset="utf-8"></script>
        <script src="<${root}/resources/plugins/bootstrap/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
        <script src="" type="text/javascript" charset="utf-8"></script>
    </body>
</html>