<%--
  Created by IntelliJ IDEA.
  User: wuming
  Date: 2023/10/28
  Time: 12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>宠物升级规则页面</title>
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
    <a href="pages/manager/login_success.jsp" style="text-align: left;display:block;margin-left: 40px">返回</a>
    <br>
    <table>
        <tr>
            <td>宠物稀有度/计算公式</td>
            <td>基础经验值</td>
            <td>指数</td>
        </tr>
        <tr>
            <td>普通</td>
            <td>${requestScope.baseExp[0]}</td>
            <td>${requestScope.power}</td>
        </tr>
        <tr>
            <td>稀有</td>
            <td>${requestScope.baseExp[1]}</td>
            <td>${requestScope.power}</td>
        </tr>
        <tr>
            <td>史诗</td>
            <td>${requestScope.baseExp[2]}</td>
            <td>${requestScope.power}</td>
        </tr>
        <tr>
            <td>经验槽计算公式</td>
            <td colspan="2">经验槽 = 基础经验值 * 宠物当前等级 ^ ${requestScope.power}(指数)</td>
        </tr>
        <tr>
            <td>经验获取计算公式</td>
            <td colspan="2">获取的经验 = 对手经验槽 * ${requestScope.multiplier}(经验增长倍数) / 己方参战宠物数</td>
        </tr>
    </table>
    <br>
    <br>
    <hr>
    <table>
        <tr>
            <td>宠物稀有度/计算公式</td>
            <td>基础属性值</td>
        </tr>
        <tr>
            <td>普通</td>
            <td>${requestScope.baseAttribute[0]}</td>
        </tr>
        <tr>
            <td>稀有</td>
            <td>${requestScope.baseAttribute[1]}</td>
        </tr>
        <tr>
            <td>史诗</td>
            <td>${requestScope.baseAttribute[2]}</td>
        </tr>
        <tr>
            <td>血量计算公式</td>
            <td>血量 = 基础血量（由管理员创建宠物时设定，一般为10~20） + 3 *（等级-1） + 基础属性值 * （等级-1）</td>
        </tr>
        <tr>
            <td>技能伤害计算公式</td>
            <td>技能伤害 = 基础技能伤害（有管理员创建宠物时设定，一般为5~10） + 等级-1 + 基础属性值 / 2 *（等级-1）</td>
        </tr>
    </table>
</div>
</body>
</html>
