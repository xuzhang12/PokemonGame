<%--
  Created by IntelliJ IDEA.
  User: wuming
  Date: 2023/10/24
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员登录成功页面</title>
    <%--静态包含base标签，css样式，jQuery文件--%>
    <%@include file="/pages/common/head.jsp" %>
</head>
<body>
<div class="main">
    <a href="managerServlet?action=pokemon_list">宠物管理</a>
    <br>
    <br>
    <a href="managerServlet?action=pokemon_upgrade_rule">查看宠物升级规则</a>
    <br>
    <br>
    <a href="managerServlet?action=user_list">用户信息</a>
    <br>
    <br>
    <a href="managerServlet?action=announcement_list">编辑公告</a>
    <br>
    <br>
    <a href="managerServlet?action=logout">退出</a>
</div>
</body>
</html>
