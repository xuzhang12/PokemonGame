<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wuming
  Date: 2023/11/18
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员编辑公告页面</title>
  <%--静态包含base标签，css样式，jQuery文件--%>
  <%@include file="/pages/common/head.jsp" %>
</head>
<body style="background-image: none">
<div class="main">
  <span class="msg">${sessionScope.msg}</span>
  <%session.setAttribute("msg",null);%>
  <br>
  <form action="managerServlet?action=editAnnouncement" method="post">
    <textarea name="message" id="message" cols="80" rows="15"></textarea>
    <br><br>
    <a href="pages/manager/login_success.jsp">返回</a>&nbsp;&nbsp;
    <input type="submit" value="发布公告">
  </form>
  <br>
  <c:forEach items="${sessionScope.announcements}" var="announcement">
    <h3>${announcement.day}</h3>
    <p style="border: 1px solid black;width: 580px;height: 250px;margin: 0 auto">${announcement.message}</p>
    <br>
  </c:forEach>
</div>
</body>
</html>
