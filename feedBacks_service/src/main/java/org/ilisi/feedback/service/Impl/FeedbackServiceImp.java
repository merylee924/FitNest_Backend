package org.ilisi.feedback.service.Impl;

import org.ilisi.feedback.clients.EventClient;
import org.ilisi.feedback.clients.ParticipationClient;
import org.ilisi.feedback.dto.EventEndedDTO;
import org.ilisi.feedback.dto.FeedbackDTO;
import org.ilisi.feedback.dto.ParticipationDTO;
import org.ilisi.feedback.entities.Feedback;
import org.ilisi.feedback.repo.FeedbackRepository;
import org.ilisi.feedback.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackServiceImp implements IFeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private EventClient eventClient;
    @Autowired
    private ParticipationClient participationClient;

    @Override
    public Feedback saveFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback getFeedbackById(Long id) {
        return feedbackRepository.findById(id).orElse(null);
    }



    @Override
    public List<EventEndedDTO> getEventsByUserId(Long userId) {
        List<EventEndedDTO> allEndedEvents = eventClient.getCompletedEvents();
        System.out.println("Tous les événements terminés :");
        allEndedEvents.forEach(event -> System.out.println("Événement terminé - ID: " + event.getIdEvent() + ", Nom: " + event.getTitle()));

        // Filtrage des événements auxquels l'utilisateur a participé
        List<EventEndedDTO> filteredEvents = allEndedEvents.stream()
                .filter(event -> {
                    // Récupérer les participations pour cet événement
                    List<ParticipationDTO> participations = participationClient.getParticipationsByEventEndedId(event.getIdEvent());

                    System.out.println("Participations pour l'événement ID " + event.getIdEvent() + ":");
                    participations.forEach(participation -> System.out.println("Participation - UserID: " + participation.getUserId()));

                    // Vérifier si l'utilisateur a participé
                    boolean userParticipated = participations.stream()
                            .anyMatch(participation -> participation.getUserId().equals(userId));

                    if (userParticipated) {
                        System.out.println("L'utilisateur avec ID " + userId + " a participé à l'événement ID " + event.getIdEvent());

                        // Vérifier si l'utilisateur a soumis un feedback
                        boolean feedbackSubmitted = participations.stream()
                                .anyMatch(participation -> feedbackRepository.existsByParticipationId(participation.getId()));

                        if (feedbackSubmitted) {
                            System.out.println("L'utilisateur avec ID " + userId + " a déjà soumis un feedback pour l'événement ID " + event.getIdEvent());
                        } else {
                            System.out.println("L'utilisateur avec ID " + userId + " n'a PAS soumis de feedback pour l'événement ID " + event.getIdEvent());
                        }

                        // Si l'utilisateur a participé, inclure l'événement dans la liste
                        return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());

        System.out.println("Événements auxquels l'utilisateur a participé :");
        filteredEvents.forEach(event -> System.out.println("Événement ID: " + event.getIdEvent() + ", Nom: " + event.getTitle()));

        return filteredEvents;
    }

   /* @Override
    public List<FeedbackDTO> getFeedbacksByOrganizerId(Long organizerId) {
        List<FeedbackDTO> feedbackDTOs = new ArrayList<>();

        // Récupérer les participations associées à un organisateur
        List<ParticipationDTO> participations = participationClient.getParticipationsByOrganizerId(organizerId);

        // Pour chaque participation, récupérer les feedbacks associés
        for (ParticipationDTO participation : participations) {
            // Récupérer les feedbacks associés à cette participation
            List<Feedback> feedbacks = feedbackRepository.findByParticipationId(participation.getId());

            // Pour chaque feedback, récupérer les informations supplémentaires (nom, prénom, image, etc.)
            for (Feedback feedback : feedbacks) {
                String userName = participation.getUserName(); // Exemple de récupération des données utilisateur
                String userLastName = participation.getUserLastName();
                String userImage = participation.getUserImage();
                String eventName = participation.getEventName(); // Récupérer le nom de l'événement

                // Créer le DTO pour le feedback
                FeedbackDTO feedbackDTO = new FeedbackDTO(feedback, participation, userName, userLastName, userImage, eventName);
                feedbackDTOs.add(feedbackDTO);
            }
        }

        return feedbackDTOs;
    }*/
}
