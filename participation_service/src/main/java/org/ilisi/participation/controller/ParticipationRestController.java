package org.ilisi.participation.controller;

import org.ilisi.participation.clients.UserServiceFeignClient;
import org.ilisi.participation.dto.EventDTO;
import org.ilisi.participation.dto.UserDTO;
import org.ilisi.participation.entities.Participation;
import org.ilisi.participation.clients.EventServiceFeignClient;
import org.ilisi.participation.repository.ParticipationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participations")
public class ParticipationRestController {

    private final ParticipationRepository participationRepository;
    private final UserServiceFeignClient userServiceClient;
    private final EventServiceFeignClient eventServiceClient;

    public ParticipationRestController(ParticipationRepository participationRepository,
                                       UserServiceFeignClient userServiceClient,
                                       EventServiceFeignClient eventServiceClient) {
        this.participationRepository = participationRepository;
        this.userServiceClient = userServiceClient;
        this.eventServiceClient = eventServiceClient;
    }

    @PostMapping("/{eventId}/add/{userId}")
    public ResponseEntity<String> addParticipant(@PathVariable Long eventId, @PathVariable Long userId) {
        // Appel des services distants
        EventDTO event = eventServiceClient.getEventById(eventId);
        UserDTO user = userServiceClient.getUserById(userId);

        if (event.getCurrentNumParticipants() >= event.getMaxParticipants()) {
            return ResponseEntity.badRequest().body("Event is full. No more participants can be added.");
        }

        // Vérification de la participation existante
        boolean alreadyParticipating = participationRepository.existsByEventIdAndUserId(eventId, userId);
        if (alreadyParticipating) {
            return ResponseEntity.badRequest().body("User is already participating in this event.");
        }

        // Création de la participation
        Participation participation = Participation.builder()
                .eventId(eventId)
                .userId(userId)
                .build();
        participationRepository.save(participation);

        // Mettre à jour le nombre actuel de participants via FeignClient
        eventServiceClient.incrementParticipants(eventId);

        return ResponseEntity.ok("User added to the event successfully.");
    }

    @GetMapping("/{eventId}/participants")
    public ResponseEntity<List<UserDTO>> getParticipants(@PathVariable Long eventId) {
        List<Long> userIds = participationRepository.findUserIdsByEventId(eventId);
        List<UserDTO> participants = userServiceClient.getUsersByIds(userIds);
        return ResponseEntity.ok(participants);
    }
}
