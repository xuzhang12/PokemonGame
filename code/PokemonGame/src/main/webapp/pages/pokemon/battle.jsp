<%@ page import="java.util.List" %>
<%@ page import="com.example.pokemongame.domain.Pokemon" %>
<%--
  Created by IntelliJ IDEA.
  User: wuming
  Date: 2023/11/4
  Time: 0:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>不使用了的页面</title>
    <%--静态包含base标签，css样式，jQuery文件--%>
    <%@include file="/pages/common/head.jsp" %>
</head>
<body>
<div class="main">

    <br>
    <h2>行动选择：</h2>
    <form action="pokemonServlet?action=battle&index=${requestScope.index}">
        <select name="skillname">
            <option value="${pokemon.skillname_1}">
                ${pokemon.skillname_1}:${pokemon.skillvalue_1}
            </option>
            <option value="${pokemon.skillname_2}">
                ${pokemon.skillname_2}:${pokemon.skillvalue_2}
            </option>
            <option value="${pokemon.skillname_3}">
                ${pokemon.skillname_3}:${pokemon.skillvalue_3}
            </option>
            <option value="${pokemon.skillname_4}">
                ${pokemon.skillname_4}:${pokemon.skillvalue_4}
            </option>
        </select>
        <input type="submit" value="确定" style="font-size: 16px">
    </form>
</div>
</body>
</html>
