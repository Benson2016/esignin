<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<!--
Template Name: Metronic - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.0.3
Version: 1.5.5
Author: KeenThemes
Website: http://www.keenthemes.com/
Purchase: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
-->
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
    <link href="${root}/resources/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="${root}/resources/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${root}/resources/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link rel="stylesheet" type="text/css" href="${root}/resources/plugins/select2/select2_metro.css"/>
    <!-- END PAGE LEVEL SCRIPTS -->
    <!-- BEGIN THEME STYLES -->
    <link href="${root}/resources/css/style-metronic.css" rel="stylesheet" type="text/css"/>
    <link href="${root}/resources/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${root}/resources/css/style-responsive.css" rel="stylesheet" type="text/css"/>
    <link href="${root}/resources/css/plugins.css" rel="stylesheet" type="text/css"/>
    <link href="${root}/resources/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="${root}/resources/css/pages/login-soft.css" rel="stylesheet" type="text/css"/>
    <!-- END THEME STYLES -->
    <link rel="shortcut icon" href="${root}/commons/img/favicon.ico"/>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="login">
<!-- BEGIN LOGO -->
<div class="logo">
    <img src="${root}/resources/img/logo-big.png" alt=""/>
</div>
<!-- END LOGO -->
<!-- BEGIN LOGIN -->
<div class="content">
    <!-- BEGIN LOGIN FORM -->
    <form class="login-form" action="${root}/user/login.bs" method="post">
        <h3 class="form-title">用户登录</h3>
        <div class="alert alert-danger display-hide">
            <button class="close" data-close="alert"></button>
			<span class="login_error"></span>
        </div>
        <div class="form-group">
            <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
            <label class="control-label visible-ie8 visible-ie9">用户名</label>
            <div class="input-icon">
                <i class="fa fa-user"></i>
                <input name="username" id="userName" size="25" class="form-control placeholder-no-fix"
                       type="text" autocomplete="off" placeholder="用户名"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">密码</label>
            <div class="input-icon">
                <i class="fa fa-lock"></i>
                <input name="password" id="password" size="25" value="" class="form-control placeholder-no-fix"
                       type="password" autocomplete="off" placeholder="密码"/>
            </div>
        </div>
        <div class="form-actions">
            <label class="checkbox">
                <input type="checkbox" name="remember" value="1"/> 记住我 </label>
            <button id="loginBut" type="submit" class="btn blue pull-right">
                登录 <i class="m-icon-swapright m-icon-white"></i>
            </button>
        </div>
        <div class="alert alert-tips display-hide">
        </div>
        <div class="forget-password">
            <h4>忘记密码 ?</h4>
            <p>点击 <a href="javascript:;" id="forget-password">这里</a> 重置您的密码.
            </p>
        </div>
        <div class="create-account">
            <p>
                还没有账号 ?&nbsp; <a href="javascript:;" id="register-btn">创建一个账号</a>
            </p>
        </div>
    </form>
    <!-- END LOGIN FORM -->
    <!-- BEGIN FORGOT PASSWORD FORM -->
    <form class="forget-form" action="/forget.html" method="post">
        <h3>忘记密码 ?</h3>
        <p>
            请输入您的电子邮箱地址来重置您的密码.
        </p>
        <div class="form-group">
            <div class="input-icon">
                <i class="fa fa-envelope"></i>
                <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Email"
                       name="email"/>
            </div>
        </div>
        <div class="form-actions">
            <button type="button" id="back-btn" class="btn">
                <i class="m-icon-swapleft"></i> 返回
            </button>
            <button type="submit" class="btn blue pull-right">
                提交 <i class="m-icon-swapright m-icon-white"></i>
            </button>
        </div>
    </form>
    <!-- END FORGOT PASSWORD FORM -->
    <!-- BEGIN REGISTRATION FORM -->
    <form class="register-form" action="${root}/user/register.bs" method="post">
        <h3>注 册</h3>
        <p>
            请输入您的个人信息:
        </p>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">全名</label>
            <div class="input-icon">
                <i class="fa fa-font"></i>
                <input class="form-control placeholder-no-fix" type="text" placeholder="全名" name="fullName"/>
            </div>
        </div>
        <div class="form-group">
            <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
            <label class="control-label visible-ie8 visible-ie9">Email</label>
            <div class="input-icon">
                <i class="fa fa-envelope"></i>
                <input class="form-control placeholder-no-fix" type="text" placeholder="Email" name="email"/>
            </div>
        </div>
        <div class="form-group">
            <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
            <label class="control-label visible-ie8 visible-ie9">Mobile</label>
            <div class="input-icon">
                <i class="fa fa-mobile"></i>
                <input class="form-control placeholder-no-fix" type="text" placeholder="Mobile" name="mobile"/>
            </div>
        </div>
        <p>
            请输入账号信息:
        </p>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">用户名</label>
            <div class="input-icon">
                <i class="fa fa-user"></i>
                <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="用户名"
                       name="userName"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">密码</label>
            <div class="input-icon">
                <i class="fa fa-lock"></i>
                <input class="form-control placeholder-no-fix" type="password" autocomplete="off" id="register_password"
                       placeholder="密码" name="password"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">再次输入密码</label>
            <div class="controls">
                <div class="input-icon">
                    <i class="fa fa-check"></i>
                    <input class="form-control placeholder-no-fix" type="password" autocomplete="off"
                           placeholder="再次输入密码" name="rpassword"/>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label>
                <input type="checkbox" name="tnc"/> 我同意 <a href="#">服务条款</a> 和 <a href="#">隐私政策</a>
            </label>
            <div id="register_tnc_error">
            </div>
        </div>
        <div class="form-actions">
            <button id="register-back-btn" type="button" class="btn">
                <i class="m-icon-swapleft"></i> 返回
            </button>
            <button type="submit" id="register-submit-btn" class="btn blue pull-right">
                注册 <i class="m-icon-swapright m-icon-white"></i>
            </button>
        </div>
    </form>
    <!-- END REGISTRATION FORM -->
</div>
<!-- END LOGIN -->
<!-- BEGIN COPYRIGHT -->
<div class="copyright">
    2016 &copy; Benson - SignIn System
</div>
<!-- END COPYRIGHT -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="${root}/resources/plugins/respond.min.js"></script>
<script src="${root}/resources/plugins/excanvas.min.js"></script>
<![endif]-->
<script src="${root}/resources/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="${root}/resources/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<script src="${root}/resources/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${root}/resources/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${root}/resources/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${root}/resources/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="${root}/resources/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${root}/resources/plugins/jquery-validation/dist/jquery.validate.min.js"
        type="text/javascript"></script>
<script src="${root}/resources/plugins/backstretch/jquery.backstretch.min.js" type="text/javascript"></script>
<script src="${root}/resources/plugins/select2/select2.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${root}/resources/scripts/app.js" type="text/javascript"></script>
<script src="${root}/resources/scripts/login-soft.js" type="text/javascript"></script>

<script src="${root}/commons/lib/security/sha256.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
    jQuery(document).ready(function () {
        App.init();
        Login.init();
        // CHECK OPERATION
        var operType = '${operType}';
        if(''==operType) return;
        if(operType==2) {
            $("#register-btn").click();
            // ERROR TIPS
            var error = '${rspMsg}';
            if (null != error && ''!==error) {
                $("#register_tnc_error").html(error).css({fontSize:"12px",color:"red"});
            }
        } else {
            var error = '${rspMsg}';
            if (null != error && ''!==error) {
                $('.login_error').html(error);
                $('.alert-danger', $('.login-form')).show();
            }
        }
    });
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>