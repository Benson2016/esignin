<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <base href="${root}/">
    <meta charset="utf-8"/>
    <title>E签到--Easy to sign in</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <meta name="MobileOptimized" content="320">
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="${root}/resources/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->
    <link rel="shortcut icon" href="${root}/commons/img/favicon.ico"/>
    <style>
        /*body {
            background-image: url("${root}/resources/img/bg/1.jpg");
            background-repeat: repeat;
        }*/
        .content {
            background: url("${root}/resources/img/bg-white-lock.png") repeat;
            width: 360px;
            margin: 0 auto;
            margin-bottom: 0px;
            padding: 30px;
            padding-top: 20px;
            padding-bottom: 15px;
        }
        .copyright {
            text-align: center;
            margin: 0 auto;
            padding: 10px;
            color: #eee;
            font-size: 12px;
        }
    </style>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body>
<!-- BEGIN LOGO -->
<div class="logo">
    <div style="height:100px;">&nbsp;</div>
</div>
<!-- END LOGO -->
<!-- BEGIN LOGIN -->
<div class="content">
    <!-- BEGIN LOGIN FORM -->
    <form class="login-form" action="${root}/user/verify.bs" method="post">
        <input type="hidden" id="cid" name="cid">
        <h3 class="form-title">手机验证</h3>
        <div class="form-group">
            <div class="input-icon">
                <i class="fa fa-mobile"></i>
                <input class="form-control placeholder-no-fix" type="text" placeholder="输入手机号" id="mobile" name="mobile"/>
            </div>
        </div>
        <div class="form-group">
            <div class="input-icon">
                <i class="fa fa-mobile"></i>
                <input class="form-control placeholder-no-fix" type="text" placeholder="输入验证码" id="verifyCode" name="verifyCode"/>
            </div>
        </div>
        <div class="form-actions">
            <button id="getCodeBtn" type="button" class="btn">
                点击获取验证码
            </button>
            <button id="okBtn" type="button" class="btn blue pull-right">
                确定 <i class="m-icon-swapright m-icon-white"></i>
            </button>
        </div>
    </form>
    <!-- END LOGIN FORM -->
</div>
<!-- END LOGIN -->

<!-- BEGIN COPYRIGHT -->
<div class="copyright">
    2016 &copy; Benson - eSignIn
</div>
<!-- END COPYRIGHT -->
<script src="${root}/resources/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="${root}/resources/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script>
    jQuery(document).ready(function () {
        // SUBMIT BUTTON
        $("#okBtn").click(function(){

            alert("功能开发中...");
        });
        // 获取验证码
        $("#getCodeBtn").click(function(){
            if(checkPhone()){
                getCode();
            }
        });
    });

    function checkPhone(){
        var phone = $("#mobile").val();
        if(!(/^1[3|4|5|7|8]\d{9}$/.test(phone))){
            alert("请输入正确的手机号！");
            return false;
        }
        return true;
    }

    function getCode() {
        alert("获取验证码功能开发中...");
    }
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>