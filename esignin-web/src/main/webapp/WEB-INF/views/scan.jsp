<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
    <title>E Sign In System</title>
    <link rel="shortcut icon" href="${root}/commons/img/favicon.ico"/>
    <style>
        .scan {
            align-content: center;
            width: 100%;
            height: 100%;
        }
        .scan .scan-content {
            align-content: center;
            width:300px;
            height:360px;
            margin-top: 0px;
            margin-left: 0px;
            padding-top: 10%;
            padding-left: 38%;
        }

    </style>
</head>
<body>
    <div class="scan">
        <div class="scan-content">
            <h3>请扫描以下二维码参与精彩活动</h3>
            <img src="${root}/code/getQrCode.bs?businessId=${businessId}" width="300" height="300" alt="请使用微信扫一扫">
        </div>
    </div>
</body>
</html>
