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
    <title>E签到 手机验证</title>
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
        .register-form {
            display: none;
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
    <form class="login-form" action="/" method="post">
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

    <!-- BEGIN REGISTER FORM -->
    <form class="register-form" action="/" method="post" onsubmit="return nextStep()">
        <h3 class="form-title">用户注册</h3>
        <div class="form-group">
            <div class="input-icon">
                <input class="form-control placeholder-no-fix" type="text" placeholder="输入姓名" id="fullName" name="fullName"/>
            </div>
        </div>
        <div class="form-actions">
            <button id="nextBtn" type="button" class="btn blue pull-right">
                下一步 <i class="m-icon-swapright m-icon-white"></i>
            </button>
        </div>
    </form>
    <!-- END REGISTER FORM -->
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
    var isCallback = true;
    function submitOK() {
        var vc = $("#verifyCode").val();
        if(vc==null || vc==''){
            alert("请输入验证码!");
            return;
        }
        var cid = $("#cid").val();
        if(cid==null || cid==''){
            alert("请点击获取验证码,并输入正确的验证码!");
            return;
        }
        // 再次验证手机号
        if(checkPhone()) {
            var phone = $("#mobile").val();
            if(isCallback) {
                isCallback = false;
                $.ajax({
                    url: "${root}/checkCode.bs",
                    dataType: "json",
                    type: "POST",
                    data: {mobile: phone, code: vc, cid: cid},
                    cache: false,
                    success: function(data){
                        isCallback = true;
                        if(data.rspCode==100) {
                            if (1==data.isUser||'1'==data.isUser) { //do login
                                doLogin();
                            } else { //do register
                                $(".login-form").hide();
                                $(".register-form").show();
                            }
                        } else {
                            alert(data.rspMsg);
                        }
                    },
                    error: function(e) {
                        isCallback = true;
                        alert("系统错误！");
                    }
                }); // end ajax
            }   // end if
        }

    }

    // initial events
    jQuery(document).ready(function () {
        // SUBMIT BUTTON
        $("#okBtn").click(function(){
            if(checkPhone()){
                submitOK();
            }
        });
        // 获取验证码
        $("#getCodeBtn").click(function(){
            if(checkPhone()){
                getCode();
            }
        });
        // 下一步
        $("#nextBtn").click(function(){
            nextStep();
        });

        disabledOkBtn(true);
    });

    function disabledOkBtn(b) {
        $("#okBtn").attr("disabled", b);
    }
    function disabledCodeBtn(b) {
        $("#getCodeBtn").attr("disabled", b);
    }

    function checkPhone(){
        var phone = $("#mobile").val();
        if(!(/^1[3|4|5|7|8]\d{9}$/.test(phone))){
            alert("请输入正确的手机号！");
            return false;
        }
        return true;
    }

    function getCode() {
        var phone = $("#mobile").val();
        $.ajax({
            url: "${root}/getVerifyCode.bs",
            dataType: "json",
            type: "POST",
            data: {mobile: phone},
            cache: false,
            success: function(data){
                if(data.rspCode==100) {
                    $("#cid").val(data.vcid);
                    // 显示计时
                    showCountDown(data.countDown);
                    disabledCodeBtn(true);
                    //$("#getCodeBtn").attr("disabled", true);
                    disabledOkBtn(false);
                } else {
                    alert(data.rspMsg);
                }
            },
            error: function(e) {
                alert("系统错误！");
            }
        });
    }

    function showCountDown(seconds) {
        $("#getCodeBtn").html(seconds + "秒");
        --seconds;
        if (seconds > 0) {
            setTimeout("showCountDown("+seconds+")", 1000);
        } else {
            $("#getCodeBtn").html("点击获取验证码");
            //$("#getCodeBtn").attr("disabled", false);
            disabledCodeBtn(false);
        }
    }

    function nextStep() {
        var fullName = $("#fullName").val();
        if(null==fullName || ''==fullName){
            alert("请输入您的姓名!");
            return;
        }

        var phone = $("#mobile").val();
        $.ajax({
            url: "${root}/user/regByMobile.bs",
            dataType: "json",
            type: "POST",
            data: {mobile: phone, fullName: fullName},
            cache: false,
            success: function(data){
                if(data.rspCode==100) {
                    <%-- 注册成功,进入签到环节 --%>
                    window.location.href = "${root}/signIn.bs";
                } else {
                    alert(data.rspMsg);
                }
            },
            error: function(e) {
                alert("系统错误！");
            }
        });
    }

    function doLogin() {
        var phone = $("#mobile").val();
        $.ajax({
            url: "${root}/user/loginByMobile.bs",
            dataType: "json",
            type: "POST",
            data: {mobile: phone},
            cache: false,
            success: function(data){
                if(data.rspCode==100) {
                    var $_story = window.localStorage;
                    $_story.setItem("sun", data.un);
                    $_story.setItem("sup", data.up);
                    <%-- 登录成功,进入签到环节 --%>
                    window.location.href = "${root}/signIn.bs";
                } else {
                    alert(data.rspMsg);
                }
            },
            error: function(e) {
                alert("系统错误！");
            }
        });
    }
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>