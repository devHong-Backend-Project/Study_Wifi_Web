package wifi;

public class Bookmark {

    private Integer BOOKMARK_ID;
    private Integer GROUP_ID;
    private String GROUP_NAME;
    private String WIFI_NM;
    private String CREATE_AT;
    private Integer WIFI_ID;

    public Integer getWIFI_ID() {
        return WIFI_ID;
    }

    public void setWIFI_ID(Integer WIFI_ID) {
        this.WIFI_ID = WIFI_ID;
    }

    public String getGROUP_NAME() {
        return GROUP_NAME;
    }

    public void setGROUP_NAME(String GROUP_NAME) {
        this.GROUP_NAME = GROUP_NAME;
    }

    public Integer getBOOKMARK_ID() {
        return BOOKMARK_ID;
    }

    public void setBOOKMARK_ID(Integer BOOKMARK_ID) {
        this.BOOKMARK_ID = BOOKMARK_ID;
    }

    public Integer getGROUP_ID() {
        return GROUP_ID;
    }

    public void setGROUP_ID(Integer GROUP_ID) {
        this.GROUP_ID = GROUP_ID;
    }

    public String getWIFI_NM() {
        return WIFI_NM;
    }

    public void setWIFI_NM(String WIFI_NM) {
        this.WIFI_NM = WIFI_NM;
    }

    public String getCREATE_AT() {
        return CREATE_AT;
    }

    public void setCREATE_AT(String CREATE_AT) {
        this.CREATE_AT = CREATE_AT;
    }
}
