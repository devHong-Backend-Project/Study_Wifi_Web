package wifi;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WifiDB {

    private final static String url = "jdbc:mysql://localhost:3306/wifi";
    private final static String user = "root";
    private final static String password = "Dnjfchs23";

    public static boolean insertData(OkHttpClient client, Request req) throws SQLException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = DriverManager.getConnection(url, user, password);
        //Statement stmt = conn.createStatement();
        String sql = "INSERT INTO TB_WIFI_INFO(MGR_NO, WRDOFC, WIFI_NM, ADRES1, ADRES2, INSTL_FLOOR, INSTL_TY, INSTL_MBY, SVC_SE, WIFI_TY, INSTL_YEAR, INOUT_DOOR, WIFI_ENV, LAT, LNT, WORK_DT) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        try (Response response = client.newCall(req).execute()) {
            JsonParser parser = new JsonParser();
            Object obj = parser.parse(response.body().string());
            JsonObject jsonObject = (JsonObject) obj;
            JsonArray rs = jsonObject.getAsJsonObject("TbPublicWifiInfo").getAsJsonArray("row");
            //System.out.println(rs.size());
            for (int i = 0; i < rs.size(); i++) {
                JsonObject js = rs.get(i).getAsJsonObject();
                String MGR_NO = js.get("X_SWIFI_MGR_NO").getAsString();
                String WRDOFC = js.get("X_SWIFI_WRDOFC").getAsString();
                String WIFI_NM = js.get("X_SWIFI_MAIN_NM").getAsString();
                String ADRES1 = js.get("X_SWIFI_ADRES1").getAsString();
                String ADRES2 = js.get("X_SWIFI_ADRES2").getAsString();
                String INSTL_FLOOR = js.get("X_SWIFI_INSTL_FLOOR").getAsString();
                String INSTL_TY = js.get("X_SWIFI_INSTL_TY").getAsString();
                String INSTL_MBY = js.get("X_SWIFI_INSTL_MBY").getAsString();
                String SVC_SE = js.get("X_SWIFI_SVC_SE").getAsString();
                String WIFI_TY = js.get("X_SWIFI_CMCWR").getAsString();
                String INSTL_YEAR = js.get("X_SWIFI_CNSTC_YEAR").getAsString();
                String INOUT = js.get("X_SWIFI_INOUT_DOOR").getAsString();
                String WIFI_ENV = js.get("X_SWIFI_REMARS3").getAsString();
                Double LAT = js.get("LAT").getAsDouble();
                Double LNT = js.get("LNT").getAsDouble();
                if (LAT>100){
                    double x = LAT;
                    LAT = LNT;
                    LNT = x;
                }
                String WORK_DT = js.get("WORK_DTTM").getAsString();

                pstmt.setString(1, MGR_NO);
                pstmt.setString(2, WRDOFC);
                pstmt.setString(3, WIFI_NM);
                pstmt.setString(4, ADRES1);
                pstmt.setString(5, ADRES2);
                pstmt.setString(6, INSTL_FLOOR);
                pstmt.setString(7, INSTL_TY);
                pstmt.setString(8, INSTL_MBY);
                pstmt.setString(9, SVC_SE);
                pstmt.setString(10, WIFI_TY);
                pstmt.setString(11, INSTL_YEAR);
                pstmt.setString(12, INOUT);
                pstmt.setString(13, WIFI_ENV);
                pstmt.setDouble(14, LAT);
                pstmt.setDouble(15, LNT);
                pstmt.setString(16, WORK_DT);

                pstmt.addBatch();
                pstmt.clearParameters();
            }
            pstmt.executeBatch();
            pstmt.clearBatch();

            //System.out.println(rs.toString());

            if (!pstmt.isClosed()) pstmt.close();
            if (!conn.isClosed()) conn.close();

            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static List<Wifi> selectNearWifi(int number, double lat, double lnt) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = DriverManager.getConnection(url, user, password);
        //Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM TB_WIFI_INFO ORDER BY ABS(LAT - ?) + ABS(LNT - ?) ASC LIMIT ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setDouble(1, lat);
        pstmt.setDouble(2, lnt);
        pstmt.setInt(3, number);

        ResultSet rs = pstmt.executeQuery();

        List<Wifi> wifis = new ArrayList<Wifi>();
        while (rs.next()){
            Integer WIFI_ID = rs.getInt("WIFI_ID");
            String MGR_NO = rs.getString("MGR_NO");
            String WRDOFC = rs.getString("WRDOFC");
            String WIFI_NM = rs.getString("WIFI_NM");
            String ADRES1 = rs.getString("ADRES1");
            String ADRES2 = rs.getString("ADRES2");
            String INSTL_FLOOR = rs.getString("INSTL_FLOOR");
            String INSTL_TY = rs.getString("INSTL_TY");
            String INSTL_MBY = rs.getString("INSTL_MBY");
            String SVC_SE = rs.getString("SVC_SE");
            String WIFI_TY = rs.getString("WIFI_TY");
            String INSTL_YEAR = rs.getString("INSTL_YEAR");
            String INOUT = rs.getString("INOUT_DOOR");
            String WIFI_ENV = rs.getString("WIFI_ENV");
            Double LAT = rs.getDouble("LAT");
            Double LNT = rs.getDouble("LNT");
            String WORK_DT = rs.getString("WORK_DT");

            Double dis = WifiService.distance(lat,lnt,LAT,LNT);

            Wifi wifi = new Wifi();
            wifi.setWIFI_ID(WIFI_ID);
            wifi.setDistance(dis);
            wifi.setMGR_NO(MGR_NO);
            wifi.setWRDOFC(WRDOFC);
            wifi.setWIFI_NM(WIFI_NM);
            wifi.setADRES1(ADRES1);
            wifi.setADRES2(ADRES2);
            wifi.setINSTL_FLOOR(INSTL_FLOOR);
            wifi.setINSTL_TY(INSTL_TY);
            wifi.setINSTL_MBY(INSTL_MBY);
            wifi.setSVC_SE(SVC_SE);
            wifi.setWIFI_TY(WIFI_TY);
            wifi.setINSTL_YEAR(INSTL_YEAR);
            wifi.setINOUT_DOOR(INOUT);
            wifi.setWIFI_ENV(WIFI_ENV);
            wifi.setLAT(LAT);
            wifi.setLNT(LNT);
            wifi.setWORK_DT(WORK_DT);

            wifis.add(wifi);
        }

        if (!rs.isClosed())  rs.close();
        if (!pstmt.isClosed()) pstmt.close();
        if (!conn.isClosed()) conn.close();

        return wifis;
    }

    public static Wifi getWifiDetail(double lat, double lnt, Integer id) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = DriverManager.getConnection(url, user, password);
        //Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM TB_WIFI_INFO WHERE WIFI_ID=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setDouble(1, id);

        ResultSet rs = pstmt.executeQuery();

        Wifi wifi = new Wifi();
        while (rs.next()){
            Integer WIFI_ID = rs.getInt("WIFI_ID");
            String MGR_NO = rs.getString("MGR_NO");
            String WRDOFC = rs.getString("WRDOFC");
            String WIFI_NM = rs.getString("WIFI_NM");
            String ADRES1 = rs.getString("ADRES1");
            String ADRES2 = rs.getString("ADRES2");
            String INSTL_FLOOR = rs.getString("INSTL_FLOOR");
            String INSTL_TY = rs.getString("INSTL_TY");
            String INSTL_MBY = rs.getString("INSTL_MBY");
            String SVC_SE = rs.getString("SVC_SE");
            String WIFI_TY = rs.getString("WIFI_TY");
            String INSTL_YEAR = rs.getString("INSTL_YEAR");
            String INOUT = rs.getString("INOUT_DOOR");
            String WIFI_ENV = rs.getString("WIFI_ENV");
            Double LAT = rs.getDouble("LAT");
            Double LNT = rs.getDouble("LNT");
            String WORK_DT = rs.getString("WORK_DT");

            Double dis = WifiService.distance(lat,lnt,LAT,LNT);

            wifi.setWIFI_ID(WIFI_ID);
            wifi.setDistance(dis);
            wifi.setMGR_NO(MGR_NO);
            wifi.setWRDOFC(WRDOFC);
            wifi.setWIFI_NM(WIFI_NM);
            wifi.setADRES1(ADRES1);
            wifi.setADRES2(ADRES2);
            wifi.setINSTL_FLOOR(INSTL_FLOOR);
            wifi.setINSTL_TY(INSTL_TY);
            wifi.setINSTL_MBY(INSTL_MBY);
            wifi.setSVC_SE(SVC_SE);
            wifi.setWIFI_TY(WIFI_TY);
            wifi.setINSTL_YEAR(INSTL_YEAR);
            wifi.setINOUT_DOOR(INOUT);
            wifi.setWIFI_ENV(WIFI_ENV);
            wifi.setLAT(LAT);
            wifi.setLNT(LNT);
            wifi.setWORK_DT(WORK_DT);
        }

        if (!rs.isClosed())  rs.close();
        if (!pstmt.isClosed()) pstmt.close();
        if (!conn.isClosed()) conn.close();

        return wifi;
    }
}
