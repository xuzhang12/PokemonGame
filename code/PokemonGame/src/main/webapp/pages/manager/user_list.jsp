<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wuming
  Date: 2023/10/28
  Time: 12:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>所有用户列表页面</title>
    <%--静态包含base标签，css样式，jQuery文件--%>
    <%@include file="/pages/common/head.jsp" %>
    <style type="text/css">
        a {
            font-size: 16px;
            color: skyblue;
        }
    </style>
</head>
<body style="background-image: none">
<div class="main">
    <a href="pages/manager/login_success.jsp" style="float:left;;display:block;margin-left: 100px">返回</a>
    <a href="managerServlet?action=user_online" style="float:right;display: block;margin-right: 40px;">仅查看在线用户</a>
    &nbsp;&nbsp;
    <a href="managerServlet?action=user_offline" style="float:right;display: block;margin-right: 40px;">仅查看离线用户</a>
    <br>
    <br>
    <table>
        <tr>
            <td>用户</td>
            <td>在线情况</td>
            <td>昵称</td>
            <td>个性签名</td>
            <td>生日</td>
            <td colspan="1">操作</td>
        </tr>
        <c:forEach items="${requestScope.users}" var="user">
            <tr>
                <td>${user.name}</td>
                <td>${user.online==1?"在线":"离线"}</td>
                <td>${user.nickname}</td>
                <td>${user.signature}</td>
                <td>${user.birthday}</td>
                <td><a href="managerServlet?action=user_pokemon_list&username=${user.name}&source=user_list">查看宠物详情</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
