import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.sql.*;

public class HttpRequest {

    private final static String url = "jdbc:mysql://localhost:3306/wifi";
    private final static String user = "root";
    private final static String password = "Dnjfchs23";

    public static Integer getTotalCount(OkHttpClient client) throws IOException {
        Request request = new Request.Builder()
                .url("http://openapi.seoul.go.kr:8088/7275616250676d7233317868675579/json/TbPublicWifiInfo/1/1/")
                .build();
        try(Response response = client.newCall(request).execute()){
            JsonParser parser = new JsonParser();
            Object obj = parser.parse(response.body().string());
            JsonObject jsonObject = (JsonObject) obj;
            Integer totalCount = jsonObject.getAsJsonObject("TbPublicWifiInfo").get("list_total_count").getAsInt();
            return totalCount;
        }
    }

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
    public static void main(String[] args) throws IOException, SQLException {

        OkHttpClient client = new OkHttpClient();

        Integer totalCount = getTotalCount(client);
        //totalCount = 1000;
        System.out.println(totalCount);
        Integer startIndex = 1;
        for (int i = 1; i<=totalCount/1000+1; i++){
            Integer endIndex;
            if (i*1000>totalCount){
                endIndex = totalCount;
                if (startIndex>endIndex){
                    break;
                }
            }else{
                endIndex = i*1000;
            }
            Request request = new Request.Builder()
                    .url(String.format("http://openapi.seoul.go.kr:8088/7275616250676d7233317868675579/json/TbPublicWifiInfo/%d/%d/",startIndex,endIndex))
                    .build();
            boolean success = insertData(client, request);
            if (success) {System.out.println("insert success");}
            else{System.out.println("insert failed");}
            startIndex = endIndex + 1;
            }
        }

        //{"X_SWIFI_MGR_NO":"---GR000001",
        // "X_SWIFI_WRDOFC":"구로구",
        // "X_SWIFI_MAIN_NM":"안양천공원",
        // "X_SWIFI_ADRES1":"구로동 621-8",
        // "X_SWIFI_ADRES2":"동양미래대학 앞 농구장",
        // "X_SWIFI_INSTL_FLOOR":"",
        // "X_SWIFI_INSTL_TY":"3. 공원(하천)",
        // "X_SWIFI_INSTL_MBY":"기타",
        // "X_SWIFI_SVC_SE":"공공WiFi",
        // "X_SWIFI_CMCWR":"자가망_U무선망",
        // "X_SWIFI_CNSTC_YEAR":"2016",
        // "X_SWIFI_INOUT_DOOR":"실외",
        // "X_SWIFI_REMARS3":"",
        // "LAT":"126.86959",
        // "LNT":"37.500145",
        // "WORK_DTTM":"2023-04-19 10:58:19.0"}
    }

