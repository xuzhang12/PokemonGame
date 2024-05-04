<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wuming
  Date: 2023/10/30
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>在线用户列表页面</title>
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
    <a href="managerServlet?action=user_list" style="text-align: left;display:block;margin-left: 100px">返回</a>
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
                <td><a href="managerServlet?action=user_pokemon_list&username=${user.name}&source=user_online">查看宠物详情</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
