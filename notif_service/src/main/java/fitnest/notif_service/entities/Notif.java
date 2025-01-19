package fitnest.notif_service.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "notifications") // MongoDB collection name
public class Notif {

    @Id
    private String id;
    private Long recipient;
    private NotifType type;
    private String content;
    private LocalDateTime timestamp;
    private String token;
    private Long eventid;

    public Notif() {
    }

    public Notif(Long recipient, NotifType type, String content, String status, LocalDateTime timestamp, String topic, String token) {
        this.recipient = recipient;
        this.type = type;
        this.content = content;
        this.timestamp = timestamp;
        this.token = token;
    }

    public Long getRecipient() {
        return recipient;
    }

    public void setRecipient(Long recipient) {
        this.recipient = recipient;
    }

    public NotifType getType() {
        return type;
    }

    public void setType(NotifType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
