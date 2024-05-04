<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: wuming
  Date: 2023/11/2
  Time: 23:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户基本信息页面</title>
    <%--静态包含base标签，css样式，jQuery文件--%>
    <%@include file="/pages/common/head.jsp" %>
    <style type="text/css">
        label {
            width: 200px;
        }
    </style>
</head>
<body style="background-image: none">
<div class="main" style="margin-top: -20px">
    <label>昵称：</label>
    <span style="display:inline-block;width: 300px;height: 25px;border: 1px solid #666666;text-align: left">${sessionScope.user.nickname}</span>
    <br>
    <br>
    <label>个性签名：</label>
    <span style="display:inline-block;width: 300px;height:80px;border: 1px solid #666666;text-align: left">${sessionScope.user.signature}</span>
    <br>
    <br>
    <label>生日：</label>
    <span style="display:inline-block;width: 300px;height: 25px;border: 1px solid #666666;text-align: left">${sessionScope.birthday}</span>
    <br><br>
    <label>是否公开(公开即接受pk)：</label>
    <span style="display:inline-block;width: 300px;height: 25px;border: 1px solid #666666;text-align: left">
        ${sessionScope.user.open==1?"公开":"不公开"}
    </span>
    <br><br>
    <a href="pages/user/main.jsp">返回</a>&nbsp;&nbsp;
    <a href="pages/user/user_info_update.jsp">修改</a>
</div>
</body>
</html>
