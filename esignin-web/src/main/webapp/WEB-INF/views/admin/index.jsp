<%@ page import="com.benson.esignin.web.domain.entity.UserInfo" %>
<%@ page import="com.benson.esignin.common.cons.SysCons" %>
<%--
  Created by IntelliJ IDEA.
  User: benson
  Date: 16/5/24
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%
    UserInfo userInfo = (UserInfo) session.getAttribute(SysCons.LOGIN_USER);
    String userName = "";
    if (null != userInfo) userName = userInfo.getUserName();
%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->

<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->

<!-- BEGIN HEAD -->

<head>
    <meta charset="utf-8" />

    <title>E签到管理后台</title>

    <meta content="width=device-width, initial-scale=1.0" name="viewport" />

    <meta content="This is the system that easy to sign in." name="description" />

    <meta content="Benson Xu" name="author" />

    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="${root}/skin/media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

    <link href="${root}/skin/media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>

    <link href="${root}/skin/media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

    <link href="${root}/skin/media/css/style-metro.css" rel="stylesheet" type="text/css"/>

    <link href="${root}/skin/media/css/style.css" rel="stylesheet" type="text/css"/>

    <link href="${root}/skin/media/css/style-responsive.css" rel="stylesheet" type="text/css"/>

    <link href="${root}/skin/media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>

    <link href="${root}/skin/media/css/uniform.default.css" rel="stylesheet" type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->

    <!-- BEGIN PAGE LEVEL STYLES -->
    <link href="${root}/skin/media/css/jquery.gritter.css" rel="stylesheet" type="text/css"/>

    <link href="${root}/skin/media/css/daterangepicker.css" rel="stylesheet" type="text/css" />

    <link href="${root}/skin/media/css/fullcalendar.css" rel="stylesheet" type="text/css"/>

    <link href="${root}/skin/media/css/jqvmap.css" rel="stylesheet" type="text/css" media="screen"/>

    <link href="${root}/skin/media/css/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" media="screen"/>
    <!-- END PAGE LEVEL STYLES -->

    <link rel="shortcut icon" href="${root}/commons/img/favicon.ico" />


</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="page-header-fixed">

<!-- BEGIN HEADER -->

