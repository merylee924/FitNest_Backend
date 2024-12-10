package fitnest.notif_service.dto;

public class TokenResponse {
    private Long userid;
    private String token;

    public void setToken(String token) {
        this.token = token;
    }
    public String getToken(){return this.token;}
    public  Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
}
