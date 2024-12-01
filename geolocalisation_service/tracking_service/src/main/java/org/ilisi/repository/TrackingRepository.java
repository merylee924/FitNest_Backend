package org.ilisi.repository;

import org.ilisi.entities.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TrackingRepository extends JpaRepository<Tracking, Long> {

    // Récupérer les enregistrements pour un participant
    List<Tracking> findByParticipantId(Long participantId);

    // Récupérer les enregistrements pour un événement
    List<Tracking> findByEventId(Long eventId);
}
