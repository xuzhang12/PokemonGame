<%--
  Created by IntelliJ IDEA.
  User: wuming
  Date: 2023/10/24
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员登录界面</title>
    <%--静态包含base标签，css样式，jQuery文件--%>
    <%@include file="/pages/common/head.jsp" %>
</head>
<body>
<span class="errorMsg">
    ${requestScope.msg}
</span>
<div class="main">
    <br>
    <form action="loginOrRegistServlet?action=adminLogin" method="post">
        <label>管理员账号:</label>
        <input autocomplete="off" class="itxt" id="adminname"
               name="adminname" placeholder="请输入管理员账号" tabindex="1" type="text" maxlength="12"/>
        <br><br>
        <label>管理员密码:</label>
        <input autocomplete="off" class="itxt" id="password"
               name="password" placeholder="请输入管理员密码" tabindex="1" type="password" maxlength="12"/>
        <br><br>
        <input type="submit" value="登录">
        &nbsp;&nbsp;
        <a href="index.jsp">返回</a>
    </form>
</div>
</body>
</html>
