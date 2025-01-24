package org.ilisi.feedback.service;

import org.ilisi.feedback.dto.EventEndedDTO;
import org.ilisi.feedback.dto.FeedbackDTO;
import org.ilisi.feedback.entities.Feedback;

import java.util.List;

public interface IFeedbackService {


    public Feedback saveFeedback(Feedback feedback);

    public List<Feedback> getAllFeedbacks();

    public Feedback getFeedbackById(Long id);


    List<EventEndedDTO> getEventsByUserId(Long id);
    //List<FeedbackDTO> getFeedbacksByOrganizerId(Long organizerId);
}
