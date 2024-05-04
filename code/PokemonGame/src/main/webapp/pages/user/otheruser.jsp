<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wuming
  Date: 2023/11/15
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>其他用户列表页面</title>
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
<span class="msg">${sessionScope.msg}</span>
<%session.setAttribute("msg",null);%>
<div class="main">
    <a href="pages/user/main.jsp" style="float:left;;display:block;margin-left: 100px">返回</a>
    <br>
    <table>
        <tr>
            <td>用户名</td>
            <td>在线情况</td>
            <td>昵称</td>
            <td>个性签名</td>
            <td colspan="2">操作</td>
        </tr>
        <c:forEach items="${sessionScope.otherUsers}" var="otherUser">
            <tr>
                <td>${otherUser.name}</td>
                <td>${otherUser.online==1?"在线":"离线"}</td>
                <td>${otherUser.nickname}</td>
                <td>${otherUser.signature}</td>
                <td><a href="userServlet?action=otherUserPokemon&otherUsername=${otherUser.name}">宠物详情</a></td>
                <td><a href="pokemonServlet?action=getOpponents&object=otheruser&otherUsername=${otherUser.name}">发起挑战</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
