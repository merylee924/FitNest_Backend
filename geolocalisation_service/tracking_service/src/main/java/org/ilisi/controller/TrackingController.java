package org.ilisi.controller;

import org.ilisi.entities.Tracking;
import org.ilisi.service.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tracking")
public class TrackingController {

    @Autowired
    private TrackingService trackingService;

    // Endpoint pour ajouter un enregistrement de tracking
    @PostMapping
    public ResponseEntity<Tracking> addTrackingRecord(
            @RequestParam Long participantId,
            @RequestParam Long eventId,
            @RequestParam Double latitude,
            @RequestParam Double longitude) {
        Tracking record = trackingService.addTrackingRecord(participantId, eventId, latitude, longitude);
        return ResponseEntity.ok(record);
    }

    // Endpoint pour récupérer les données de tracking d'un participant
    @GetMapping("/participant/{participantId}")
    public ResponseEntity<List<Tracking>> getTrackingRecordsByParticipant(@PathVariable Long participantId) {
        List<Tracking> records = trackingService.getTrackingRecordsByParticipant(participantId);
        return ResponseEntity.ok(records);
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Tracking>> getTrackingRecordsByEvent(@PathVariable Long eventId) {
        List<Tracking> records = trackingService.getTrackingRecordsByEvent(eventId);
        return ResponseEntity.ok(records);
    }
}
