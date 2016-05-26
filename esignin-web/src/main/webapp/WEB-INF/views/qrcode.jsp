<%--
  Created by IntelliJ IDEA.
  User: xubs
  Date: 2016/5/26
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
    <title>Easy to Sign In</title>
</head>
<body>
  <div>
    <p style="height: 210px;">&nbsp;</p>
    <h3 align="center">请扫描下面的二维码进行登录</h3>
    <div>
      <img src="${qrImg}">
    </div>
  </div>
</body>
</html>
