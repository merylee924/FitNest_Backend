package org.ilisi.feedback.dto;

import lombok.*;
import org.ilisi.feedback.entities.Feedback;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedbackDTO {

    private Long feedbackId;
    private String comment;
    private Integer rating;

    private Long userId;
    private String userName;
    private String userLastName;
    private String userImage;

    private String eventName;

    public FeedbackDTO(Feedback feedback, ParticipationDTO participation, String userName, String userLastName, String userImage, String eventName) {
        this.feedbackId = feedback.getId();
        this.comment = feedback.getComment();
        this.rating = feedback.getRating();
        this.userId = participation.getUserId();
        this.userName = userName;
        this.userLastName = userLastName;
        this.userImage = userImage;
        this.eventName = eventName;
    }
}
