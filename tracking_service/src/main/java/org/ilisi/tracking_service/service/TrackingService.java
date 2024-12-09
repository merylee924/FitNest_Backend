package org.ilisi.tracking_service.service;

import lombok.RequiredArgsConstructor;
import org.ilisi.tracking_service.repository.TrackingRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TrackingService {

    private final TrackingRepository trackingRepository;

    public Map<String, Object> getParticipantProgression(Long trackingId) {
        Double distanceToRoute = trackingRepository.getDistanceToRoute(trackingId);
        Double progression = trackingRepository.getProgression(trackingId);

        Map<String, Object> progressData = new HashMap<>();
        progressData.put("distanceToRoute", distanceToRoute);
        progressData.put("progressionPercentage", (progression != null) ? progression * 100 : 0);
        return progressData;
    }
}
