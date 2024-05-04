<%--
  Created by IntelliJ IDEA.
  User: wuming
  Date: 2023/10/25
  Time: 23:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户信息更改页面</title>
    <%--静态包含base标签，css样式，jQuery文件--%>
    <%@include file="/pages/common/head.jsp" %>
    <script>
        $(function () {
            // 获取年份列表
            function getYearList() {
                var yearList = [];
                var currentYear = new Date().getFullYear();
                for (var i = currentYear; i > currentYear - 100; i--) {
                    yearList.push(i);
                }
                return yearList;
            }

            // 获取月份列表
            function getMonthList() {
                var monthList = [];
                for (var i = 1; i <= 12; i++) {
                    monthList.push(i);
                }
                return monthList;
            }

            // 获取某个月的天数
            function getDaysInMonth(year, month) {
                return new Date(year, month, 0).getDate();
            }

            // 更新日列表
            function updateDayList() {
                var year = $("#year").val();
                var month = $("#month").val();
                var dayList = [];
                var daysInMonth = getDaysInMonth(year, month);
                for (var i = 1; i <= daysInMonth; i++) {
                    dayList.push(i);
                }
                $("#day").empty();
                $.each(dayList, function (index, value) {
                    $("#day").append($("<option>").text(value));
                });
            }

            $(function () {
                // 初始化年份列表
                var yearList = getYearList();
                $.each(yearList, function (index, value) {
                    $("#year").append($("<option>").text(value));
                });

                // 初始化月份列表
                var monthList = getMonthList();
                $.each(monthList, function (index, value) {
                    $("#month").append($("<option>").text(value));
                });

                // 更新日列表
                updateDayList();

                // 监听年、月下拉框的变化
                $("#year, #month").change(function () {
                    updateDayList();
                });
            })
        })

    </script>
    <style type="text/css">
        label {
            width: 200px;
        }
    </style>
</head>
<body style="background-image: none">
<div class="main">
    <form action="userServlet?action=user_info_update" method="post">
        <label>昵称：</label>
        <input type="text" id="nickname" name="nickname" maxlength="20" placeholder="长度不得超过20"
               style="width: 300px;" value="${sessionScope.user.nickname}">
        <br><br>
        <label>个性签名：</label>
        <textarea name="signature" id="signature" cols="40" rows="5"
                  maxlength="200">${sessionScope.user.signature}</textarea>
        <br><br>
        <label>生日：</label>
        <select id="year" name="year"></select>&nbsp;年&nbsp;
        <select id="month" name="month"></select>&nbsp;月&nbsp;
        <select id="day" name="day"></select>&nbsp;日&nbsp;
        <br><br>
        <label>是否公开(公开即接受pk)：</label>
        <select id="open" name="open">
            <option value="1">公开</option>
            <option value="0">不公开</option>
        </select>
        <br><br>
        <a href="pages/user/user_info_list.jsp">返回</a>&nbsp;&nbsp;
        <input type="submit" value="提交修改" style="font-size: 16px">
    </form>
</div>
</body>
</html>
