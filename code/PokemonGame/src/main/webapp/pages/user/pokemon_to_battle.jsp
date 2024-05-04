<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wuming
  Date: 2023/11/8
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>战斗时用于更换宠物的页面</title>
    <%--静态包含base标签，css样式，jQuery文件--%>
    <%@include file="/pages/common/head.jsp"%>
    <script>
        $(function () {
            $("#changMyPokemon").click(function () {
                var $blood = $("#blood").text();
                if($blood==="0")
                {
                    $(".errorMsg").text("该宠物血量为0，不能出战，请重新选择！")
                    return false;
                }else $(".errorMsg").text("");
            })
        })
    </script>
    <style type="text/css">
        a {
            font-size: 16px;
            color: skyblue;
        }
    </style>
</head>
<body>
<span class="errorMsg"></span>
<div class="main">
    <a href="pages/pokemon/main.jsp" style="float:left;display:block;margin-left: 40px">返回</a>
    <table>
        <tr>
            <td>名字</td>
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
            <td>经验</td>
            <td>经验槽</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${sessionScope.pokemons}" var="pokemon">
            <c:if test="${pokemon.name!=sessionScope.mypokemon.name}">
            <tr>
                <td>${pokemon.name}</td>
                <td id="blood">${pokemon.blood}</td>
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
                <td>${pokemon.experience}</td>
                <td>${pokemon.expSlot}</td>
                <td><a href="pokemonServlet?action=changeMyPokemon&pokemonName=${pokemon.name}"
                       id="changMyPokemon">出战</a></td>
            </tr>
            </c:if>
        </c:forEach>
    </table>
</div>
</body>
</html>
