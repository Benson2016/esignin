<%@ page import="com.benson.esignin.web.domain.entity.UserInfo" %>
<%
    UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
    String userName = "";
    if (null != userInfo) userName = userInfo.getUserName();
%>
<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Success for login</title>

    <link rel="shortcut icon" href="${root}/commons/img/favicon.ico"/>

</head>
<body>
    <h3>恭喜您<%=userName%>,登录成功!</h3>
</body>
</html>
