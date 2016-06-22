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
    <title>ESignIn--用户管理</title>

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
                    <label class="g-label l">手机号码 &nbsp;</label>
                    <input type="text" name="mobile" id="sMobile" class="g-input l w-180" value=""
                           style="margin-right:50px">
                    <label class="g-label l">用户姓名 &nbsp;</label>
                    <input type="text" name="fullName" id="sFullName" class="g-input l w-180" value=""
                           style="margin-right:50px">
                    <label class="g-label l">用户名称 &nbsp;</label>
                    <input type="text" name="userName" id="sUserName" class="g-input l w-180" value=""
                           style="margin-right:50px">
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
                        <th width="8%">
                            <input type="checkbox" name="checkAllOrNot" id="checkAllOrNot"onclick="checkAllEvent()">选项
                        </th>
                        <th width="15%">用户名称</th>
                        <th width="17%">用户姓名</th>
                        <th width="12%">手机号码</th>
                        <th width="17%">创建时间</th>
                        <th width="8%">有效状态</th>
                        <th width="10%">来源</th>
                        <th width="8%">操 作</th>
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
        $("#searchBtn").click(function () {
            getDataHtml(1, 10);
        });

        // 清除查询条件
        $("#clearBtn").click(function(){
            $('#sMobile').val("");
            $('#sFullName').val("");
            $('#sUserName').val("");
        });

        //导出数据
        $('.tableTopBtn').delegate('.exportDataBtn', 'click', function () {
            $('#searchForm').submit();
        });

        //删除选中
        $('.tableTopBtn').delegate('.delSelectBtn', 'click', function () {
            var arr = getCheckedValues();
            if (arr.toString() == null || arr.toString() == '') {
                showMsg("您尚未选中记录！");
                return;
            }
            if (isCallbacked) {
                isCallbacked = false;
                //弹窗确认
                showConfirm("确定删除选中记录？", function () {
                    $('#loading').show();
                    $.ajax({
                        url: "${root}/user/delUser.bs",
                        dataType: "json",
                        type: "POST",
                        data: {ids: arr.toString()},
                        cache: false,
                        success: function (data) {
                            isCallbacked = true;
                            $('#loading').hide();
                            if (data.rspCode == 100) { //success
                                showMsg(data.rspMsg);
                                getDataHtml(1); //重新加载数据
                            } else {
                                showMsg(data.rspMsg);
                            }
                        },
                        error: function (e) {
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
            showFormDialog("${root}/page/toUserAdd.bs", "addForm", "添加用户", 610, 560, {yes: "保 存", yes_before_close:checkAddForm, yes_after_close: null});
        });


    });

    function checkPhone(phone){
        if(!(/^1[3|4|5|7|8]\d{9}$/.test(phone))){
            showMsg("请输入正确的手机号！");
            return false;
        }
        return true;
    }

    var reg = /^(0|([1-9]\d?)|(1[01]\d)|(120))(\.\d*)?$/;
    function checkAge(age) {
        if (!reg.test(age)) {
            showMsg("请输入年龄范围0~120");
            return false;
        }
        return true;
    }

    // 检查Form元素
    function checkAddForm() {
        console.log("enter checkAddForm()");
        var userName = $('#addForm').contents().find("#userName").val();
        if(''==userName) {
            showMsg("请填写用户名！");
            return false;
        }
        var fullName = $('#addForm').contents().find("#fullName").val();
        if(''==fullName) {
            showMsg("请填写姓名！");
            return false;
        }
        var mobile = $('#addForm').contents().find("#mobile").val();
        if(''==mobile) {
            showMsg("请填写手机号码！");
            return false;
        }
        if (!checkPhone(mobile)) {
            return false;
        }
        var age = $('#addForm').contents().find("#age").val();
        if (!checkAge(age)) {
            return false;
        }

        // 输入有效后提交表单保存数据
        if(isCallbacked) {
            isCallbacked = false;
            $.ajax({
                url: "${root}/user/addUser.bs",
                dataType: "json",
                type: "POST",
                data: {
                    userName: userName,
                    fullName: fullName,
                    mobile: mobile,
                    age: age,
                    sex: $('#addForm').contents().find("#sex").val(),
                    email: $('#addForm').contents().find("#email").val(),
                    flag: $('#addForm').contents().find("#flag").val()
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
        showFormDialog("${root}/user/toUserEdit.bs?uid="+id, "editForm", "编辑用户信息", 610, 560, {yes: "保 存", yes_before_close:checkFormForEdit, yes_after_close: null});
    }

    function checkFormForEdit() {
        var userName = $('#editForm').contents().find("#userName").val();
        if(''==userName) {
            showMsg("请填写用户名！");
            return false;
        }
        var fullName = $('#editForm').contents().find("#fullName").val();
        if(''==fullName) {
            showMsg("请填写姓名！");
            return false;
        }
        var mobile = $('#editForm').contents().find("#mobile").val();
        if(''==mobile) {
            showMsg("请填写手机号码！");
            return false;
        }
        if (!checkPhone(mobile)) {
            return false;
        }
        var age = $('#editForm').contents().find("#age").val();
        if (!checkAge(age)) {
            return false;
        }

        // 输入有效后提交表单保存数据
        if(isCallbacked) {
            isCallbacked = false;
            $.ajax({
                url: "${root}/user/saveUser.bs",
                dataType: "json",
                type: "POST",
                data: {
                    userName: userName,
                    fullName: fullName,
                    mobile: mobile,
                    age: age,
                    sex: $('#editForm').contents().find("#sex").val(),
                    email: $('#editForm').contents().find("#email").val(),
                    flag: $('#editForm').contents().find("#flag").val(),
                    id: $('#editForm').contents().find("#id").val(),
                    isValid: $('#editForm').contents().find("#isValid").val(),
                    createTime: $('#editForm').contents().find("#createTime").val(),
                    password: $('#editForm').contents().find("#password").val()
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


    var isCallBack = true;

    function getDataHtml(pageNo, pagesize) {
        if (isCallBack) {
            $('#loading').show();
            isCallBack = false;
            $.ajax({
                url: "${root}/admin/userListData.bs",
                dataType: "json",
                type: "POST",
                cache: false,
                data: {
                    mobile: $("#sMobile").val(),
                    fullName: $("#sFullName").val(),
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
                                '<td>' + v.fullName + '</td>' +
                                '<td>' + v.mobile + '</td>' +
                                '<td>' + v.createTimeStr + '</td>' +
                                '<td>' + fmtIsValid(v.isValid) + '</td>' +
                                '<td>' + fmtOrigin(v.origin) + '</td>' +
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
        $("[name='checkbox']").each(function () {
            if ($(this).is(':checked')) {
                arr.push($(this).val());
            }
        });
        return arr;
    }

    function checkAllEvent() {
        if ($("#checkAllOrNot").is(':checked')) {
            $("[name='checkbox']").prop("checked", 'true');//全选
        } else {
            $("[name='checkbox']").removeAttr("checked");//取消全选
        }
    }

    // format valid value
    function fmtIsValid(v) {
        return 1==v ? "<font color='green'>是</font>" : "<font color='red'>否</font>";
    }
    // format origin
    function fmtOrigin(v) {
        var ret = '';
        switch (v) {
            case 1:
                ret = '后台添加';
                break;
            case 2:
                ret = '<font color="orange">后台注册</font>';
                break;
            case 3:
                ret = '<font color="blue">手机注册</font>';
                break;
            default:
                ret = '未知';
                break;
        }
        return ret;
    }

</script>
</body>

</html>
