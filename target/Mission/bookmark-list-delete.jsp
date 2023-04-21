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
        td { border:solid 1px #000; border-color: darkgray;}
    </style>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<h1>즐겨찾기 관리</h1>
<div>
    <a href="bookmark.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="bookmark.jsp">Open API 와이파이 정보 가져오기</a> | <a href="bookmark.jsp">즐겨 찾기 보기</a> | <a href="bookmark.jsp">즐겨 찾기 그룹 관리</a>
</div>
<br>
<div>
    <table>
        <form>
            <tr height="30">
                <td width="25%" align="center" bgcolor="#228b22" style="color:white">북마크 이름</td>
                <td style="color:black"><input type="text" name="bookmark-name"></td>
            </tr>
            <tr height="30">
                <td width="25%" align="center" bgcolor="#228b22" style="color:white">순서</td>
                <td style="color:black"><input type="number" name="order"></td>
            </tr>
            <tr height="30">
                <td colspan="2" align="center">
                    <a href="bookmark.jsp">돌아가기</a> | <button type="submit">삭제</button>
                </td>
            </tr>
        </form>
    </table>
</div>
</body>
</html>
