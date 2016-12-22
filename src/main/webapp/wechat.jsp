<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>index</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta http-equiv="Access-Control-Allow-Origin" content="https://wx.qq.com/">
    <!--
        <link rel="stylesheet" type="text/css" href="styles.css">
        -->
    <link rel="stylesheet" href="<%=basePath%>/weui-master/dist/style/weui.min.css">
    <style type="text/css">
        #login {
            position: fixed;
            width: 100%;
            left: 0;
            bottom: 0;
        }

        #QR_div {
            text-align: center;
        }
    </style>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/wechat.js"></script>
</head>
<body>
<!-- <div id="QR_div">
    <img id="QR_img" alt="扫描二维码" src="@{imgPath}">
    <p id="QR_p">扫描二维码登录</p>
</div> -->
<div id="login">
    <a href="javascript:login()" id="login_btn" class="weui_btn weui_btn_primary">登录微信</a>
</div>
<form action="/wechat/init.do" id="init_form"/>
</body>
</html>

