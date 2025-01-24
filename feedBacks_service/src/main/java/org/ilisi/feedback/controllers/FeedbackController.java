package org.ilisi.feedback.controllers;


import org.ilisi.feedback.clients.ParticipationClient;
import org.ilisi.feedback.dto.EventEndedDTO;
import org.ilisi.feedback.dto.FeedbackDTO;
import org.ilisi.feedback.entities.Feedback;
import org.ilisi.feedback.service.IFeedbackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    @Autowired
    private IFeedbackService feedbackService;
    @Autowired
    private ParticipationClient participationClient;

    @PostMapping
    public ResponseEntity<Feedback> submitFeedback(@RequestBody Feedback feedback) {

        // Vérifier si les IDs user et event sont présents dans l'objet reçu
        if (feedback.getParticipationDTO() == null ||
                feedback.getParticipationDTO().getUserId() == null ||
                feedback.getParticipationDTO().getEventId() == null) {
            return ResponseEntity.badRequest().build(); // Retourne 400 si des champs nécessaires sont manquants
        }

        // Ajouter un bloc try-catch pour gérer les erreurs lors de la récupération de l'ID de participation
        Long participationId = null;
        try {
            participationId = participationClient.getParticipationIdByUserAndEvent(
                    feedback.getParticipationDTO().getUserId(),
                    feedback.getParticipationDTO().getEventId()
            );
        } catch (Exception e) {
            // Log de l'erreur et retour d'un statut 500 si un problème survient
            System.err.println("Error during participation lookup: " + e.getMessage());
            return ResponseEntity.status(500).body(null);  // Retourne 500 en cas d'erreur
        }

        // Vérifier si participationId est null
        if (participationId == null) {
            return ResponseEntity.badRequest().body(null); // Retourne 400 si aucune participation n'est trouvée
        }

        // Associer l'ID de participation au feedback
        feedback.setParticipationId(participationId);

        // Sauvegarde du feedback avec gestion des erreurs
        try {
            Feedback updatedFeedback = feedbackService.saveFeedback(feedback);
            return ResponseEntity.ok(updatedFeedback);  // Retourne le feedback mis à jour
        } catch (Exception e) {
            // Log de l'erreur et retour d'un statut 500 en cas d'échec de la sauvegarde
            System.err.println("Error saving feedback: " + e.getMessage());
            return ResponseEntity.status(500).body(null);  // Retourne 500 en cas d'erreur
        }
    }

    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        return ResponseEntity.ok(feedbackService.getAllFeedbacks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long id) {
        Feedback feedback = feedbackService.getFeedbackById(id);
        if (feedback != null) {
            return ResponseEntity.ok(feedback);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/event/User/{id}")
    public List<EventEndedDTO> getEventByUserId(@PathVariable Long id)
    {
        return feedbackService.getEventsByUserId(id);
    }

/*    @GetMapping("/organizer/{organizerId}")
    public ResponseEntity<List<FeedbackDTO>> getFeedbacksByOrganizerId(@PathVariable Long organizerId) {
        List<FeedbackDTO> feedbacks = feedbackService.getFeedbacksByOrganizerId(organizerId);
        if (feedbacks.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(feedbacks);
    }
*/

}