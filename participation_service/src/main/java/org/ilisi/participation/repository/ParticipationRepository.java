package org.ilisi.participation.repository;

import org.ilisi.participation.entities.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    boolean existsByEventIdAndUserId(Long eventId, Long userId);

    List<Long> findUserIdsByEventId(Long eventId);
}
