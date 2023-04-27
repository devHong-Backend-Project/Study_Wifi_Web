<%@ page import="wifi.WifiDB" %><%--
  Created by IntelliJ IDEA.
  User: hongseungmin
  Date: 2023/04/27
  Time: 10:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  request.setCharacterEncoding("UTF-8");
  String ID = request.getParameter("ID");
  String bm = request.getParameter("bm");
  String order = request.getParameter("order");

  boolean success = WifiDB.updateBookmarkGroup(ID,bm,order);
  String htmlTag = "";
%>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<%
  if(success){
    htmlTag = "<script type=\"text/javascript\">alert(\"북마크 그룹 정보를 수정하였습니다.\");location.href = 'bookmark.jsp';</script>";
  }else{
    htmlTag = "<script type=\"text/javascript\">alert(\"북마크 이름 또는 순서에 중복이 있습니다.\");location.href = 'bookmark.jsp';</script>";
  }
%>
<%=htmlTag%>
</body>
</html>
