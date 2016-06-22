<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
    <title>ESignIn--角色权限列表</title>

    <link rel="shortcut icon" href="${root}/commons/img/favicon.ico"/>

    <meta name="keywords" content="E签到,ESignIn,easy to sign in"/>
    <meta name="description" content="全球最流行最简便的签到系统"/>
    <link href="${root}/resources/css/global.css" rel="stylesheet" type="text/css" />
    <link href="${root}/resources/css/jquery.placeholder.css" rel="stylesheet" type="text/css" />

    <link href="${root}/resources/css/page.css" rel="stylesheet" type="text/css" />
    <link href="${root}/resources/plugins/jquery-datetimepicker/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
        body {background-color: #ffffff; }
        a:hover { color:#f74e37; }
        .screen .headBox { /*padding-bottom:25px; background:#e3e3e3;*/ height:64px; position:fixed; width:100%; left:0; top:0; z-index:8;}
        .screen .headBox .topBox { overflow:hidden; height:84px; background:#fff; padding:0 40px 0 20px;}
        .screen .headBox .childicon { float:left; width:45px; height:45px; overflow:hidden; margin-top:20px;}
        .screen .headBox .childicon img { float:left;  width:45px; height:45px;}
        .screen .headBox .childtetle{ float:left; height:45px; line-height:45px; overflow:hidden; padding:20px 0 0 24px; font-size:24px; color:#666;}
        .screen .headBox .childclose { float:right; overflow:hidden; width:34px; height:34px; margin:25px 0 0 28px; background:url(${root}/commons/img/close.png);}
        .screen .headBox .childclose:hover{ background:url(${root}/commons/img/closehover.png);}
        .screen .headBox .childbut{ float:right;overflow:hidden; margin-top:20px;margin-right:19px}

        .screen .contentBox{ overflow:hidden; min-height:500px; background:#fff; padding: 10px 20px 20px 20px;}

        .g-tableList{font-size:14px;table-layout:auto;}
        .g-tableList thead th{font-size:14px;color:#666;border-bottom:1px solid #e5e5e5;}
        .g-tableList tbody td{font-size:14px;border-bottom:1px solid #e5e5e5;color:#666;line-height:20px;}
        .g-tableList tbody tr:nth-child(2n-1) td { background:#f7f7f7; }
        .g-tableList tbody img { border:1px solid #cecece; }

        .g-tableList .thSort { cursor:ns-resize; }
        .g-tableList .thSort span {background:url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAkAAAALCAMAAACah1cpAAAAM1BMVEX///+Dg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4Ovn3wBAAAAEHRSTlMAJC02P0JOUVpgeIfq8Pb57Ter4AAAAD9JREFUeF5Fy1sKgDAQQ9GMWh9ja+/+VysZBM9XCIns0CdJlR04HdYHmE2Kjo3QbxlYD6lN7za3V33LTepTzQuA3AJblVbHMwAAAABJRU5ErkJggg==) /*${root}/commons/img/toggle-ico.png*/ right 5px no-repeat;_background:url(${root}/commons/img/toggle-ico.png) right 5px no-repeat;display:inline-block;padding-right:16px;}
        .g-tableList .thSort.desc span{background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAkAAAALBAMAAABfd7ooAAAAJ1BMVEX///+Dg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4PIqRqDAAAADHRSTlMA+VrwJEItP2Dqh3i2e/DgAAAAM0lEQVR4XnWKsQ0AIAzDsrDwQJcOPYVf+g6PIXwUFRsDGSJZtn5LWEPNsYJJ1Pd9TbzhAdMxBnYi4KGKAAAAAElFTkSuQmCC);_background-image: url(${root}/commons/img/toggle-ico1.png);}
        .g-tableList .thSort.asc span{background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAkAAAALBAMAAABfd7ooAAAAJ1BMVEX///+Dg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4ODg4PIqRqDAAAADHRSTlMAJHhRWjb2+UJO6vCS7BriAAAAL0lEQVR4Xm3EsQ0AEBRAwReJXm0KIxjIAIYxjdYbSn7NFcdPi9KOlx3ycELVU3hcwU8G34G0RpYAAAAASUVORK5CYII=);_background-image: url(${root}/commons/img/toggle-ico2.png);}

        .g-dialog .g-dialog_tit{background: #f74e37;}
        input[type=text]:focus, input[type=email]:focus, input[type=password]:focus, textarea:focus, input[type=number]:focus, input[type=tel]:focus {border: 1px solid #f74e37;}
        <%--
        .pagebar_numlist .pagebar_numlist_current,.pagebar_numlist a:hover{background-color:#f74e37;border:1px solid #f74e37}
        .pagebar_numlist button:hover {background: #f74e37;color: #FFF;}
        --%>
        #loading { display: none; position: fixed; top:0; left:0; width:100%; height:100%; z-index:9999; background:rgba(0, 0, 0, .5) url(${root}/commons/img/loading.gif) no-repeat center; }
    </style>
</head>
<body>
<div class="screen">
    <div class="contentBox">
        <div class="blank3"></div>
        <div class="configureBox">
            <!-- 查询
            <form id="searchForm" action="/" method="post">
                <div style="margin:5px 0;">
                    <label class="g-label l">用户名称 &nbsp;</label>
                    <input type="text" name="userName" id="sUserName" class="g-input l w-180" value="" style="margin-right:50px">
                    <div class="blank3"></div>
                    <label class="g-label l">用户姓名 &nbsp;</label>
                    <input type="text" name="fullName" id="sFullName" class="g-input l w-180" value="" style="margin-right:50px">
                    <a href="javascript:;" class="g-searchBtn r" id="searchBtn">查询</a>
                    <a href="javascript:;" class="g-searchBtn r" id="clearBtn">清除</a>&nbsp;&nbsp;
                    <div class="clear"></div>
                </div>
            </form>
            -->
            <input type="hidden" id="roleId" name="roleId" value="${roleId}"/>
            <!-- 列表开始-->
            <div class="listBox">
                <table class="g-tableList">
                    <thead>
                    <tr>
                        <th width="10%">序号</th>
                        <th width="15%">
                            <input type="checkbox" name="checkAllOrNot" id="checkAllOrNot"onclick="checkAllEvent()">选项
                        </th>
                        <th width="35%">权限名称</th>
                        <th width="40%">权限描述</th>
                    </tr>
                    </thead>
                    <tbody id="data_body"></tbody>
                </table>
                <%--
                <div class="pagebar" id="pagebar"></div>
                --%>
            </div>

        </div>
    </div>
</div>
<div id="loading"></div>

<script src="${root}/resources/js/jquery.min.js"></script>
<script src="${root}/resources/js/plus/placeholder.min.js"></script>
<script src="${root}/resources/js/plus/graphic/highcharts.js"></script>
<script src="${root}/resources/js/hyxt.js"></script>
<script src="${root}/resources/js/dialog.js"></script>
<%--
<script src="${root}/resources/js/PageBarNumList.js"></script>
--%>
<script src="${root}/resources/plugins/jquery-datetimepicker/jquery.datetimepicker.js"></script>
<script src="${root}/commons/js/esignInDialog.js"></script>

<script>
    $(function () {
        hyxt.init();
        <%--
        // 查询记录
        $("#searchBtn").click(function () {
            getDataHtml(1, 10);
        });
        // 清除查询条件
        $("#clearBtn").click(function(){
            $('#sUserName').val("");
            $('#sFullName').val("");
        });
        --%>
    });

    var rpiArr = eval(${rpis});
    var isCallBack = true;
    function getDataHtml(pageNo, pagesize) {
        if (isCallBack) {
            isCallBack = false;
            $.ajax({
                url: "${root}/perm/getData.bs",
                dataType: "json",
                type: "POST",
                cache: false,
                data: {
                    page: pageNo,
                    size: pagesize || 10
                },
                success: function (data) {
                    var html = "";
                    $.each(data, function (i, v) {
                        html += '<tr class="' + ((i % 2 == 0) ? 'even' : '') + '">' +
                                '<td>' + (i + 1) + '</td>' +
                                '<td><input type="checkbox" name="checkbox" value="' + v.id + '"/></td>' +
                                '<td>' + v.name + '</td>' +
                                '<td>' + v.description + '</td>' +
                                '</tr>';
                    });

                    $("#data_body").html(html);
                    <%--
                    document.getElementById('pagebar').innerHTML = PageBarNumList.getPageBar(pageNo, data.totalPages, 3, 'getDataHtml', pagesize || 1000, true);
                    --%>
                    isCallBack = true;
                    initChecked();
                },
                error: function (e) {
                    isCallBack = true;
                    console.log(e);
                    showMsg("系统错误,请重试!");
                }
            });
        }
    }
    getDataHtml(1, 10);

    // 获取多个checkbox选中项
    function getCheckedValues() {
        var arr = new Array();
        $("[name='checkbox']").each(function () {
            if ($(this).is(':checked')) {
                arr.push($(this).val());
            }
        });
        return arr;
    }
    // 全选按钮事件
    function checkAllEvent() {
        if ($("#checkAllOrNot").is(':checked')) {
            $("[name='checkbox']").prop("checked", 'true');//全选
        } else {
            $("[name='checkbox']").removeAttr("checked");//取消全选
        }
    }
    // 初始化复选框选中
    function initChecked() {
        $("[name='checkbox']").each(function(i,v) {
            //console.log((i+1) +"-->"+ v.value);
            v.checked = hasRole(v.value);
        });
    }
    function hasRole(v) {
        if(null==rpiArr || ''==rpiArr.toString()){
            return false;
        }
        var b = false;
        for(var i=0; i<rpiArr.length; i++) {
            if(v == rpiArr[i].permissionId) {
                b = true;
                break;
            }
        }
        return b;
    }
</script>
</body>

</html>
