<%--
  Created by IntelliJ IDEA.
  User: benson
  Date: 16/5/22
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
    <title>E签到--国内最流行的签到系统</title>
</head>
<body>

    <div><img src="${root}/resources/images/photo2.jpg" alt="beautiful girl" ></div>
    <p>&nbsp;</p>
    <div>
        <span>查到人数:${users.size()}</span>
        <c:forEach var="user" items="${users}" >
            <div>
                <span>用户名:${user.userName}</span><br>
                <span>邮箱:${user.email}</span>
            </div>
        </c:forEach>

    </div>
</body>

</html>
