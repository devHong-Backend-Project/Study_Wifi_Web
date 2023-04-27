<%--
  Created by IntelliJ IDEA.
  User: hongseungmin
  Date: 2023/04/18
  Time: 7:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("UTF-8");
    String BID = request.getParameter("BID");
    String bm = request.getParameter("bm");
    String WIFI_NM = request.getParameter("WIFI_NM");
    String DT = request.getParameter("DT");

%>
<html>
<head>
    <style>
        table { border-collapse: collapse; width: 100%; }
        td { border:solid 1px #000; border-color: darkgray;}
    </style>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<h1>즐겨찾기 관리</h1>
<div>
    <a href="index.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="dbupdate.jsp">Open API 와이파이 정보 가져오기</a> | <a href="bookmark-list.jsp">즐겨 찾기 보기</a> | <a href="bookmark.jsp">즐겨 찾기 그룹 관리</a>
</div>
<br>
<div>
    <table>
        <form action="bookmark-list-delete-submit.jsp" method="post">
            <input type="hidden" name="BID" value="<%=BID%>" />
            <tr height="30">
                <td width="25%" align="center" bgcolor="#228b22" style="color:white">북마크 이름</td>
                <td style="color:black"><%=bm%></td>
            </tr>
            <tr height="30">
                <td width="25%" align="center" bgcolor="#228b22" style="color:white">와이파이명</td>
                <td style="color:black"><%=WIFI_NM%></td>
            </tr>
            <tr height="30">
                <td width="25%" align="center" bgcolor="#228b22" style="color:white">등록일자</td>
                <td style="color:black"><%=DT%></td>
            </tr>
            <tr height="30">
                <td colspan="2" align="center">
                    <a href="bookmark-list.jsp">돌아가기</a> | <button type="submit">삭제</button>
                </td>
            </tr>
        </form>
    </table>
</div>
</body>
</html>
