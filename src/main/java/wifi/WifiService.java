package wifi;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class WifiService {

    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat/2)* Math.sin(dLat/2)+ Math.cos(Math.toRadians(lat1))* Math.cos(Math.toRadians(lat2))* Math.sin(dLon/2)* Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d =6371* c;    // Distance in m
        return d;
    }

    public static String getDetailTable(double lat, double lnt, Integer id) throws SQLException {
        String htmlTag = "";
        Wifi w = WifiDB.getWifiDetail(lat,lnt,id);
        List<String> list = w.getAllMember();
        List<String> lst = new ArrayList<>(Arrays.asList("거리(Km)","관리번호","자치구","와이파이명","도로명주소","상세주소","설치위치(층)","설치유형","설치기관","서비스구분","망종류","설치년도","실내외구분","WIFI접속환경","X좌표","Y좌표","작업일자"));

        for(int i = 0; i<list.size(); i++){
            htmlTag += "<tr height=\"30\">";
            htmlTag += String.format("<td width=\"25%%\" align=\"center\" bgcolor=\"#228b22\">%s</td>",lst.get(i));
            htmlTag += String.format("<td style=\"color:black\">%s</td>",list.get(i));
            htmlTag += "</tr>";
        }

        return htmlTag;

        //        <tr height="30">
//            <td width="25%" align="center" bgcolor="#228b22">거리(Km)</td>
//            <td style="color:black">1km</td>
//        </tr>
//        Double LAT = w.getLAT();
//        Double LNT = w.getLNT();
//        Double dis = WifiService.distance(lat,lnt,LAT,LNT);
//
//        Integer WIFI_ID = w.getWIFI_ID();
//        String MGR_NO = w.getMGR_NO();
//        String WRDOFC = w.getWRDOFC();
//        String WIFI_NM = w.getWIFI_NM();
//        String ADRES1 = w.getADRES1();
//        String ADRES2 = w.getADRES2();
//        String INSTL_FLOOR = w.getINSTL_FLOOR();
//        String INSTL_TY = w.getINSTL_TY();
//        String INSTL_MBY = w.getINSTL_MBY();
//        String SVC_SE = w.getSVC_SE();
//        String WIFI_TY = w.getWIFI_TY();
//        String INSTL_YEAR = w.getINSTL_YEAR();
//        String INOUT_DOOR = w.getINOUT_DOOR();
//        String WIFI_ENV = w.getWIFI_ENV();
//        String WORK_DT = w.getWORK_DT();

    }

    public static String getWifiTable(int num, double lat, double lnt) throws SQLException, ParseException {
        List<Wifi> wifis = WifiDB.selectNearWifi(20,lat,lnt);
        String htmlTag = "";
        Collections.sort(wifis,(a,b)-> a.getDistance().compareTo(b.getDistance()));

        for(Wifi w:wifis){
            Double LAT = w.getLAT();
            Double LNT = w.getLNT();
            Double dis = WifiService.distance(lat,lnt,LAT,LNT);
            System.out.println(dis);
            System.out.println(w.getDistance());
            Integer WIFI_ID = w.getWIFI_ID();
            htmlTag += String.format("<tr id=\"%d\">",WIFI_ID);
            htmlTag += "<td>"+Math.round(dis*10000)/10000.0+"</td>";

            String MGR_NO = w.getMGR_NO();
            htmlTag += "<td>"+MGR_NO+"</td>";

            String WRDOFC = w.getWRDOFC();
            htmlTag += "<td>"+WRDOFC+"</td>";

            String WIFI_NM = w.getWIFI_NM();
            htmlTag += String.format("<form id=\"detail%d\" action=\"detail.jsp\" method=\"get\">",WIFI_ID);
            htmlTag += String.format("<input type=\"hidden\" name=\"LAT2\" value=\"%.7f\"/>",lat);
            htmlTag += String.format("<input type=\"hidden\" name=\"LNT2\" value=\"%.7f\"/>",lnt);
            htmlTag += String.format("<input type=\"hidden\" name=\"ID\" value=\"%d\"/>",WIFI_ID);
            htmlTag += String.format("<td><a href=\"#\" onclick=\"document.getElementById('detail%d').submit()\">"+WIFI_NM+"</a></td>",WIFI_ID);
            htmlTag += "</form>";

            String ADRES1 = w.getADRES1();
            htmlTag += "<td>"+ADRES1+"</td>";

            String ADRES2 = w.getADRES2();
            htmlTag += "<td>"+ADRES2+"</td>";

            String INSTL_FLOOR = w.getINSTL_FLOOR();
            htmlTag += "<td>"+INSTL_FLOOR+"</td>";

            String INSTL_TY = w.getINSTL_TY();
            htmlTag += "<td>"+INSTL_TY+"</td>";

            String INSTL_MBY = w.getINSTL_MBY();
            htmlTag += "<td>"+INSTL_MBY+"</td>";

            String SVC_SE = w.getSVC_SE();
            htmlTag += "<td>"+SVC_SE+"</td>";

            String WIFI_TY = w.getWIFI_TY();
            htmlTag += "<td>"+WIFI_TY+"</td>";

            String INSTL_YEAR = w.getINSTL_YEAR();
            htmlTag += "<td>"+INSTL_YEAR+"</td>";

            String INOUT_DOOR = w.getINOUT_DOOR();
            htmlTag += "<td>"+INOUT_DOOR+"</td>";

            String WIFI_ENV = w.getWIFI_ENV();
            htmlTag += "<td>"+WIFI_ENV+"</td>";


            htmlTag += "<td>"+LAT+"</td>";


            htmlTag += "<td>"+LNT+"</td>";

            String WORK_DT = w.getWORK_DT();
            htmlTag += "<td>"+WORK_DT+"</td>";

            htmlTag += "</tr>";
        }

        return htmlTag;
    }

    public static void main(String[] args) throws SQLException, ParseException {
        String tag = getDetailTable(37.5316882,126.9041109,23);
        System.out.println(tag);
        //System.out.println(distance(37.5317504,126.9040165,	37.53316,	126.903564));
    }
}
