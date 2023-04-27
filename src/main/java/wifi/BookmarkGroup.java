package wifi;

public class BookmarkGroup {

    private Integer GROUP_ID;
    private String GROUP_NAME;
    private Integer ORDER_NO;
    private String CREATE_AT;
    private String UPDATE_AT;

    public Integer getGROUP_ID() {
        return GROUP_ID;
    }

    public void setGROUP_ID(Integer GROUP_ID) {
        this.GROUP_ID = GROUP_ID;
    }

    public String getGROUP_NAME() {
        return GROUP_NAME;
    }

    public void setGROUP_NAME(String GROUP_NAME) {
        this.GROUP_NAME = GROUP_NAME;
    }

    public Integer getORDER_NO() {
        return ORDER_NO;
    }

    public void setORDER_NO(Integer ORDER_NO) {
        this.ORDER_NO = ORDER_NO;
    }

    public String getCREATE_AT() {
        return CREATE_AT;
    }

    public void setCREATE_AT(String CREATE_AT) {
        this.CREATE_AT = CREATE_AT;
    }

    public String getUPDATE_AT() {
        return UPDATE_AT;
    }

    public void setUPDATE_AT(String UPDATE_AT) {
        this.UPDATE_AT = UPDATE_AT;
    }
}