<div class="header navbar navbar-inverse navbar-fixed-top">

    <!-- BEGIN TOP NAVIGATION BAR -->

    <div class="navbar-inner">

        <div class="container-fluid">
            <%--
            <!-- BEGIN LOGO -->
            <a class="brand" href="${root}/admin/toAdmin.bs">
                <img src="${root}/skin/media/image/logo.png" alt="logo"/>
            </a>
            <!-- END LOGO -->
            --%>
            <!-- BEGIN RESPONSIVE MENU TOGGLER -->

            <a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">

                <img src="${root}/skin/media/image/menu-toggler.png" alt="" />

            </a>

            <!-- END RESPONSIVE MENU TOGGLER -->

            <!-- BEGIN TOP NAVIGATION MENU -->

            <ul class="nav pull-right">

                <!-- BEGIN NOTIFICATION DROPDOWN -->

                <li class="dropdown" id="header_notification_bar">

                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">

                        <i class="icon-warning-sign"></i>

                        <span class="badge">6</span>

                    </a>

                    <ul class="dropdown-menu extended notification">

                        <li>

                            <p>You have 14 new notifications</p>

                        </li>

                        <li>

                            <a href="#">

                                <span class="label label-success"><i class="icon-plus"></i></span>

                                New user registered.

                                <span class="time">Just now</span>

                            </a>

                        </li>

                        <li>

                            <a href="#">

                                <span class="label label-important"><i class="icon-bolt"></i></span>

                                Server #12 overloaded.

                                <span class="time">15 mins</span>

                            </a>

                        </li>

                        <li>

                            <a href="#">

                                <span class="label label-warning"><i class="icon-bell"></i></span>

                                Server #2 not respoding.

                                <span class="time">22 mins</span>

                            </a>

                        </li>

                        <li>

                            <a href="#">

                                <span class="label label-info"><i class="icon-bullhorn"></i></span>

                                Application error.

                                <span class="time">40 mins</span>

                            </a>

                        </li>

                        <li>

                            <a href="#">

                                <span class="label label-important"><i class="icon-bolt"></i></span>

                                Database overloaded 68%.

                                <span class="time">2 hrs</span>

                            </a>

                        </li>

                        <li>

                            <a href="#">

                                <span class="label label-important"><i class="icon-bolt"></i></span>

                                2 user IP blocked.

                                <span class="time">5 hrs</span>

                            </a>

                        </li>

                        <li class="external">

                            <a href="#">See all notifications <i class="m-icon-swapright"></i></a>

                        </li>

                    </ul>

                </li>

                <!-- END NOTIFICATION DROPDOWN -->

                <!-- BEGIN INBOX DROPDOWN -->

                <li class="dropdown" id="header_inbox_bar">

                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">

                        <i class="icon-envelope"></i>

                        <span class="badge">5</span>

                    </a>

                    <ul class="dropdown-menu extended inbox">

                        <li>

                            <p>You have 12 new messages</p>

                        </li>

                        <li>

                            <a href="inbox.html?a=view">

                                <span class="photo"><img src="${root}/skin/media/image/avatar2.jpg" alt="" /></span>

								<span class="subject">

								<span class="from">Lisa Wong</span>

								<span class="time">Just Now</span>

								</span>

								<span class="message">

								Vivamus sed auctor nibh congue nibh. auctor nibh

								auctor nibh...

								</span>

                            </a>

                        </li>

                        <li>

                            <a href="inbox.html?a=view">

                                <span class="photo"><img src="${root}/skin/media/image/avatar3.jpg" alt="" /></span>

								<span class="subject">

								<span class="from">Richard Doe</span>

								<span class="time">16 mins</span>

								</span>

								<span class="message">

								Vivamus sed congue nibh auctor nibh congue nibh. auctor nibh

								auctor nibh...

								</span>

                            </a>

                        </li>

                        <li>

                            <a href="inbox.html?a=view">

                                <span class="photo"><img src="${root}/skin/media/image/avatar1.jpg" alt="" /></span>

								<span class="subject">

								<span class="from">Bob Nilson</span>

								<span class="time">2 hrs</span>

								</span>

								<span class="message">

								Vivamus sed nibh auctor nibh congue nibh. auctor nibh

								auctor nibh...

								</span>

                            </a>

                        </li>

                        <li class="external">

                            <a href="inbox.html">See all messages <i class="m-icon-swapright"></i></a>

                        </li>

                    </ul>

                </li>

                <!-- END INBOX DROPDOWN -->

                <!-- BEGIN TODO DROPDOWN -->

                <li class="dropdown" id="header_task_bar">

                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">

                        <i class="icon-tasks"></i>

                        <span class="badge">5</span>

                    </a>

                    <ul class="dropdown-menu extended tasks">

                        <li>

                            <p>You have 12 pending tasks</p>

                        </li>

                        <li>

                            <a href="#">

								<span class="task">

								<span class="desc">New release v1.2</span>

								<span class="percent">30%</span>

								</span>

								<span class="progress progress-success ">

								<span style="width: 30%;" class="bar"></span>

								</span>

                            </a>

                        </li>

                        <li>

                            <a href="#">

								<span class="task">

								<span class="desc">Application deployment</span>

								<span class="percent">65%</span>

								</span>

								<span class="progress progress-danger progress-striped active">

								<span style="width: 65%;" class="bar"></span>

								</span>

                            </a>

                        </li>

                        <li>

                            <a href="#">

								<span class="task">

								<span class="desc">Mobile app release</span>

								<span class="percent">98%</span>

								</span>

								<span class="progress progress-success">

								<span style="width: 98%;" class="bar"></span>

								</span>

                            </a>

                        </li>

                        <li>

                            <a href="#">

								<span class="task">

								<span class="desc">Database migration</span>

								<span class="percent">10%</span>

								</span>

								<span class="progress progress-warning progress-striped">

								<span style="width: 10%;" class="bar"></span>

								</span>

                            </a>

                        </li>

                        <li>

                            <a href="#">

								<span class="task">

								<span class="desc">Web server upgrade</span>

								<span class="percent">58%</span>

								</span>

								<span class="progress progress-info">

								<span style="width: 58%;" class="bar"></span>

								</span>

                            </a>

                        </li>

                        <li>

                            <a href="#">

								<span class="task">

								<span class="desc">Mobile development</span>

								<span class="percent">85%</span>

								</span>

								<span class="progress progress-success">

								<span style="width: 85%;" class="bar"></span>

								</span>

                            </a>

                        </li>

                        <li class="external">

                            <a href="#">See all tasks <i class="m-icon-swapright"></i></a>

                        </li>

                    </ul>

                </li>

                <!-- END TODO DROPDOWN -->

                <!-- BEGIN USER LOGIN DROPDOWN -->

                <li class="dropdown user">

                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">

                        <img alt="" src="${root}/skin/media/image/avatar1_small.jpg" />

                        <span class="username"><%=userName%><shiro:hasRole name="super">超级管理员</shiro:hasRole></span>

                        <i class="icon-angle-down"></i>

                    </a>

                    <ul class="dropdown-menu">
                        <%--
                        <li><a href="extra_profile.html"><i class="icon-user"></i> My Profile</a></li>

                        <li><a href="page_calendar.html"><i class="icon-calendar"></i> My Calendar</a></li>

                        <li><a href="inbox.html"><i class="icon-envelope"></i> My Inbox(3)</a></li>

                        <li><a href="#"><i class="icon-tasks"></i> My Tasks</a></li>

                        <li class="divider"></li>
                        --%>
                        <li><a href="extra_lock.html"><i class="icon-lock"></i> Lock Screen</a></li>

                        <li><a href="${root}/user/logout.bs"><i class="icon-key"></i> Log Out</a></li>

                    </ul>

                </li>

                <!-- END USER LOGIN DROPDOWN -->

            </ul>

            <!-- END TOP NAVIGATION MENU -->

        </div>

    </div>

    <!-- END TOP NAVIGATION BAR -->

