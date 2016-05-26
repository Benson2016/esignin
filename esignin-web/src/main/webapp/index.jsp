<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
    <script src="${root}/resources/plugins/jquery/jquery-1.11.1.min.js" type="text/javascript"></script>
</head>
    <body>
        <!--
        <h2>欢迎大牛，您已登录成功!</h2>
        -->
        <script>

            /*document.write("User-agent header sent: " + navigator.userAgent);
            document.write("<br>");
            document.write("navigator.appCodeName: " + navigator.appCodeName);
            document.write("<br>");
            document.write("navigator.appVersion: " + navigator.appVersion);*/

            var av = navigator.appVersion;
            var $_story = window.localStorage;
            if ($_story){
                if ($_story.getItem("sun")) {
                    // read u
                    var un = $_story.getItem("sun");
                    var up = $_story.getItem("sup");
                    $.ajax({
                        url: "${root}/code/loginByQR.bs",
                        dataType: "json",
                        type: "POST",
                        data: {av: av, un: un, up: up},
                        cache: false,
                        success: function(data){
                            if(data.rspCode==100) {
                                // alert(登陆成功!);
                                $_story.clear();
                                $_story.setItem("sun", data.un);
                                $_story.setItem("sup", data.up);

                                // 重新跳转页面
                                window.location.href = "${root}/page/success.bs";
                            } else {
                                register();
                            }
                        },
                        error: function(e) {
                            alert("系统错误！");
                        }
                    });
                }

            }

            /*$(function(){

             });*/

            function register() {
                // unmake
            }
        </script>
    </body>
</html>
