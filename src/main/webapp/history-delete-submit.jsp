<%@ page import="wifi.WifiDB" %><%--
  Created by IntelliJ IDEA.
  User: hongseungmin
  Date: 2023/04/27
  Time: 5:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String id = request.getParameter("ID");
    WifiDB.deleteHistory(id);
%>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<script type="text/javascript">
    alert("위치조회 시점이 삭제되었습니다.")
    location.href = 'history.jsp'
</script>
</body>
</html>
