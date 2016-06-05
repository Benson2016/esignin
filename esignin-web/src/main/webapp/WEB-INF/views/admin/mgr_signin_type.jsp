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
    <title>ESignIn--签到类型管理</title>

    <link rel="shortcut icon" href="${root}/commons/img/favicon.ico" />

    <meta name="keywords" content="E签到,ESignIn,easy to sign in"/>
    <meta name="description" content="全球最流行最简便的签到系统" />
    <!-- <link rel="shortcut icon" href="" type="image/x-icon"/> -->
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
            <span class="childtetle">签到类型列表</span>
        </div>
    </div>
    <div class="contentBox">
        <div class="blank3"></div>
        <div class="configureBox"  >
            <!-- 查询与导出 -->
            <form id="searchForm" action="${root}/type/exportTypeData.bs" method="post">
                <div style="margin:13px 0;">
                    <label class="g-label l">类型名称:&nbsp;</label>
                    <input type="text" name="typeName" id="sTypeName" class="g-input l w-180" value="" style="margin-right:10px">

                    <a href="javascript:;" class="g-searchBtn r" id="searchBtn">查询</a>
                    <a href="javascript:;" class="g-searchBtn r" id="clearBtn">清除</a>&nbsp;&nbsp;
                    <div class="clear"></div>
                </div>
            </form>

            <div class="tableTopBtn">
                <a class="addBtn g-searchBtn" href="javascript:void(0)">添 加</a>&nbsp;&nbsp;
                <a class="delSelectBtn g-searchBtn" href="javascript:void(0)">删除选中</a>&nbsp;&nbsp;
                <a class="exportDataBtn" href="javascript:void(0)" title="当无查询条件时，则导出所有数据">导出数据</a>
            </div>
            <input id="orderBy" type="hidden" name="orderBy" value="" />
            <input id="direction" type="hidden" name="direction" value="desc" />
            <!-- 列表开始-->
            <div class="listBox">
                <table class="g-tableList">
                    <thead>
                    <tr>
                        <th width="5%">序号</th>
                        <th width="10%"><input type="checkbox" name="checkAllOrNot" id="checkAllOrNot" onclick="checkAllEvent()">选项</th>
                        <th width="20%">类型ID</th>
                        <th width="50%">签到名称</th>
                        <th width="15%">操 作</th>
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

    // 是否已回调
    var isCallbacked = true;

    $(function () {
        hyxt.init();

        // 查询记录
        $("#searchBtn").click(function(){
            getDataHtml(1, 10);
        });
        // 清除查询条件
        $("#clearBtn").click(function(){
            $('#sTypeName').val("");
        });

        // 导出数据
        $('.tableTopBtn').delegate('.exportDataBtn', 'click', function(){
            $('#searchForm').submit();
        });

        // 显示添加Dialog
        $('.tableTopBtn').delegate('.addBtn', 'click', function(){
            showFormDialog("${root}/type/toTypeAdd.bs", "addForm", "添加签到类别", 610, 300, {yes: "保 存", yes_before_close:checkForm, yes_after_close: addedCallback});
        });
        // 检查Form元素
        function checkForm() {
            console.log("进入checkForm()。");
            var typeName = $('#addForm').contents().find("#typeName").val();
            if(''==typeName) {
                showMsg("请填写类型名称！");
                return false;
            }

            // 输入有效后提交表单保存数据
            if(isCallbacked) {
                isCallbacked = false;
                $.ajax({
                    url: "${root}/type/addType.bs",
                    dataType: "json",
                    type: "POST",
                    data: {typeName: typeName},
                    cache: false,
                    success: function(data){
                        isCallbacked = true;
                        $('#loading').hide();
                        if(data.rspCode==100){ //success
                            getDataHtml(1); //重新加载数据
                            showMsg(data.rspMsg);
                            return true;
                        } else{
                            showMsg(data.rspMsg);
                            return false;
                        }
                    },
                    error: function(e) {
                        $('#loading').hide();
                        isCallbacked = true;
                        showMsg("系统错误！");
                        return false;
                    }
                });
            } //end of if
        }
        // 添加成功后的回调
        function addedCallback(p) {
            console.log("add form already close.");
        }

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
                        url: "${root}/type/delType.bs",
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
                url: "${root}/admin/typeListData.bs",
                dataType: "json",
                type: "POST",
                cache: false,
                data: {typeName: $("#sTypeName").val(), page: pageNo, size: pagesize || 10},
                success: function (data) {
                    var html = "";
                    $.each(data.content, function (i, v) {
                        html += '<tr class="' + ((i % 2 == 0) ? 'even' : '') + '">' +
                                '<td>' + (i+1) + '</td>' +
                                '<td><input type="checkbox" name="checkbox" value="' + v.id + '"/></td>' +
                                '<td>' + v.id + '</td>' +
                                '<td>' + v.typeName + '</td>' +
                                '<td><a class="editBtn" href="javascript:void(0)" onclick="openEdit(\'' + v.id + '\')">编辑</a></td>' +
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

    function openEdit(id) {
        showFormDialog("${root}/type/toTypeEdit.bs?id="+id, "editForm", "编辑签到类别", 610, 300, {yes: "保 存", yes_before_close:checkFormForEdit, yes_after_close: callbackOfEdit});
    }

    // 检查Form元素
    function checkFormForEdit() {
        var typeName = $('#editForm').contents().find("#typeName").val();
        if ('' == typeName) {
            showMsg("类型名称不能为空！");
            return false;
        }

        $('#editForm').on('submit', function() {
            var params = {
                id: $('#editForm').contents().find("#id").val(),
                typeName: $('#editForm').contents().find("#typeName").val()
            };

            if(isCallbacked) {
                isCallbacked = false;
                $.ajax({
                    url: "${root}/type/saveType.bs",
                    dataType: "json",
                    type: "POST",
                    data: params,
                    cache: false,
                    success: function(data){
                        isCallbacked = true;
                        $('#loading').hide();
                        if(data.rspCode==100){ //success
                            getDataHtml(1); //重新加载数据
                            showMsg(data.rspMsg);
                            return true;
                        } else{
                            showMsg(data.rspMsg);
                            return false;
                        }
                    },
                    error: function(e) {
                        $('#loading').hide();
                        isCallbacked = true;
                        showMsg("系统错误！");
                        return false;
                    }
                });
            } //end of if

        });

        $('#editForm').submit();
        //return true;
    }

    function callbackOfEdit() {
        getDataHtml(1); //重新加载数据
    }
</script>
</body>

</html>