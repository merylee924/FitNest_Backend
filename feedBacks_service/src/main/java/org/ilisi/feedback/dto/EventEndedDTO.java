package org.ilisi.feedback.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventEndedDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long idEvent;
    private Long idCoordinator;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private int maxParticipants;
    private String image ;

    private String sportCategory;  // Include this if you need it
    private Long idFeedback;

    // Optionally add location as latitude and longitude, if relevant
    private String location;  // Modify based on your needs
}