</div>

<!-- END HEADER -->

<!-- BEGIN CONTAINER -->

<div class="page-container">

    <!-- BEGIN SIDEBAR -->

    <div class="page-sidebar nav-collapse collapse">

        <!-- BEGIN SIDEBAR MENU -->

        <ul class="page-sidebar-menu">
            <li>
                <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
                <div class="sidebar-toggler hidden-phone"></div>
                <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
            </li>

            <li class="start active">
                <a href="javascript:(0);" onclick="openMenuItem(0)">
                    <i class="icon-home"></i>
                    <span class="title">控制面板</span>
                    <span class="selected"></span>
                </a>
            </li>


            <!-- QR Manager -->
            <li class="">
                <a href="javascript:;">
                    <i class="icon-barcode"></i>
                    <span class="title">QRCode管理</span>
                    <span class="arrow "></span>
                </a>
                <ul class="sub-menu">
                    <%--
                    <li >
                        <a href="javascript:;">业务类型</a>
                    </li>
                    --%>
                    <li >
                        <a href="javascript:void(0);" onclick="openMenuItem(5)">
                            <i class="icon-barcode"></i>QRCode列表</a>
                    </li>
                </ul>
            </li>

            <!-- Sign in Manager -->
            <li class="">
                <a href="javascript:;">
                    <i class="icon-ok"></i>
                    <span class="title">签到管理</span>
                    <span class="arrow "></span>
                </a>
                <ul class="sub-menu">
                    <li >
                        <a href="javascript:void(0);" onclick="openMenuItem(6)">
                            <i class="icon-time"></i>签到类型</a>
                    </li>
                    <li >
                        <a href="javascript:void(0);" onclick="openMenuItem(7)">
                            <i class="icon-time"></i>签到记录</a>
                    </li>
                </ul>
            </li>

            <!-- Log Manager -->
            <li class="">
                <a href="javascript:;">
                    <i class="icon-bookmark-empty"></i>
                    <span class="title">日志管理</span>
                    <span class="arrow "></span>
                </a>
                <ul class="sub-menu">
                    <li >
                        <a href="javascript:0" onclick="openMenuItem(8)"><i class="icon-file-alt"></i>操作日志</a>
                    </li>
                    <li >
                        <a href="javascript:0" onclick="openMenuItem(9)"><i class="icon-file-alt"></i>异常日志</a>
                    </li>

                </ul>
            </li>

            <!-- Statistics Manager -->
            <li class="">
                <a href="javascript:;">
                    <i class="icon-bar-chart"></i>
                    <span class="title">统计分析</span>
                    <span class="arrow "></span>
                </a>
                <ul class="sub-menu">
                    <li >
                        <a href="extra_profile.html">
                            <i class="icon-bar-chart"></i>用户统计</a>
                    </li>
                    <li >
                        <a href="extra_lock.html">
                            <i class="icon-bar-chart"></i>签到统计</a>
                    </li>
                </ul>
            </li>

            <!-- Sys Manager -->
            <li class="">
                <a href="javascript:(0);">
                    <i class="icon-cogs"></i>
                    <span class="title">系统管理</span>
                    <span class="arrow "></span>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a href="javascript:0" onclick="openMenuItem(1)"><i class="icon-user"></i>用户管理</a>
                    </li>
                    <li>
                        <a href="javascript:0" onclick="openMenuItem(2)"><i class="icon-user-md"></i>角色管理</a>
                    </li>
                    <li>
                        <a href="javascript:0" onclick="openMenuItem(3)"><i class="icon-unlock"></i>权限管理</a>
                    </li>
                    <li>
                        <a href="javascript:0" onclick="openMenuItem(4)"><i class="icon-tasks"></i>菜单管理</a>
                    </li>

                </ul>
            </li>

        </ul>
        <!-- END SIDEBAR MENU -->

    </div>
    <!-- END SIDEBAR -->

    <!-- BEGIN PAGE -->
    <div class="page-content">

        <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->
        <div id="portlet-config" class="modal hide">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button"></button>
                <h3>Widget Settings</h3>
            </div>
            <div class="modal-body">
                Widget settings form goes here
            </div>
        </div>
        <!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM-->

        <!-- BEGIN MAINPANEL -->
        <div id="mainPanel" style="display: none; width: 100%; height: 100%">
            <iframe id="mainContent" scrolling="auto" frameborder="0"  width="100%" height="100%" style="_width: 100%;" onload="javascript:this.height=this.contentWindow.document.body.scrollHeight+110;" src="${root}/admin/welcome.bs"></iframe>
        </div>
        <!-- END MAINPANEL -->

        <!-- BEGIN PAGE CONTAINER-->
        <div class="container-fluid" id="container123">
            <!-- BEGIN PAGE HEADER-->
            <div class="row-fluid">
                <div class="span12">
                    <!-- BEGIN STYLE CUSTOMIZER -->
                    <div class="color-panel hidden-phone">
                        <div class="color-mode-icons icon-color"></div>
                        <div class="color-mode-icons icon-color-close"></div>
                        <div class="color-mode">
                            <p>THEME COLOR</p>
                            <ul class="inline">
                                <li class="color-black current color-default" data-style="default"></li>
                                <li class="color-blue" data-style="blue"></li>
                                <li class="color-brown" data-style="brown"></li>
                                <li class="color-purple" data-style="purple"></li>
                                <li class="color-grey" data-style="grey"></li>
                                <li class="color-white color-light" data-style="light"></li>
                            </ul>
                            <label>
                                <span>Layout</span>
                                <select class="layout-option m-wrap small">
                                    <option value="fluid" selected>Fluid</option>
                                    <option value="boxed">Boxed</option>
                                </select>
                            </label>
                            <label>
                                <span>Header</span>
                                <select class="header-option m-wrap small">
                                    <option value="fixed" selected>Fixed</option>
                                    <option value="default">Default</option>
                                </select>
                            </label>
                            <label>
                                <span>Sidebar</span>
                                <select class="sidebar-option m-wrap small">
                                    <option value="fixed">Fixed</option>
                                    <option value="default" selected>Default</option>
                                </select>
                            </label>
                            <label>
                                <span>Footer</span>
                                <select class="footer-option m-wrap small">
                                    <option value="fixed">Fixed</option>
                                    <option value="default" selected>Default</option>
                                </select>
                            </label>
                        </div>
                    </div>
                    <!-- END BEGIN STYLE CUSTOMIZER -->

                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                    <h3 class="page-title">
                        ESignIn <small>statistics and more</small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="index.html">主页</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <!-- Dashboard Module -->
                        <li><a href="javascript:(0)" onclick="openMenuItem(0)">控制面板</a></li>
                        <li class="pull-right no-text-shadow">
                            <div id="dashboard-report-range" class="dashboard-date-range tooltips no-tooltip-on-touch-device responsive" data-tablet="" data-desktop="tooltips" data-placement="top" data-original-title="Change dashboard date range">
                                <i class="icon-calendar"></i>
                                <span></span>
                                <i class="icon-angle-down"></i>
                            </div>
                        </li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->

                </div>
            </div>
            <!-- END PAGE HEADER-->

            <!-- BEGIN DASHBOARD -->
            <div id="dashboard">
                <div id="dashboardPanel"><c:import url="dashboard.jsp"></c:import></div>
            </div>
            <!-- END DASHBOARD -->

        </div>
        <!-- END PAGE CONTAINER-->

    </div>
    <!-- END PAGE -->

