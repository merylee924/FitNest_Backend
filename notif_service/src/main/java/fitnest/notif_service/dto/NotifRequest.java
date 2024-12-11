package fitnest.notif_service.dto;

import fitnest.notif_service.entities.NotifType;

public class NotifRequest {

    private Long recipient;
    private String type;
    private String content;
    private String token;
    private Long eventid;

    public Long getEventid() {
        return eventid;
    }

    public void setEventid(Long eventid) {
        this.eventid = eventid;
    }

    public NotifRequest() {}

    // Constructor with parameters
    public NotifRequest(Long recipient, String type, String content, String token) {
        this.recipient = recipient;
        this.type = type;
        this.content = content;
        this.token = token;
    }

    // Getter and Setter for recipient
    public Long getRecipient() {
        return recipient;
    }

    public void setRecipient(Long recipient) {
        this.recipient = recipient;
    }

    // Getter and Setter for type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Getter and Setter for content
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // Getter and Setter for token
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
