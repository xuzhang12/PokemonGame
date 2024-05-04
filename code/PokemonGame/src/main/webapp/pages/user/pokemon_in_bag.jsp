<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wuming
  Date: 2023/10/25
  Time: 23:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>宠物背包页面</title>
    <%--静态包含base标签，css样式，jQuery文件--%>
    <%@include file="/pages/common/head.jsp"%>
    <style type="text/css">
        a {
            font-size: 16px;
            color: skyblue;
        }
    </style>
    <script>
        $(function () {
            $(".move_up").click(function () {
                var $queue = $(this).parent().prev().text();
                if($queue==="1")
                {
                    $(".msg").text("该宠物出战位序已为最高");
                }else {
                    $(".msg").text("")
                    location.href="userServlet?action=queueUpPokemon&queue="+$queue;
                }
            })
            $("#save").click(function (){
                location.href="userServlet?action=saveQueue";
            })
        })
    </script>
</head>
<body>
<span class="msg">${requestScope.msg}</span>
<div class="main">
    <a href="pages/user/main.jsp" style="float:left;display:block;margin-left: 40px">返回</a>
    <h3>注意：该顺序为别人对你发起挑战时，你的宠物的出战顺序</h3>
    <br>
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
            <td>出战位序</td>
            <td colspan="2">操作</td>
        </tr>
        <c:forEach items="${sessionScope.pokemons}" var="pokemon">
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
                <td>${pokemon.experience}</td>
                <td>${pokemon.expSlot}</td>
                <td id="queue">${pokemon.queue}</td>
                <td><button class="move_up">上移</button></td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <button id="save">保存修改</button>
</div>
</body>
</html>
