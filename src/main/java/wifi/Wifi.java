package wifi;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Wifi {
    private Integer WIFI_ID;
    private Double distance;
    private String MGR_NO;
    private String WRDOFC;
    private String WIFI_NM;
    private String ADRES1;
    private String ADRES2;
    private String INSTL_FLOOR;
    private String INSTL_TY;
    private String INSTL_MBY;
    private String SVC_SE;
    private String WIFI_TY;
    private String INSTL_YEAR;
    private String INOUT_DOOR;
    private String WIFI_ENV;
    private Double LAT;
    private Double LNT;
    private String WORK_DT;

    public List<String> getAllMember(){
        List<String> list = new ArrayList<>();
        Double dis = distance;
        dis = Math.round(dis*10000)/10000.0;
        list.add(String.valueOf(dis));
        list.add(String.valueOf(MGR_NO));
        list.add(String.valueOf(WRDOFC));
        list.add(String.valueOf(WIFI_NM));
        list.add(String.valueOf(ADRES1));
        list.add(String.valueOf(ADRES2));
        list.add(String.valueOf(INSTL_FLOOR));
        list.add(String.valueOf(INSTL_TY));
        list.add(String.valueOf(INSTL_MBY));
        list.add(String.valueOf(SVC_SE));
        list.add(String.valueOf(WIFI_TY));
        list.add(String.valueOf(INSTL_YEAR));
        list.add(String.valueOf(INOUT_DOOR));
        list.add(String.valueOf(WIFI_ENV));
        list.add(String.valueOf(LAT));
        list.add(String.valueOf(LNT));
        list.add(String.valueOf(WORK_DT));

        return list;
    }

    public Integer getWIFI_ID() {
        return WIFI_ID;
    }

    public void setWIFI_ID(Integer WIFI_ID) {
        this.WIFI_ID = WIFI_ID;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getMGR_NO() {
        return MGR_NO;
    }

    public void setMGR_NO(String MGR_NO) {
        this.MGR_NO = MGR_NO;
    }

    public String getWRDOFC() {
        return WRDOFC;
    }

    public void setWRDOFC(String WRDOFC) {
        this.WRDOFC = WRDOFC;
    }

    public String getWIFI_NM() {
        return WIFI_NM;
    }

    public void setWIFI_NM(String WIFI_NM) {
        this.WIFI_NM = WIFI_NM;
    }

    public String getADRES1() {
        return ADRES1;
    }

    public void setADRES1(String ADRES1) {
        this.ADRES1 = ADRES1;
    }

    public String getADRES2() {
        return ADRES2;
    }

    public void setADRES2(String ADRES2) {
        this.ADRES2 = ADRES2;
    }

    public String getINSTL_FLOOR() {
        return INSTL_FLOOR;
    }

    public void setINSTL_FLOOR(String INSTL_FLOOR) {
        this.INSTL_FLOOR = INSTL_FLOOR;
    }

    public String getINSTL_TY() {
        return INSTL_TY;
    }

    public void setINSTL_TY(String INSTL_TY) {
        this.INSTL_TY = INSTL_TY;
    }

    public String getINSTL_MBY() {
        return INSTL_MBY;
    }

    public void setINSTL_MBY(String INSTL_MBY) {
        this.INSTL_MBY = INSTL_MBY;
    }

    public String getSVC_SE() {
        return SVC_SE;
    }

    public void setSVC_SE(String SVC_SE) {
        this.SVC_SE = SVC_SE;
    }

    public String getWIFI_TY() {
        return WIFI_TY;
    }

    public void setWIFI_TY(String WIFI_TY) {
        this.WIFI_TY = WIFI_TY;
    }

    public String getINSTL_YEAR() {
        return INSTL_YEAR;
    }

    public void setINSTL_YEAR(String INSTL_YEAR) {
        this.INSTL_YEAR = INSTL_YEAR;
    }

    public String getINOUT_DOOR() {
        return INOUT_DOOR;
    }

    public void setINOUT_DOOR(String INOUT_DOOR) {
        this.INOUT_DOOR = INOUT_DOOR;
    }

    public String getWIFI_ENV() {
        return WIFI_ENV;
    }

    public void setWIFI_ENV(String WIFI_ENV) {
        this.WIFI_ENV = WIFI_ENV;
    }

    public Double getLAT() {
        return LAT;
    }

    public void setLAT(Double LAT) {
        this.LAT = LAT;
    }

    public Double getLNT() {
        return LNT;
    }

    public void setLNT(Double LNT) {
        this.LNT = LNT;
    }

    public String getWORK_DT() {
        return WORK_DT;
    }

    public void setWORK_DT(String WORK_DT) {
        this.WORK_DT = WORK_DT;
    }
}
