<%--
  Created by IntelliJ IDEA.
  User: hongseungmin
  Date: 2023/04/18
  Time: 7:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        table { border-collapse: collapse; width: 100%; }
        td { border:solid 1px #000; border-color: darkgray; color:white}
    </style>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<h1>와이파이 상세 정보</h1>
<div>
    <a href="index.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="index.jsp">Open API 와이파이 정보 가져오기</a> | <a href="bookmark-list.jsp">즐겨 찾기 보기</a> | <a href="bookmark.jsp">즐겨 찾기 그룹 관리</a> | <a href="detail.jsp">와이파이 상세 정보 창</a>
</div>
<br>
<div>
    <form action="#">
        <select id="bookmark">
            <option selected>북마크 그룹 이름 선택</option>
            <option value="test">test</option>
        </select>
        <button type="submit">즐겨찾기 추가하기</button>
    </form>
</div>
<div>
    <table>
        <tr height="30">
            <td width="25%" align="center" bgcolor="#228b22">거리(Km)</td>
            <td style="color:black">1km</td>
        </tr>
    </table>
</div>
</body>
</html>
