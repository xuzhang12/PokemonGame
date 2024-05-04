<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wuming
  Date: 2023/10/28
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>宠物列表页面</title>
    <%--静态包含base标签，css样式，jQuery文件--%>
    <%@include file="/pages/common/head.jsp" %>
    <style type="text/css">
        a {
            font-size: 16px;
            color: skyblue;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $(".delete").click(function () {
                var $name = $(this).parent().parent().find("td:first").text();
                return confirm("你确定要删除【"+$name+"】吗？");
            })
        })
    </script>
</head>
<body style="background-image: none">
<div class="main">
    <a href="pages/manager/login_success.jsp" style="text-align: left;display:block;margin-left: 40px">返回</a>
    <br>
    <table>
        <tr>
            <td>名称</td>
            <td>基础血量</td>
            <td>稀有度</td>
            <td>技能1</td>
            <td>基础伤害</td>
            <td>技能2</td>
            <td>基础伤害</td>
            <td>技能3</td>
            <td>基础伤害</td>
            <td>技能4</td>
            <td>基础伤害</td>
            <td colspan="2">操作</td>
        </tr>
        <c:forEach items="${requestScope.pokemons}" var="pokemon">
            <tr>
                <td>${pokemon.name}</td>
                <td>${pokemon.blood}</td>
                <td>${pokemon.rarity}</td>
                <td>${pokemon.skillname_1}</td>
                <td>${pokemon.skillvalue_1}</td>
                <td>${pokemon.skillname_2}</td>
                <td>${pokemon.skillvalue_2}</td>
                <td>${pokemon.skillname_3}</td>
                <td>${pokemon.skillvalue_3}</td>
                <td>${pokemon.skillname_4}</td>
                <td>${pokemon.skillvalue_4}</td>
                <td><a href="managerServlet?action=getPokemon&name=${pokemon.name}&method=pokemon_update" class="update">修改</a></td>
                <td><a href="managerServlet?action=pokemon_delete&name=${pokemon.name}" class="delete">删除</a></td>
            </tr>
        </c:forEach>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><a href="pages/manager/pokemon_edit.jsp?method=pokemon_add" class="add">添加宠物</a></td>
        </tr>
    </table>
</div>
</body>
</html>
