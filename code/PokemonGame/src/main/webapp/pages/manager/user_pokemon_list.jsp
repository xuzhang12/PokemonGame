<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wuming
  Date: 2023/10/30
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户的宠物信息页面</title>
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
    <a href="managerServlet?action=${param.source}" style="text-align: left;display:block;margin-left: 40px">返回</a>
    <br>
    <table>
        <tr>
            <td>宠物</td>
            <td>血量</td>
            <td>等级</td>
            <td>稀有度</td>
            <td>技能一</td>
            <td>伤害</td>
            <td>技能二</td>
            <td>伤害</td>
            <td>技能三</td>
            <td>伤害</td>
            <td>技能四</td>
            <td>伤害</td>
            <td>出战位序</td>
        </tr>
        <c:forEach items="${requestScope.pokemonlist}" var="pokemon">
            <tr>
                <td>${pokemon.name}</td>
                <td>${pokemon.blood}</td>
                <td>${pokemon.grade}</td>
                <td>${pokemon.rarity}</td>
                <td>${pokemon.skillname_1}</td>
                <td>${pokemon.skillvalue_1}</td>
                <td>${pokemon.skillname_2}</td>
                <td>${pokemon.skillvalue_2}</td>
                <td>${pokemon.skillname_3}</td>
                <td>${pokemon.skillvalue_3}</td>
                <td>${pokemon.skillname_4}</td>
                <td>${pokemon.skillvalue_4}</td>
                <td>${pokemon.queue}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