</div>
<!-- END CONTAINER -->

<!-- BEGIN FOOTER -->
<div class="footer">
    <div class="footer-inner">
        <span id="curr_year"></span> &copy; ESignIn by BensonXu.
    </div>

    <div class="footer-tools">
			<span class="go-top">
			<i class="icon-angle-up"></i>
			</span>
    </div>
</div>
<!-- END FOOTER -->

<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<script src="${root}/skin/media/js/jquery-1.10.1.min.js" type="text/javascript"></script>
<script src="${root}/skin/media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="${root}/skin/media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>
<script src="${root}/skin/media/js/bootstrap.min.js" type="text/javascript"></script>
<!--[if lt IE 9]>
<script src="${root}/skin/media/js/excanvas.min.js"></script>
<script src="${root}/skin/media/js/respond.min.js"></script>
<![endif]-->

<script src="${root}/skin/media/js/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${root}/skin/media/js/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${root}/skin/media/js/jquery.cookie.min.js" type="text/javascript"></script>
<script src="${root}/skin/media/js/jquery.uniform.min.js" type="text/javascript" ></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<%--
<script src="${root}/skin/media/js/jquery.vmap.js" type="text/javascript"></script>
<script src="${root}/skin/media/js/jquery.vmap.russia.js" type="text/javascript"></script>
<script src="${root}/skin/media/js/jquery.vmap.world.js" type="text/javascript"></script>
<script src="${root}/skin/media/js/jquery.vmap.europe.js" type="text/javascript"></script>
<script src="${root}/skin/media/js/jquery.vmap.germany.js" type="text/javascript"></script>
<script src="${root}/skin/media/js/jquery.vmap.usa.js" type="text/javascript"></script>
<script src="${root}/skin/media/js/jquery.vmap.sampledata.js" type="text/javascript"></script>
--%>

