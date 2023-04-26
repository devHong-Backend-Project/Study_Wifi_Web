package wifi;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        List<Wifi> wifis = WifiDB.selectNearWifi(20,1,1);
        for (Wifi w: wifis){
            System.out.println(w.getWIFI_NM());
        }
    }
}
