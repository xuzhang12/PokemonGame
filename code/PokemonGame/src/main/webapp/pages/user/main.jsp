<%--
  Created by IntelliJ IDEA.
  User: wuming
  Date: 2023/10/25
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
    <%--静态包含base标签，css样式，jQuery文件--%>
    <%@include file="/pages/common/head.jsp" %>
</head>
<body>
<span class="msg">
    ${requestScope.msg}
</span>
<div class="main">
    <div class="menu">
        <label><a href="pages/user/pokemon_in_bag.jsp">宠物背包</a></label>
        <label><a href="userServlet?action=saveDate">存档</a></label>
        <br>
        <br>
        <label><a href="pages/user/user_info_list.jsp">基本信息</a></label>
        <label><a href="userServlet?action=signInRecord">每日签到</a></label>
        <br>
        <br>
        <label><a href="pokemonServlet?action=pokemon_field_list">宠物场</a></label>
        <label><a href="pages/user/announcement.jsp">公告</a></label>
        <br><br>
        <label><a href="userServlet?action=otherUserList">其他玩家</a></label>
        <label><a href="userServlet?action=logout">退出</a></label>
        <br><br>
    </div>
</div>
</body>
</html>
