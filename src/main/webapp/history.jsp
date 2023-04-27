<%@ page import="wifi.WifiService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String htmlTag = WifiService.getHistoryTable();
    if (htmlTag.equals("")){
        htmlTag = "<tr><td colspan=\"17\" align=\"center\" height=\"60\">조회 기록이 없습니다.</td></tr>";
    }
%>
<html>
<head>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th { border:solid 1px #000; border-color: darkgray; color: white}
        td { border:solid 1px #000; border-color: darkgray;}
    </style>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<h1>위치 히스토리 목록</h1>
<div>
    <a href="index.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="dbupdate.jsp">Open API 와이파이 정보 가져오기</a> | <a href="bookmark-list.jsp">즐겨 찾기 보기</a> | <a href="bookmark.jsp">즐겨 찾기 그룹 관리</a>
</div>
<br>
<div>
    <table>
        <tr height="30" bgcolor="#228b22">
            <th>ID</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>조회일자</th>
            <th>비고</th>
        </tr>
        <%=htmlTag%>
    </table>
</div>
<script type="text/javascript" src="js/my_location.js"></script>
</body>
</html>
