package wifi;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.sql.*;

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


    public static Integer WifiApiReq() throws IOException, SQLException {

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
        return totalCount;
        }
    }

