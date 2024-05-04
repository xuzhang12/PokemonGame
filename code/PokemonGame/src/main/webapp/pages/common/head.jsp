<%--
  Created by IntelliJ IDEA.
  User: wuming
  Date: 2023/10/26
  Time: 9:04
  To change this template use File | Settings | File Templates.
--%>

<%--这里是各个jsp页面共同的需要的base相对路径，css样式，jQuery文件，静态包含到各个jsp页面中--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%--动态获取服务器访问地址--%>
<%
    String basePath=request.getScheme()+"://"
            +request.getServerName()+":"
            +request.getServerPort()+request.getContextPath()+"/";
%>
<!--写 base 标签，永远固定相对路径跳转的结果-->
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css">
<script src="static/script/jquery-1.7.2.js" type="text/javascript"></script>