<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户登录页面</title>
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
<%--<audio id="bgmusic" loop autoplay>--%>
<%--    <source src="/PokemonGame_war/static/mp3/精灵宝可梦.mp3" type="audio/mpeg">--%>
<%--</audio>--%>
<span class="errorMsg">
    ${requestScope.msg}
    </span>
<div class="main">
    <div class="form">
        <form action="loginOrRegistServlet" method="post">
            <input type="hidden" name="action" value="userLogin">
            <label>用户名:</label>
            <input autocomplete="off" class="itxt" id="username"
                   name="username" placeholder="请输入用户名" tabindex="1" type="text"
                   value="${requestScope.username}"/>
            <br/>
            <br/>
            <label>密码:</label><input autocomplete="off" class="itxt" id="password"
                                       name="password" placeholder="请输入密码" tabindex="1" type="password"/>
            <br/>
            <br/>
            <input id="sub_btn" type="submit" value="登录"/>
            &nbsp;&nbsp;
            <a href="index.jsp">返回</a>
        </form>
    </div>
</div>
</body>
</html>