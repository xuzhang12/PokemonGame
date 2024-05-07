<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎来到皇家斗兽场</title>
    <%--静态包含base标签，css样式，jQuery文件--%>
    <%@include file="/pages/common/head.jsp" %>
    <script>
        // window.addEventListener('DOMContentLoaded', function() {
        //     var bgmusic = document.getElementById('bgmusic');
        //     if (bgmusic.paused) {
        //         bgmusic.play();
        //     }
        // });
        // document.addEventListener('click', function(event) {
        //     if (event.target.tagName === 'A' && event.target.getAttribute('href')) {
        //         event.preventDefault();
        //         var url = event.target.getAttribute('href');
        //         fetch(url)
        //             .then(response => response.text())
        //             .then(data => {
        //                 document.getElementById('content').innerHTML = data;
        //             });
        //     }
        // });
    </script>
</head>
<body>
<div id="content">
    <h1 style="margin-top: 100px;">
        欢迎来到皇家斗兽场
    </h1>
    <%--<audio controls autoplay="autoplay" loop="loop" hidden="hidden">--%>
    <%--    <source src="static/mp3/精灵宝可梦.mp3" type="audio/mpeg">--%>
    <%--</audio>--%>
<%--    <audio id="bgmusic" loop autoplay>--%>
<%--        <source src="static/mp3/精灵宝可梦.mp3" type="audio/mpeg">--%>
<%--    </audio>--%>
    <br><br>
    <a href="pages/user/login.jsp">玩家登录</a>
    &nbsp;&nbsp;
    <a href="pages/user/regist.jsp">玩家注册</a>
    &nbsp;&nbsp;
    <a href="pages/manager/login.jsp">管理员登录</a>
</div>
</body>
</html>