<script src="${root}/skin/media/js/jquery.flot.js" type="text/javascript"></script>
<script src="${root}/skin/media/js/jquery.flot.resize.js" type="text/javascript"></script>
<script src="${root}/skin/media/js/jquery.pulsate.min.js" type="text/javascript"></script>
<script src="${root}/skin/media/js/date.js" type="text/javascript"></script>
<script src="${root}/skin/media/js/daterangepicker.js" type="text/javascript"></script>
<script src="${root}/skin/media/js/jquery.gritter.js" type="text/javascript"></script>
<script src="${root}/skin/media/js/fullcalendar.min.js" type="text/javascript"></script>
<script src="${root}/skin/media/js/jquery.easy-pie-chart.js" type="text/javascript"></script>
<script src="${root}/skin/media/js/jquery.sparkline.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${root}/skin/media/js/app.js" type="text/javascript"></script>
<script src="${root}/skin/media/js/index.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script>

    jQuery(document).ready(function() {

        App.init(); // initlayout and core plugins

        Index.init();

        /*Index.initJQVMAP(); // init index page's custom scripts

        Index.initCalendar(); // init index page's custom scripts

        Index.initCharts(); // init index page's custom scripts

        Index.initChat();

        Index.initMiniCharts();*/

        Index.initDashboardDaterange();

        Index.initIntro();

        yearShow();

    });

    function yearShow() {
        var d = new Date();
        var currYear = d.getFullYear();
        $("#curr_year").html(currYear);
    }
    // 打开菜单项
    function openMenuItem(operType) {
        $("#container123").hide();
        switch (operType) {
            case 1:
                $("#mainContent").attr("src", "${root}/admin/mgrUser.bs");
                $("#mainPanel").show();
                break;
            case 2:
                $("#mainContent").attr("src", "${root}/admin/mgrRole.bs");
                $("#mainPanel").show();
                break;
            case 3:
                $("#mainContent").attr("src", "${root}/admin/mgrPermission.bs");
                $("#mainPanel").show();
                break;
            case 4:
                $("#mainContent").attr("src", "${root}/admin/mgrMenu.bs");
                $("#mainPanel").show();
                break;
            case 5:
                $("#mainContent").attr("src", "${root}/admin/mgrQrCode.bs");
                $("#mainPanel").show();
                break;
            case 6:
                $("#mainContent").attr("src", "${root}/admin/mgrSignInType.bs");
                $("#mainPanel").show();
                break;
            case 7:
                $("#mainContent").attr("src", "${root}/admin/mgrSignInRecord.bs");
                $("#mainPanel").show();
                break;
            case 8:
                $("#mainContent").attr("src", "${root}/log/mgrSysLog.bs");
                $("#mainPanel").show();
                break;
            case 9:
                $("#mainContent").attr("src", "${root}/log/mgrSysExceptionLog.bs");
                $("#mainPanel").show();
                break;
            default:
                $("#container123").show();
                $("#mainPanel").hide();
                console.log("enter openMenuItem default.");
                break;
        }
    }

</script>

<!-- END JAVASCRIPTS -->
<script type="text/javascript">  var _gaq = _gaq || [];  _gaq.push(['_setAccount', 'UA-37564768-1']);  _gaq.push(['_setDomainName', 'keenthemes.com']);  _gaq.push(['_setAllowLinker', true]);  _gaq.push(['_trackPageview']);  (function() {    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();</script></body>
<!-- END BODY -->

</html>
