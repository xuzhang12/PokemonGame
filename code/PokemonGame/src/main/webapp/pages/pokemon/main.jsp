<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.pokemongame.domain.Pokemon" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Random" %>
<%@ page import="com.example.pokemongame.domain.User" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: wuming
  Date: 2023/11/3
  Time: 22:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>捕获或战斗界面</title>
    <%--静态包含base标签，css样式，jQuery文件--%>
    <%@include file="/pages/common/head.jsp" %>
    <script>
        $(function () {
            $("#battle").click(function () {
                if ($(this).text() === "战斗") {
                    $(this).text("取消")
                    $("#skilltable").show()
                } else if ($(this).text() === "取消") {
                    $(this).text("战斗")
                    $("#skilltable").hide()
                }
            })
            $("#back").click(function () {
                var $finalResult = $("#finalResult").val();
                location.href = "pokemonServlet?action=afterBattle&finalResult=" + $finalResult;
            })
            $("#capture").click(function () {
                $(this).next().show();
                location.href = "pokemonServlet?action=capture";
            })
            $("#escape").click(function () {
                location.href = "pokemonServlet?action=escape";
            })
            $("#changePokemon").click(function () {
                location.href = "pages/user/pokemon_to_battle.jsp";
            })
        })
    </script>
</head>
<body>
<div class="main">
    <%
        Pokemon mypokemon = null;
        Pokemon opponent = null;
        boolean gameFinished = false;
        if (session.getAttribute("mypokemon") == null) {
            List<Pokemon> pokemons = (List<Pokemon>) session.getAttribute("pokemons");
            List<Pokemon> participant = new ArrayList<>();
            if (pokemons != null) {
                mypokemon = pokemons.get(0);
                participant.add(mypokemon);
                session.setAttribute("mypokemon", mypokemon);
                session.setAttribute("participant", participant);
                session.setAttribute("myInitInfo","你派出了"+mypokemon.getName());
            }
        } else mypokemon = (Pokemon) session.getAttribute("mypokemon");
        if (session.getAttribute("opponent") == null) {
            List<Pokemon> opponents = (List<Pokemon>) session.getAttribute("opponents");
            if (opponents.size()!=0) {
                opponent = opponents.get(0);
                session.setAttribute("opponent", opponent);
                if (session.getAttribute("object").equals("otheruser")) {
                    String oppoName = (String) session.getAttribute("oppoName");
                    session.setAttribute("oppoInitInfo", oppoName + "派出了" + opponent.getName());
                } else if (session.getAttribute("object").equals("pokemon")) {
                    session.setAttribute("oppoInitInfo","野生的"+opponent.getName()+"跳了出来");
                }
            }
        } else opponent = (Pokemon) session.getAttribute("opponent");
        if (session.getAttribute("gameFinished") != null)
            gameFinished = (boolean) session.getAttribute("gameFinished");
    %>
    <h3 class="msg">${requestScope.msg}</h3>
    <h3 class="oppoInitInfo">${sessionScope.oppoInitInfo}</h3>
    <%session.setAttribute("oppoInitInfo",null);%>
    <br>
    <h3 class="myInitInfo">${sessionScope.myInitInfo}</h3>
    <%session.setAttribute("myInitInfo", null);%>
    <h3 class="captureInfo">${sessionScope.captureInfo}</h3>
    <%session.setAttribute("captureInfo", null);%>
    <br>
    <h3 class="battleInfo">${sessionScope.attackInfo}</h3>
    <%session.setAttribute("attackInfo", null);%>
    <br>
    <h3 class="changeInfo">${sessionScope.changeInfo}</h3>
    <%session.setAttribute("changeInfo", null);%>
    <br>
    <h3 class="battleInfo">${sessionScope.attackedInfo}</h3>
    <%session.setAttribute("attackedInfo", null);%>
    <br>
    <h3>${sessionScope.nowResult}</h3>
    <%session.setAttribute("nowResult", null);%>
    <br>
    <h3 class="oppoChangeInfo">${sessionScope.oppoChangeInfo}</h3>
    <%session.setAttribute("oppoChangeInfo",null);%>
    <br>
    <%if (!gameFinished) {%>
    <div class="menu" style="width: 300px;height: 150px;margin: 100px auto;">
        <%if (mypokemon.getBlood() > 0) {%>
        <button name="battle" id="battle">战斗</button>
        <%}%>
        <form action="pokemonServlet?action=battle" method="post" style="display: none;margin-left: 70px"
              id="skilltable" name="skilltable">
            <label>
                <select name="skillname">
                    <option value="<%=mypokemon.getSkillname_1()%>">
                        <%=mypokemon.getSkillname_1()%>:<%=mypokemon.getSkillvalue_1()%>
                    </option>
                    <option value="<%=mypokemon.getSkillname_2()%>">
                        <%=mypokemon.getSkillname_2()%>:<%=mypokemon.getSkillvalue_2()%>
                    </option>
                    <option value="<%=mypokemon.getSkillname_3()%>">
                        <%=mypokemon.getSkillname_3()%>:<%=mypokemon.getSkillvalue_3()%>
                    </option>
                    <option value="<%=mypokemon.getSkillname_4()%>">
                        <%=mypokemon.getSkillname_4()%>:<%=mypokemon.getSkillvalue_4()%>
                    </option>
                </select>
            </label>
            <input type="submit" value="确定" style="font-size: 14px">
        </form>
        <br><br>
        <%if ((opponent.getBlood() > 0) && (mypokemon.getBlood() > 0)) {%>
        <button name="capture" id="capture">捕捉</button>
        <%}%>
        <br><br>
        <button name="escape" id="escape">逃跑</button>
        <br><br>
        <button name="changePokemon" id="changePokemon">更换宠物</button>
    </div>
    <%}%>
    <%if (gameFinished) {%>
    <%if ((int) session.getAttribute("finalResult") == 1) {%>
    <h3>战斗胜利</h3>
    <%} else {%>
    <h3>战斗失败</h3>
    <%}%>
    <br>
    <button name="back" id="back">
        <input type="hidden" name="finalResult" id="finalResult" value="${sessionScope.finalResult==1?"win":"lose"}">返回
    </button>
    <%}%>
</div>
</body>
</html>