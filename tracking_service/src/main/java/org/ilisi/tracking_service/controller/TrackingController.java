package org.ilisi.tracking_service.controller;

import lombok.RequiredArgsConstructor;
import org.ilisi.tracking_service.service.TrackingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/tracking")
@RequiredArgsConstructor
public class TrackingController {

    private final TrackingService trackingService;

    @GetMapping("/{trackingId}/progression")
    public ResponseEntity<Map<String, Object>> getProgression(@PathVariable Long trackingId) {
        Map<String, Object> progressData = trackingService.getParticipantProgression(trackingId);
        return ResponseEntity.ok(progressData);
    }
}
