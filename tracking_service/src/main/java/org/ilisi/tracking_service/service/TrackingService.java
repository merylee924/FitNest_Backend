package org.ilisi.tracking_service.service;

import org.ilisi.tracking_service.entities.Tracking;
import org.ilisi.tracking_service.repository.TrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackingService {

    @Autowired
    private TrackingRepository repository;

    public Tracking saveTrackingData(Tracking tracking) {
        return repository.save(tracking);
    }

    public List<Tracking> getTrackingDataByEventId(int eventId) {
        return repository.findAll().stream()
                .filter(tracking -> tracking.getEventId() == eventId)
                .toList();
    }
}
