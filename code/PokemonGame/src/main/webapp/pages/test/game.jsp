<%--<%@ page import="com.zhangxingyu.domain.Pokemon" %>&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: wuming--%>
<%--  Date: 2023/11/4--%>
<%--  Time: 15:09--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Title</title>--%>
<%--    &lt;%&ndash;静态包含base标签，css样式，jQuery文件&ndash;%&gt;--%>
<%--    <%@include file="/pages/common/head.jsp" %>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h1>回合制游戏</h1>--%>
<%--<%--%>
<%--    // 创建角色和敌人--%>
<%--    Pokemon mypokemon = null, opponent = null;--%>
<%--    if (request.getSession().getAttribute("mypokemon") == null)--%>
<%--        mypokemon = new Pokemon("小火龙", 26, 2, "稀有", "火花", 6, "火花", 6, "火花", 6, "火花", 6, 0, 24, null, "Tom");--%>
<%--    else mypokemon = (Pokemon) request.getSession().getAttribute("mypokemon");--%>
<%--    System.out.println(mypokemon);--%>
<%--    if (request.getSession().getAttribute("opponent") == null)--%>
<%--        opponent = new Pokemon("火球鼠", 20, 1, "稀有", "火花", 3, "火花", 3, "火花", 3, "火花", 3, 0, 2, null, "admin");--%>
<%--    else opponent = (Pokemon) request.getSession().getAttribute("opponent");--%>
<%--    System.out.println(opponent);--%>
<%--    // 判断游戏是否结束--%>
<%--    boolean gameFinished = false;--%>
<%--    if (request.getSession().getAttribute("gameFinished") != null)--%>
<%--        gameFinished = (boolean) request.getSession().getAttribute("gameFinished");--%>
<%--    System.out.println(gameFinished);--%>
<%--    System.out.println(request.getParameter("action"));--%>
<%--    if (request.getParameter("action") != null) {--%>
<%--        // 玩家回合--%>
<%--        String action = request.getParameter("action");--%>
<%--        if (action.equals("attack")) {--%>
<%--            mypokemon.attackPokemon(opponent, mypokemon.getSkillname_1());--%>
<%--        }--%>
<%--//        else if (action.equals("specialAttack")) {--%>
<%--//            player.specialAttack(enemy);--%>
<%--//        }--%>

<%--        // 敌人回合--%>
<%--        if (opponent.getBlood() > 0) {--%>
<%--            opponent.attackPokemon(mypokemon, opponent.getSkillname_1());--%>
<%--        }--%>

<%--        // 判断游戏是否结束--%>
<%--        if (mypokemon.getBlood() == 0 || opponent.getBlood() == 0) {--%>
<%--            gameFinished = true;--%>
<%--        }--%>
<%--    }--%>
<%--%>--%>

<%--<div>--%>
<%--    <h2>角色信息：</h2>--%>
<%--    <p><strong>玩家：</strong> <%= mypokemon.getName() %> 生命值： <%= mypokemon.getBlood() %>--%>
<%--    </p>--%>
<%--    <p><strong>敌人：</strong> <%= opponent.getName() %> 生命值： <%= opponent.getBlood() %>--%>
<%--    </p>--%>
<%--</div>--%>

<%--<%--%>
<%--    if (!gameFinished) {--%>
<%--        request.getSession().setAttribute("mypokemon",mypokemon);--%>
<%--        request.getSession().setAttribute("opponent",opponent);--%>
<%--%>--%>
<%--<div>--%>
<%--    <h2>行动选择：</h2>--%>
<%--    <form action="pages/test/game.jsp" method="post">--%>
<%--        <input type="hidden" name="action" value="attack">--%>
<%--        <input type="submit" value="攻击">--%>
<%--    </form>--%>
<%--    &lt;%&ndash;    <form action="游戏页面的URL" method="post">&ndash;%&gt;--%>
<%--    &lt;%&ndash;        <input type="hidden" name="action" value="specialAttack">&ndash;%&gt;--%>
<%--    &lt;%&ndash;        <input type="submit" value="技能攻击">&ndash;%&gt;--%>
<%--    &lt;%&ndash;    </form>&ndash;%&gt;--%>
<%--</div>--%>
<%--<% } else { %>--%>
<%--<div>--%>
<%--    <h2>游戏结束</h2>--%>
<%--    <% if (mypokemon.getBlood() > 0) { %>--%>
<%--    <p>玩家获胜！</p>--%>
<%--    <% } else { %>--%>
<%--    <p>敌人获胜！</p>--%>
<%--    <% } %>--%>
<%--</div>--%>
<%--<% } %>--%>
<%--</body>--%>
<%--</html>--%>
