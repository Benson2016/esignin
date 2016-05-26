<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
    <script src="${root}/resources/plugins/jquery/jquery-1.11.1.min.js" type="text/javascript"></script>
</head>
    <body>
        <h2>欢迎大牛，您已登录成功!</h2>
        <script>

            /*document.write("User-agent header sent: " + navigator.userAgent);
            document.write("<br>");
            document.write("navigator.appCodeName: " + navigator.appCodeName);
            document.write("<br>");
            document.write("navigator.appVersion: " + navigator.appVersion);*/

            var appVersion = navigator.appVersion;

            $(function(){
                $.ajax({
                    url: "${root}/code/loginByQR.bs",
                    dataType: "json",
                    type: "POST",
                    data: {appVersion: appVersion},
                    cache: false,
                    success: function(data){

                    },
                    error: function(e) {
                        alert("系统错误！");
                    }
                });
             });
        </script>
    </body>
</html>
