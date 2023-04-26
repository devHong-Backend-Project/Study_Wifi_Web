package wifi;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HttpRequest {



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
            boolean success = WifiDB.insertData(client, request);
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

