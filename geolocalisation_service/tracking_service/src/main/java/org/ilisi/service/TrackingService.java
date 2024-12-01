package org.ilisi.service;

import org.ilisi.entities.Tracking;
import org.ilisi.repository.TrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TrackingService {

    @Autowired
    private TrackingRepository trackingRecordRepository;

    // Ajouter un enregistrement de tracking
    public Tracking addTrackingRecord(Long participantId, Long eventId, Double latitude, Double longitude) {
        Tracking trackingRecord = new Tracking();
        trackingRecord.setParticipantId(participantId);
        trackingRecord.setEventId(eventId);
        trackingRecord.setLatitude(latitude);
        trackingRecord.setLongitude(longitude);
        trackingRecord.setTimestamp(LocalDateTime.now());
        return trackingRecordRepository.save(trackingRecord);
    }

    // Récupérer les données de tracking d'un participant
    public List<Tracking> getTrackingRecordsByParticipant(Long participantId) {
        return trackingRecordRepository.findByParticipantId(participantId);
    }

    // Récupérer les données de tracking d'un événement
    public List<Tracking> getTrackingRecordsByEvent(Long eventId) {
        return trackingRecordRepository.findByEventId(eventId);
    }
}
