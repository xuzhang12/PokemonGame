<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户注册页面</title>
    <%--静态包含base标签，css样式，jQuery文件--%>
    <%@include file="/pages/common/head.jsp" %>
    <script>
        // 给提交按钮绑定单击事件，检测各个输入是否合法
        $(function () {
            $("#sub_btn").click(function () {
                var $username = $("#username").val();
                var $password = $("#password").val();
                var $repwd = $("#repwd").val();
                var patt1 = /^\w{5,12}$/;
                if (!patt1.test($username)) {
                    $("span.errorMsg").text("用户名不合法，用户名必须由字母，数字，下划线组成，且长度在5-12之间");
                    return false;
                } else $("span.errorMsg").text("");
                if (!patt1.test($password)) {
                    $("span.errorMsg").text("密码不合法，密码必须由字母，数字，下划线组成，且长度在5-12之间");
                    return false;
                } else $("span.errorMsg").text("");
                if ($password !== $repwd) {
                    $("span.errorMsg").text("前后密码不一致");
                    return false;
                } else $("span.errorMsg").text("");
                var $email = $("#email").val();
                var patt2 = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                if (!patt2.test($email)) {
                    $("span.errorMsg").text("邮箱格式错误！");
                    return false;
                } else $("span.errorMsg").text("");
            })
        })
    </script>
</head>
<body>
<span class="errorMsg">
    ${requestScope.msg}
</span>
<div class="main" style="margin-top: -35px;">
    <div class="form">
        <form action="loginOrRegistServlet" method="post">
            <input type="hidden" name="action" value="userRegist">
            <label>用户名:</label>
            <input autocomplete="off" class="itxt" id="username"
                   name="username" placeholder="请输入用户名" tabindex="1" type="text" maxlength="12"
                   value="${sessionScope.username}"/>
            <br/>
            <br/>
            <label>密码:</label>
            <input autocomplete="off" class="itxt" id="password"
                   name="password" placeholder="请输入密码" tabindex="1" type="password"/>
            <br/>
            <br/>
            <label>确认密码:</label>
            <input autocomplete="off" class="itxt" id="repwd"
                   name="repwd" placeholder="请确认密码" tabindex="1" type="password"/>
            <br/>
            <br/>
            <label>邮箱:</label>
            <input autocomplete="off" class="itxt" id="email"
                   name="email" placeholder="请输入邮箱" tabindex="1" type="email"
                   value="${sessionScope.email}"/>
            <br/>
            <br/>
            <input id="sub_btn" type="submit" value="注册"/>
            &nbsp;&nbsp;
            <a href="index.jsp">返回</a>
        </form>
    </div>
</div>
</body>
</html>