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
    <title>ESignIn--角色管理</title>

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
            <span class="childtetle">角色信息列表</span>
        </div>
    </div>
    <div class="contentBox">
        <div class="blank3"></div>
        <div class="configureBox"  >
            <!-- 查询与导出 -->
            <form id="searchForm" action="${root}/admin/exportRoleDataToExcel.bs" method="post">
                <div style="margin:13px 0;">
                    <label class="g-label l">角色名称 &nbsp;</label>
                    <input type="text" name="name" id="sName" class="g-input l w-180" value="" style="margin-right:50px">
                    <a href="javascript:;" class="g-searchBtn r" id="searchBtn">查询</a>
                    <a href="javascript:;" class="g-searchBtn r" id="clearBtn">清除</a>&nbsp;&nbsp;
                    <div class="clear"></div>
                </div>
            </form>

            <div class="tableTopBtn">
                <a class="addBtn g-searchBtn" href="javascript:void(0)">添 加</a>&nbsp;&nbsp;
                <a class="delSelectBtn g-searchBtn" href="javascript:void(0)">删除选中</a>&nbsp;&nbsp;
                <a class="grantRoleBtn g-searchBtn" href="javascript:void(0)">分配角色</a>&nbsp;&nbsp;
                <a class="grantPermBtn g-searchBtn" href="javascript:void(0)">分配权限</a>&nbsp;&nbsp;
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
                        <th width="15%">角色名称</th>
                        <th width="20%">角色标识</th>
                        <th width="20%">角色描述</th>
                        <th width="20%">创建时间</th>
                        <th width="10%">操 作</th>
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
            $('#sName').val("");
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
                        url: "${root}/role/delRole.bs",
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

        // 显示添加Dialog
        $('.tableTopBtn').delegate('.addBtn', 'click', function(){
            showFormDialog("${root}/page/toRoleAdd.bs", "addForm", "添加角色", 610, 560, {yes: "保 存", yes_before_close:checkAddForm, yes_after_close: null});
        });

        // 显示分配角色框
        $('.tableTopBtn').delegate('.grantRoleBtn', 'click', function(){
            var arr = getCheckedValues();
            if(arr.toString()==null || arr.toString()=='') {
                showMsg("您尚未选中记录！");
                return;
            }
            if(arr.length>1) {
                showMsg("每次只允许选一个角色进行分配！");
                return;
            }
            var roleName = $(":checkbox:checked").parents("tr").find("td[name='rn']").text();
            roleName +="-->角色分配";
            showFormDialog("${root}/role/toRoleGrant.bs?id="+arr.toString(), "grantForm", roleName, 630, 560, {yes: "保 存", yes_before_close:checkGrantForm, yes_after_close: grantCallback});
        });

        // 显示授权框
        $('.tableTopBtn').delegate('.grantPermBtn', 'click', function(){
            var arr = getCheckedValues();
            if(arr.toString()==null || arr.toString()=='') {
                showMsg("您尚未选中记录！");
                return;
            }
            if(arr.length>1) {
                showMsg("每次只允许选一个角色进行分配！");
                return;
            }
            var roleName = $(":checkbox:checked").parents("tr").find("td[name='rn']").text();
            roleName +="-->权限分配";
            showFormDialog("${root}/perm/toPermGrant.bs?id="+arr.toString(), "grantPermForm", roleName, 630, 560, {yes: "保 存", yes_before_close:checkGrantPermForm, yes_after_close: grantPermCallback});
        });

    });

    /*------------------------------------Grant Role Operation Start-----------------------------------------*/
    // 检查授予角色Form
    function checkGrantForm() {
        var ids = getGrantFormCheckedValues();
        return ids;
    }
    // 授予角色回调函数
    function grantCallback(data) {
        console.log("本次分配：" + data);
        var arr = getCheckedValues();
        $.ajax({
            url: "${root}/role/distribution.bs",
            dataType: "json",
            type: "POST",
            data: {
                ids: data.toString(),
                roleId: arr.toString()
            },
            cache: false,
            success: function(data){
                if(data.rspCode==100){ //success
                    showMsg(data.rspMsg);
                    $("[name='checkbox']").removeAttr("checked"); // 取消全选
                } else{
                    showMsg(data.rspMsg);
                }
            },
            error: function(e) {
                showMsg("系统错误！");
            }
        });
    }
    // 获取grantForm复选框选中值
    function getGrantFormCheckedValues() {
        var arr = new Array();
        $('#grantForm').contents().find("[name='checkbox']").each(function () {
            if ($(this).is(':checked')) {
                arr.push($(this).val());
            }
        });
        return arr;
    }
    /*------------------------------------Grant Role Operation End-----------------------------------------*/

    /*------------------------------------Grant Permission Operation Start-----------------------------------------*/
    // 检查授予权限Form
    function checkGrantPermForm() {
        var ids = getGrantPermFormCheckedValues();
        return ids;
    }
    // 获取权限分配框中选中的值
    function getGrantPermFormCheckedValues() {
        var arr = new Array();
        $('#grantPermForm').contents().find("[name='checkbox']").each(function () {
            if ($(this).is(':checked')) {
                arr.push($(this).val());
            }
        });
        return arr;
    }
    // 授予权限回调函数
    function grantPermCallback(data) {
        console.log("本次授予权限：" + data);
        var arr = getCheckedValues(); //get the current role id
        $.ajax({
            url: "${root}/perm/distribution.bs",
            dataType: "json",
            type: "POST",
            data: {
                ids: data.toString(),
                roleId: arr.toString()
            },
            cache: false,
            success: function(data){
                if(data.rspCode==100){ //success
                    showMsg(data.rspMsg);
                    $("[name='checkbox']").removeAttr("checked"); // 取消全选
                } else{
                    showMsg(data.rspMsg);
                }
            },
            error: function(e) {
                showMsg("系统错误！");
            }
        });
    }
    /*------------------------------------Grant Permission Operation End-----------------------------------------*/


    // 检查Form元素
    function checkAddForm() {
        console.log("enter checkAddForm()");
        var name = $('#addForm').contents().find("#name").val();
        if(''==name) {
            showMsg("请填写角色名称！");
            return false;
        }
        var flag = $('#addForm').contents().find("#flag").val();
        if(''==flag) {
            showMsg("请填写角色标识！");
            return false;
        }
        var description = $('#addForm').contents().find("#description").val();
        if(''==description) {
            showMsg("请填写角色描述！");
            return false;
        }

        // 输入有效后提交表单保存数据
        if(isCallbacked) {
            isCallbacked = false;
            $.ajax({
                url: "${root}/role/addRole.bs",
                dataType: "json",
                type: "POST",
                data: {
                    name: name,
                    flag: flag,
                    description: description
                },
                cache: false,
                success: function(data){
                    isCallbacked = true;
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
                    isCallbacked = true;
                    showMsg("系统错误！");
                    return false;
                }
            });
        } //end of if

    } // end of checkAddForm

    function openEdit(id) {
        showFormDialog("${root}/role/toRoleEdit.bs?rid="+id, "editForm", "编辑角色信息", 610, 560, {yes: "保 存", yes_before_close:checkFormForEdit, yes_after_close: null});
    }

    function checkFormForEdit() {
        var name = $('#editForm').contents().find("#name").val();
        if(''==name) {
            showMsg("请填写角色名称！");
            return false;
        }
        var flag = $('#editForm').contents().find("#flag").val();
        if(''==flag) {
            showMsg("请填写角色标识！");
            return false;
        }
        var description = $('#editForm').contents().find("#description").val();
        if(''==description) {
            showMsg("请填写角色描述！");
            return false;
        }

        // 输入有效后提交表单保存数据
        if(isCallbacked) {
            isCallbacked = false;
            $.ajax({
                url: "${root}/role/saveRole.bs",
                dataType: "json",
                type: "POST",
                data: {
                    id: $('#editForm').contents().find("#id").val(),
                    name: name,
                    flag: flag,
                    description: description
                },
                cache: false,
                success: function(data){
                    isCallbacked = true;
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
                    isCallbacked = true;
                    showMsg("系统错误！");
                    return false;
                }
            });
        }
    }

    // 获取分页数据
    var isCallBack = true;
    function getDataHtml(pageNo,pagesize) {
        if (isCallBack) {
            $('#loading').show();
            isCallBack = false;
            $.ajax({
                url: "${root}/admin/roleListData.bs",
                dataType: "json",
                type: "POST",
                cache: false,
                data: {name: $("#sName").val(), page: pageNo, size: pagesize || 10},
                success: function (data) {
                    var html = "";
                    $.each(data.content, function (i, v) {
                        html += '<tr class="' + ((i % 2 == 0) ? 'even' : '') + '">' +
                        '<td>' + (i+1) + '</td>' +
                        '<td><input type="checkbox" name="checkbox" value="' + v.id + '"/></td>' +
                        '<td name="rn">' + v.name + '</td>' +
                        '<td>' + v.flag + '</td>' +
                        '<td>' + v.description + '</td>' +
                        '<td>' + v.createTimeStr + '</td>' +
                        '<td><a class="editBtn" href="javascript:void(0)" onclick="openEdit(\'' + v.id + '\')">编辑</a></td>' +
                        '</tr>';
                    });

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
            $("[name='checkbox']").prop("checked", true);//全选
        } else {
            $("[name='checkbox']").removeAttr("checked");//取消全选
        }
    }

</script>
</body>

</html>