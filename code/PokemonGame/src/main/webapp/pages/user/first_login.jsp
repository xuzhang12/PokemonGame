<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户首次登录界面</title>
    <%--静态包含base标签，css样式，jQuery文件--%>
    <%@include file="/pages/common/head.jsp" %>
    <style type="text/css">
        body {
            background-image: none;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $("#sub_btn").click(function () {
                if ($(":radio:checked").length === 0) {
                    $(".errorMsg").text("你还未选择宠物");
                    return false;
                } else {
                    $(".errorMsg").text("");
                    var $checked = $(":radio:checked").val();
                    if (!confirm("确定选择[" + $checked + "]作为你的第一只宠物吗？")) {
                        return false;
                    }
                }
            })
        })
    </script>
    <style type="text/css">
        img {
            width: 100%;
            height: 100%;
        }

        .pokemon {
            width: 29%;
            float: left;
            padding: 0 23px;
        }
    </style>
</head>
<body>
<span class="errorMsg">${requestScope.msg}</span>
<br>
<span class="confirm"></span>
<h1>首次登录，请选择你的第一只宠物吧~~</h1>
<div class="main">
    <form action="loginOrRegistServlet" method="post">
        <input type="hidden" name="action" value="userFirstLogin">
        <div class="pokemon">
            <img src="static/img/菊叶草.jpg">
            <br/>
            <input type="radio" name="firstPokemon" value="菊叶草">菊叶草
            <div style="color: green">
                头上的叶子会发出微微的甜蜜香味，最喜欢晒太阳了，生来便个性温顺
            </div>
        </div>
        <div class="pokemon">
            <img src="static/img/小锯鳄.jpg">
            <br/>
            <input type="radio" name="firstPokemon" value="小锯鳄">小锯鳄
            <div style="color: blue">
                是一种双足、蓝色、与鳄鱼相似的神奇宝贝，拥有肌肉发达的下颚，什么东西都可以咬碎，
                对于眼前移动的任何东西，都会产生反射，忍不住上前咬住。
            </div>
        </div>
        <div class="pokemon">
            <img src="static/img/火球鼠.jpg">
            <br/>
            <input type="radio" name="firstPokemon" value="火球鼠">火球鼠
            <div style="color: red">
                个性温和，兴奋时会从背上发出火焰，在生气或吃惊时，背上的火焰会燃烧得更加猛烈，
                但在没有斗志或受伤严重时，火焰也会熄灭。
            </div>
        </div>
        <br>
        <div style="float:left;width: 100%;margin-top: 20px">
            <input type="submit" value="确定" id="sub_btn">
        </div>
    </form>
</div>
</body>
</html>