<%@ page import="wifi.WifiService" %>
<%@ page import="wifi.WifiDB" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %><%--
  Created by IntelliJ IDEA.
  User: hongseungmin
  Date: 2023/04/18
  Time: 7:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
    String lat = request.getParameter("LAT");
    String lnt = request.getParameter("LNT");

    if (lat == null){
        lat = "0.0";
        lnt = "0.0";
    }
%>
<html>
<head>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th { border:solid 1px #000; border-color: darkgray; color: white}
        td { border:solid 1px #000; border-color: darkgray;}
    </style>
    <title>와이파이 정보 구하기</title>
</head>
<body>
    <h1>와이파이 정보 구하기</h1>
    <div>
        <a href="index.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="dbupdate.jsp">Open API 와이파이 정보 가져오기</a> | <a href="bookmark-list.jsp">즐겨 찾기 보기</a> | <a href="bookmark.jsp">즐겨 찾기 그룹 관리</a>
    </div>
    <br>
    <div>
        <form action="index.jsp" method="get">
            LAT: <input id="lat" type="text" name="LAT" value=<%=lat%> /> , LNT: <input type="text" id="lnt" name="LNT" value=<%=lnt%> /> <button id="location" type="button">내 위치 가져오기</button> <button type="submit">근처 WIFI 정보 보기</button>
        </form>
    </div>
    <div>
        <table>
            <tr height="30" bgcolor="#228b22">
                <th>거리(Km)</th>
                <th>관리번호</th>
                <th>자치구</th>
                <th>와이파이명</th>
                <th>도로명주소</th>
                <th>상세주소</th>
                <th>설치위치(층)</th>
                <th>설치유형</th>
                <th>설치기관</th>
                <th>서비스구분</th>
                <th>망종류</th>
                <th>설치년도</th>
                <th>실내외구분</th>
                <th>WIFI접속환경</th>
                <th>X좌표</th>
                <th>Y좌표</th>
                <th>작업일자</th>
            </tr>
            <%
                String tdTag = "<tr><td colspan=\"17\" align=\"center\" height=\"60\" id=\"before\">위치 정보를 입력한 후에 조회해 주세요.</td></tr>";
                if (lat!="0.0"){
                    tdTag = "";
                }
            %>
            <%=tdTag%>
            <%
                String htmlTag = "";
                if (lat!="0.0") {
                    Double lat_ = Double.parseDouble(lat);
                    Double lnt_ = Double.parseDouble(lnt);
                    htmlTag = WifiService.getWifiTable(20,lat_,lnt_);
                }
            %>
            <%=htmlTag%>
            <%
                if (lat!="0.0"){
                    String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    WifiDB.insertHistory(Double.parseDouble(lat),Double.parseDouble(lnt),dateTime);
                }
            %>
        </table>
    </div>
    <script type="text/javascript" src="js/my_location.js"></script>
</body>
</html>
