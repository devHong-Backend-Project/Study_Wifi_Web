<%@ page import="wifi.WifiService" %>
<%@ page import="wifi.BookmarkGroup" %>
<%@ page import="java.util.List" %>
<%@ page import="wifi.WifiDB" %><%--
  Created by IntelliJ IDEA.
  User: hongseungmin
  Date: 2023/04/18
  Time: 7:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<BookmarkGroup> lst = WifiDB.getBookmarkGroup();
    String htmlTag = WifiService.getBookmarkGroupList();

    Double lat = Double.parseDouble(request.getParameter("LAT2"));
    Double lnt = Double.parseDouble(request.getParameter("LNT2"));
    Integer id = Integer.parseInt(request.getParameter("ID"));
    String WIFI_NM = request.getParameter("WIFI_NM");
%>
<html>
<head>
    <style>
        table { border-collapse: collapse; width: 100%; }
        td { border:solid 1px #000; border-color: darkgray; color:white;}
    </style>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<h1>와이파이 상세 정보</h1>
<div>
    <a href="index.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="dbupdate.jsp">Open API 와이파이 정보 가져오기</a> | <a href="bookmark-list.jsp">즐겨 찾기 보기</a> | <a href="bookmark.jsp">즐겨 찾기 그룹 관리</a>
</div>
<br>
<div>
    <form action="bookmark-list-add.jsp">
        <select id="bookmark" name="GID">
            <option selected>북마크 그룹 이름 선택</option>
            <%=htmlTag%>
        </select>
        <input type="hidden" name="WIFI_NM" value="<%=WIFI_NM%>" />
        <input type="hidden" name="WID" value="<%=id%>" />
        <button type="submit">즐겨찾기 추가하기</button>
    </form>
</div>
<div>
    <table>
        <%
            String detailTable = WifiService.getDetailTable(lat, lnt, id);
        %>
        <%=detailTable%>
    </table>
</div>
</body>
</html>
