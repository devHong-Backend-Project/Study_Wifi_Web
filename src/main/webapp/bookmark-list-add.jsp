<%@ page import="wifi.WifiDB" %><%--
  Created by IntelliJ IDEA.
  User: hongseungmin
  Date: 2023/04/28
  Time: 1:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  request.setCharacterEncoding("UTF-8");
  String GID = request.getParameter("GID");
  String WIFI_NM = request.getParameter("WIFI_NM");
  String WID = request.getParameter("WID");

  boolean success = WifiDB.insertBookmarkList(GID,WIFI_NM,WID);
  String htmlTag = "";
%>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<%
  if(success){
    htmlTag = "<script type=\"text/javascript\">alert(\"북마크를 추가하였습니다.\");location.href = 'bookmark-list.jsp';</script>";
  }else{
    htmlTag = "<script type=\"text/javascript\">alert(\"북마크 추가에 실패하였습니다. 다시시도해주십시오.\");window.history.back();</script>";
  }
%>
<%=htmlTag%>
</body>
</html>
