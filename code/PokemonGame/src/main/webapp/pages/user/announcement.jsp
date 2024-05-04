<%--
  Created by IntelliJ IDEA.
  User: wuming
  Date: 2023/11/16
  Time: 23:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>公告页面</title>
    <%--静态包含base标签，css样式，jQuery文件--%>
    <%@include file="/pages/common/head.jsp" %>
    <script>
        $(function () {
            let ws = new WebSocket("ws://"+window.location.host+"/MC-Project/pages/user/announcement.jsp");
            ws.onopen = function () {
                console.log('console.log:WebSocket链接打开');
            }
            ws.onmessage=function (event) {
                var newa = document.createElement('a');
                var backtext = document.createTextNode('返回');
                newa.href='pages/user/main.jsp';
                newa.appendChild(backtext);
                document.body.appendChild(newa);
                var htmlbrElement = document.createElement('br');
                document.body.appendChild(htmlbrElement);
                var dayAndMessage = JSON.parse(event.data);
                for (let i = 0; i <dayAndMessage.length; i++) {
                    var newp = document.createElement('p');
                    newp.className='announcement';
                    newp.style.border='1px solid black';
                    newp.style.width='580px';
                    newp.style.height='250px';
                    newp.style.margin='0px auto';
                    var textNode1=document.createTextNode(dayAndMessage[i].day);
                    var textNode2 = document.createTextNode(dayAndMessage[i].message);
                    var newbr1 = document.createElement('br');
                    var newbr2 = document.createElement('br');
                    newp.appendChild(textNode1);
                    newp.appendChild(newbr1);
                    newp.appendChild(textNode2);
                    document.body.appendChild(newp);
                    document.body.appendChild(newbr2);
                }
            }
            ws.onclose=function () {
                console.log('console.log:WebSocket链接关闭');
                // document.getElementById('send').onclick=null;
            }
        })
    </script>
</head>
<body style="background-image: none">
</body>
</html>
