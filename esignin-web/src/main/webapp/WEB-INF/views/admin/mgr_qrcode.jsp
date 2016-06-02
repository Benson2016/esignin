<%--
  Created by IntelliJ IDEA.
  User: benson
  Date: 16/5/30
  Time: 22:36
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
    <title>ESignIn--二维码管理</title>

    <link rel="shortcut icon" href="${root}/commons/img/favicon.ico" />

    <meta name="keywords" content="E签到,ESignIn,easy to sign in"/>
    <meta name="description" content="全球最流行最简便的签到系统" />
    <!-- <link rel="shortcut icon" href="" type="image/x-icon"/> -->
    <link href="${root}/resources/css/global.css" rel="stylesheet" type="text/css" />
    <link href="${root}/resources/css/jquery.placeholder.css" rel="stylesheet" type="text/css" />
    <link href="${root}/resources/css/discount.css" rel="stylesheet" type="text/css" />
    <link href="${root}/resources/css/page.css" rel="stylesheet" type="text/css" />

    <%@ include file="/commons/style.jsp" %>
</head>
<body>
<div class="screen">
    <div class="headBox">
        <div class="topBox">
            <span class="childicon"><img src="${root}/commons/img/icon.jpg"></span>
            <span class="childtetle">二维码列表</span>
        </div>
    </div>
    <div class="contentBox">
        <div class="blank30"></div>
        <div class="configureBox"  >
            <!-- 查询与导出 -->
            <form id="searchForm" action="${root}/code/exportQrCodeData.bs" method="post">
                <div style="margin:13px 0;">
                    <label class="g-label l">业务主题 &nbsp;</label>
                    <input type="text" name="title" id="sTitle" class="g-input l w-180" value="" style="margin-right:50px">
                    <label class="g-label l">签到类型 &nbsp;</label>
                    <input type="hidden" name="signInType" id="sSignInType" class="g-input l w-180" value="" style="margin-right:50px">
                    <select id="siTypeSelect">
                        <option value="">不限</option>
                        <c:if test="${null!=signInTypeList}">
                            <c:forEach var="sitype" items="${signInTypeList}">
                                <option value="${sitype.id}">${sitype.typeName}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                    <a href="javascript:;" class="g-searchBtn r" id="searchBtn">查询</a>
                    <div class="clear"></div>
                    <input type="hidden" id="exportType" name="exportType" value="4">
                </div>
            </form>

            <div class="tableTopBtn">
                <a class="delSelectBtn" href="javascript:void(0)">删除选中</a>&nbsp;&nbsp;
                <a class="exportDataBtn" href="javascript:void(0)" title="当无查询条件时，则导出所有数据">导出数据</a>
            </div>
            <input id="orderBy" type="hidden" name="orderBy" value="" />
            <input id="direction" type="hidden" name="direction" value="desc" />
            <!-- 列表开始-->
            <div class="listBox">
                <table class="g-tableList">
                    <thead>
                    <tr>
                        <th width="3%">序号</th>
                        <th width="7%"><input type="checkbox" name="checkAllOrNot" id="checkAllOrNot" onclick="checkAllEvent()">选项</th>
                        <th width="15%">业务主题</th>
                        <th width="15%">业务描述</th>
                        <th width="10%">签到类型</th>
                        <th width="20%">图片内容</th>
                        <th width="12%">创建时间</th>
                        <th width="12%">失效时间</th>
                        <th width="6%">是否有效</th>
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
<script src="${root}/resources/js/plus/date/WdatePicker.js"></script>
<script src="${root}/resources/js/plus/placeholder.min.js"></script>
<script src="${root}/resources/js/plus/graphic/highcharts.js"></script>
<script src="${root}/resources/js/hyxt.js"></script>
<script src="${root}/resources/js/dialog.js"></script>
<script src="${root}/resources/js/PageBarNumList.js"></script>

<script>
    // 是否已回调
    var isCallbacked = true;

    $(function () {
        hyxt.init();

        // 查询记录
        $("#searchBtn").click(function(){
            getDataHtml(1, 10);
        });

        //导出数据
        $('.tableTopBtn').delegate('.exportDataBtn', 'click', function(){
            $('#searchForm').submit();
        });

        //删除选中
        $('.tableTopBtn').delegate('.delSelectBtn', 'click', function(){
            var arr = getCheckedValues();
            if(arr.toString()==null || arr.toString()=='') {
                showMsg("您尚未选中记录！");
                return;
            }
            if(isCallbacked) {
                isCallbacked = false;
                //弹窗确认
                showConfirm("确定删除选中记录？", function() {
                    $('#loading').show();
                    $.ajax({
                        url: "${root}/code/delQrCode.bs",
                        dataType: "json",
                        type: "POST",
                        data: {ids: arr.toString()},
                        cache: false,
                        success: function(data){
                            isCallbacked = true;
                            $('#loading').hide();
                            if(data.rspCode==100){ //success
                                showMsg(data.rspMsg);
                                getDataHtml(1); //重新加载数据
                            } else{
                                showMsg(data.rspMsg);
                            }
                        },
                        error: function(e) {
                            $('#loading').hide();
                            isCallbacked = true;
                            showMsg("系统错误！");
                        }
                    });
                });
                isCallbacked = true;
            }
        });

    });


    var isCallBack = true;

    function getDataHtml(pageNo,pagesize) {
        if (isCallBack) {
            $('#loading').show();
            isCallBack = false;
            $.ajax({
                url: "${root}/admin/qrCodeListData.bs",
                dataType: "json",
                type: "POST",
                cache: false,
                data: {title: $("#sTitle").val(), page: pageNo, size: pagesize || 10},
                success: function (data) {
                    var html = "";
                    $.each(data.content, function (i, v) {
                        html += '<tr class="' + ((i % 2 == 0) ? 'even' : '') + '">' +
                                '<td>' + (i+1) + '</td>' +
                                '<td><input type="checkbox" name="checkbox" value="' + v.id + '"/></td>' +
                                '<td>' + v.title + '</td>' +
                                '<td>' + v.description + '</td>' +
                                '<td>' + v.signInType + '</td>' +
                                '<td>' + v.image + '</td>' +
                                '<td>' + v.createTimeStr + '</td>' +
                                '<td>' + v.effectiveTimeEndStr + '</td>' +
                                '<td>' + fmtIsValid(v.isValid) + '</td>' +
                                '</tr>';
                    })

                    $('#loading').hide();
                    $("#data_body").html(html);
                    document.getElementById('pagebar').innerHTML = PageBarNumList.getPageBar(pageNo, data.totalPages, 3, 'getDataHtml', pagesize || 10, true);
                    isCallBack = true;
                },
                error: function (e) {
                    $('#loading').hide();
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
        $("[name='checkbox']").each(function(){
            if ($(this).is(':checked')) {
                arr.push($(this).val());
            }
        });
        return arr;
    }

    function checkAllEvent() {
        if($("#checkAllOrNot").is(':checked')) {
            $("[name='checkbox']").attr("checked",'true');//全选
        } else {
            $("[name='checkbox']").removeAttr("checked");//取消全选
        }
    }

    function fmtIsValid(v) {
        return 1==v ? "<font color='green'>是</font>" : "<font color='red'>否</font>";
    }
</script>
</body>

</html>