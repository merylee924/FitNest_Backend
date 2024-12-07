package org.ilisi.tracking_service.controller;

import org.ilisi.tracking_service.entities.Tracking;
import org.ilisi.tracking_service.service.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tracking")
public class TrackingController {

    @Autowired
    private TrackingService service;

    @PostMapping
    public Tracking saveTrackingData(@RequestBody Tracking tracking) {
        return service.saveTrackingData(tracking);
    }

    @GetMapping("/{eventId}")
    public List<Tracking> getTrackingData(@PathVariable int eventId) {
        return service.getTrackingDataByEventId(eventId);
    }
}
