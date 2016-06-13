<%--
  Created by IntelliJ IDEA.
  User: benson
  Date: 16/5/29
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
    <script src="${root}/resources/plugins/jquery/jquery-1.11.1.min.js" type="text/javascript"></script>
    <link rel="shortcut icon" href="${root}/commons/img/favicon.ico"/>
</head>
<body>

<script>
    // 回调标识,防止在同一时间,重复请求后台
    var isCallback = true;
    var av = navigator.appVersion;
    var $_story = window.localStorage;
    if ($_story){
        // reading sun info
        var sun = $_story.getItem("sun");
        var sup = $_story.getItem("sup");
        if ((null!=sun && ''!==sun)&&(null!=sup && ''!==sup)) {
            if (isCallback) {
                isCallback = false;
                $.ajax({
                    url: "${root}/code/loginByQR.bs",
                    dataType: "json",
                    type: "POST",
                    data: {un: sun, up: sup},
                    cache: false,
                    success: function(data){
                        isCallback = true;
                        if(data.rspCode==100) {
                            $_story.setItem("sun", data.un);
                            $_story.setItem("sup", data.up);
                            // 业务处理
                            window.location.href = "${root}/signIn.bs";
                        } else if(101==data.rspCode) {
                            // 用户尚未注册,需要进行注册
                            window.location.href = "${root}/page/mobileVerify.bs";
                        } else {
                            alert(data.rspMsg);
                        }
                    },
                    error: function(e) {
                        isCallback = true;
                        alert("系统错误！");
                    }
                });
            }
        } else {
            gotoMobileVerify();
        }

    } else {
        // goto mobile verify
        gotoMobileVerify();
    }

    function gotoMobileVerify() {
        window.location.href = "${root}/page/mobileVerify.bs";
    }

</script>
</body>
</html>

