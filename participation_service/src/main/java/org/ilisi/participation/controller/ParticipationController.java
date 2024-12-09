package org.ilisi.participation.controller;

import org.ilisi.participation.clients.*;
import org.ilisi.participation.model.*;
import org.ilisi.participation.dto.EventDTO;
import org.ilisi.participation.dto.UserDTO;
import org.ilisi.participation.entities.Participation;
import org.ilisi.participation.repository.ParticipationRepository;
import org.ilisi.participation.service.ParticipationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participations")
public class ParticipationController {

    private final ParticipationService participationService;

    public ParticipationController(ParticipationService participationService) {
        this.participationService = participationService;
    }
    @PostMapping("/createParticipation")
    public ResponseEntity<String> createParticipation(@RequestParam Long userId, @RequestParam Long eventId) {
        try {
            participationService.createParticipation(userId, eventId);
            return ResponseEntity.ok("Participation created successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already participates in this event.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Event is full.");
        }
    }
    @GetMapping("/getDetailParticipation/{id}")
    public ResponseEntity<Participation> getParticipationDetails(@PathVariable Long id) {
        Participation participation = participationService.getParticipationDetails(id);
        return ResponseEntity.ok(participation);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> cancelParticipation(
            @RequestParam Long userId,
            @RequestParam Long eventId) {
        participationService.cancelParticipation(userId, eventId);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/accept")
    public ResponseEntity<Void> acceptParticipation(
            @RequestParam Long userId,
            @RequestParam Long eventId) {
        participationService.acceptParticipation(userId, eventId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/user/{userId}/participations")
    public ResponseEntity<List<Event>> getParticipationsForEvent(@PathVariable Long userId) {
        List<Event> events = participationService.getParticipantionEventsByUserId(userId);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Participation>> getParticipationsByEventId(@PathVariable Long eventId) {
        List<Participation> participations = participationService.getParticipationsByEventId(eventId);
        return ResponseEntity.ok(participations);
    }

}

