package fitnest.notif_service.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("fcmTokens")
public class FcmToken {
    @Id
    private String id;
    private Long userid;
    private String token;

    public FcmToken(String token, Long  userid) {
        this.token = token;
       this.userid = userid;
    }

    public  String getToken(){return this.token;}
    public void setToken(String token) {
        this.token = token;
    }

    public  Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
}
