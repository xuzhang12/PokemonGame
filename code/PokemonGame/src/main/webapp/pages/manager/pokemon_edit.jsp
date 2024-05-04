<%--
  Created by IntelliJ IDEA.
  User: wuming
  Date: 2023/10/28
  Time: 12:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>宠物信息编辑页面</title>
    <%--静态包含base标签，css样式，jQuery文件--%>
    <%@include file="/pages/common/head.jsp" %>
    <script>
        $(function () {
            $("#sub_btn").click(function () {
                var $name = $("#name").val();
                var $blood = $("#blood").val();
                var $rarity = $("#rarity").val();
                var $skillname_1 = $("#skillname_1").val();
                var $skillname_2 = $("#skillname_2").val();
                var $skillname_3 = $("#skillname_3").val();
                var $skillname_4 = $("#skillname_4").val();
                if($name===null||$name===""||$blood===null||$blood==0||$rarity===null||$rarity===""
                    ||$skillname_1===null||$skillname_1===""||$skillname_2===null||$skillname_2===""
                    ||$skillname_3===null||$skillname_3===""||$skillname_4===null||$skillname_4==="")
                {
                    $(".errorMsg").text("宠物参数有误，请重新输入");
                    return false;
                }
                else {
                    $(".errorMsg").text("")
                }
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
<span class="errorMsg">${requestScope.msg}</span>
<div class="main">
    <a href="managerServlet?action=pokemon_list" style="text-align: left;display:block;margin-left: 40px">返回</a>
    <br>
    <form action="managerServlet" method="post">
        <input type="hidden" name="action" value="${param.method}">
        <input type="hidden" name="pastname" value="${requestScope.pokemon.name}">
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
            <tr>
                <td><input class="info" type="text" name="name" id="name" value="${requestScope.pokemon.name}"></td>
                <td><input class="info" type="text" name="blood" id="blood" value="${requestScope.pokemon.blood}"></td>
                <td><input class="info" type="text" name="rarity" id="rarity" value="${requestScope.pokemon.rarity}"></td>
                <td><input class="info" type="text" name="skillname_1" id="skillname_1" value="${requestScope.pokemon.skillname_1}"></td>
                <td><input class="info" type="text" name="skillvalue_1" id="skillvalue_1" value="${requestScope.pokemon.skillvalue_1}"></td>
                <td><input class="info" type="text" name="skillname_2" id="skillname_2" value="${requestScope.pokemon.skillname_2}"></td>
                <td><input class="info" type="text" name="skillvalue_2" id="skillvalue_2" value="${requestScope.pokemon.skillvalue_2}"></td>
                <td><input class="info" type="text" name="skillname_3" id="skillname_3" value="${requestScope.pokemon.skillname_3}"></td>
                <td><input class="info" type="text" name="skillvalue_3" id="skillvalue_3" value="${requestScope.pokemon.skillvalue_3}"></td>
                <td><input class="info" type="text" name="skillname_4" id="skillname_4" value="${requestScope.pokemon.skillname_4}"></td>
                <td><input class="info" type="text" name="skillvalue_4" id="skillvalue_4" value="${requestScope.pokemon.skillvalue_4}"></td>
                <td><input type="submit" id="sub_btn" value="提交"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
