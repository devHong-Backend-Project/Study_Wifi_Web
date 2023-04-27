<%@ page import="wifi.WifiDB" %><%--
  Created by IntelliJ IDEA.
  User: hongseungmin
  Date: 2023/04/28
  Time: 3:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("UTF-8");
    String BID = request.getParameter("BID");

    boolean success = WifiDB.deleteBookmarkList(BID);
    String htmlTag = "";
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    if(success){
        htmlTag = "<script type=\"text/javascript\">alert(\"북마크를 삭제하였습니다.\");location.href = 'bookmark-list.jsp';</script>";
    }else{
        htmlTag = "<script type=\"text/javascript\">alert(\"삭제에 실패하였습니다. 다시 시도해주십시오.\");location.href = 'bookmark-list.jsp';</script>";
    }
%>
<%=htmlTag%>
</body>
</html>
