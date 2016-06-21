<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
    <title>ESignIn--角色用户列表</title>

    <link rel="shortcut icon" href="${root}/commons/img/favicon.ico"/>

    <meta name="keywords" content="E签到,ESignIn,easy to sign in"/>
    <meta name="description" content="全球最流行最简便的签到系统"/>
    <link href="${root}/resources/css/global.css" rel="stylesheet" type="text/css" />
    <link href="${root}/resources/css/jquery.placeholder.css" rel="stylesheet" type="text/css" />
    <link href="${root}/resources/css/discount.css" rel="stylesheet" type="text/css" />
    <link href="${root}/resources/css/page.css" rel="stylesheet" type="text/css" />
    <link href="${root}/resources/plugins/jquery-datetimepicker/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />
    <%@ include file="/commons/style.jsp" %>

</head>
<body>
<div class="screen">
    <div class="headBox">
        <div class="topBox">
            <span class="childicon"><img src="${root}/commons/img/icon.jpg"></span>
            <span class="childtetle">用户信息列表</span>
        </div>
    </div>
    <div class="contentBox">
        <div class="blank3"></div>
        <div class="configureBox">
            <!-- 查询与导出 -->
            <form id="searchForm" action="${root}/admin/exportUserDataToExcel.bs" method="post">
                <div style="margin:13px 0;">
                    <label class="g-label l">用户名称 &nbsp;</label>
                    <input type="text" name="userName" id="sUserName" class="g-input l w-180" value="" style="margin-right:50px">
                    <a href="javascript:;" class="g-searchBtn r" id="searchBtn">查询</a>
                    <a href="javascript:;" class="g-searchBtn r" id="clearBtn">清除</a>&nbsp;&nbsp;
                    <div class="clear"></div>
                </div>
            </form>

            <div class="tableTopBtn">
                <a class="addBtn g-searchBtn" href="javascript:void(0)">添 加</a>&nbsp;&nbsp;
                <a class="delSelectBtn g-searchBtn" href="javascript:void(0)">删除选中</a>
                <a class="exportDataBtn" href="javascript:void(0)" title="当无查询条件时，则导出所有数据">导出数据</a>
            </div>
            <input id="orderBy" type="hidden" name="orderBy" value=""/>
            <input id="direction" type="hidden" name="direction" value="desc"/>
            <!-- 列表开始-->
            <div class="listBox">
                <table class="g-tableList">
                    <thead>
                    <tr>
                        <th width="5%">序号</th>
                        <th width="10%">
                            <input type="checkbox" name="checkAllOrNot" id="checkAllOrNot"onclick="checkAllEvent()">选项
                        </th>
                        <th width="85%">用户名称</th>
                    </tr>
                    </thead>
                    <tbody id="data_body"></tbody>
                </table>
                <div class="pagebar" id="pagebar"></div>
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
<script src="${root}/resources/js/PageBarNumList.js"></script>
<script src="${root}/resources/plugins/jquery-datetimepicker/jquery.datetimepicker.js"></script>
<script src="${root}/commons/js/esignInDialog.js"></script>

<script>
    $(function () {
        hyxt.init();

        // 查询记录
        $("#searchBtn").click(function () {
            getDataHtml(1, 10);
        });

        // 清除查询条件
        $("#clearBtn").click(function(){
            $('#sUserName').val("");
        });

    });


    var isCallBack = true;

    function getDataHtml(pageNo, pagesize) {
        if (isCallBack) {
            isCallBack = false;
            $.ajax({
                url: "${root}/admin/userListData.bs",
                dataType: "json",
                type: "POST",
                cache: false,
                data: {
                    userName: $("#sUserName").val(),
                    page: pageNo,
                    size: pagesize || 10
                },
                success: function (data) {
                    var html = "";
                    $.each(data.content, function (i, v) {
                        html += '<tr class="' + ((i % 2 == 0) ? 'even' : '') + '">' +
                                '<td>' + (i + 1) + '</td>' +
                                '<td><input type="checkbox" name="checkbox" value="' + v.id + '"/></td>' +
                                '<td>' + v.userName + '</td>' +
                                '</tr>';
                    })

                    $("#data_body").html(html);
                    document.getElementById('pagebar').innerHTML = PageBarNumList.getPageBar(pageNo, data.totalPages, 3, 'getDataHtml', pagesize || 10, true);
                    isCallBack = true;
                },
                error: function (e) {
                    isCallBack = true;
                    console.log(e);
                    showMsg("系统错误,请重试!");
                }
            });
        }
    }
    getDataHtml(1);

    //获取多个checkbox选中项
    function getCheckedValues() {
        var arr = new Array();
        $("[name='checkbox']").each(function () {
            if ($(this).is(':checked')) {
                arr.push($(this).val());
            }
        });
        return arr;
    }

    function checkAllEvent() {
        if ($("#checkAllOrNot").is(':checked')) {
            $("[name='checkbox']").attr("checked", 'true');//全选
        } else {
            $("[name='checkbox']").removeAttr("checked");//取消全选
        }
    }


</script>
</body>

</html>
