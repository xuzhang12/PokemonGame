<%@ page import="java.time.LocalDate" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wuming
  Date: 2023/11/18
  Time: 13:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>签到记录页面</title>
    <%--静态包含base标签，css样式，jQuery文件--%>
    <%@include file="/pages/common/head.jsp" %>
    <script>
        $(function () {
            $("#signIn").click(function () {
                location.href="userServlet?action=signIn"
            })
            $(".back").click(function () {
                location.href="pages/user/main.jsp"
            })
        })
    </script>
</head>
<body style="background-image: none">
<span class="msg">${requestScope.msg}</span>
<div class="main">
    <table>
        <tr>
            <th colspan="7"><%=LocalDate.now().getMonthValue()%>月的签到情况</th>
        </tr>
        <tr>
            <c:forEach begin="0" end="6" items="${sessionScope.signInRecord}" var="record">
                <td>${record.key} : ${record.value==true?"已签":"漏签"}</td>
            </c:forEach>
        </tr>
        <tr>
            <c:forEach begin="7" end="13" items="${sessionScope.signInRecord}" var="record">
                <td>${record.key} : ${record.value==true?"已签":"漏签"}</td>
            </c:forEach>
        </tr>
        <tr>
            <c:forEach begin="14" end="20" items="${sessionScope.signInRecord}" var="record">
                <td>${record.key} : ${record.value==true?"已签":"漏签"}</td>
            </c:forEach>
        </tr>
        <tr>
            <c:forEach begin="21" end="27" items="${sessionScope.signInRecord}" var="record">
                <td>${record.key} : ${record.value==true?"已签":"漏签"}</td>
            </c:forEach>
        </tr>
        <tr>
            <c:forEach begin="28" end="30" items="${sessionScope.signInRecord}" var="record">
                <td>${record.key} : ${record.value==true?"已签":"漏签"}</td>
            </c:forEach>
        </tr>
    </table>
    <br>
    <button class="back">返回</button>&nbsp;&nbsp;<button id="signIn" name="signIn">签到</button>
</div>
</body>
</html>
