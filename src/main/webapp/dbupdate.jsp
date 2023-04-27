<%@ page import="wifi.WifiDB" %>
<%@ page import="wifi.HttpRequest" %><%--
  Created by IntelliJ IDEA.
  User: hongseungmin
  Date: 2023/04/20
  Time: 9:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    WifiDB.deleteAllWifiInfo();
    Integer totalCount = HttpRequest.WifiApiReq();
%>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<div align="center">
    <h1><%=totalCount%>개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
</div>
<div align="center">
    <a href="index.jsp">홈 으로 가기</a>
</div>
</body>
</html>
