package org.ilisi.participation.repository;

import org.ilisi.participation.entities.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Long> {

    boolean existsByUserIdAndEventId(Long userId, Long eventId);

    Optional<Participation> findByUserIdAndEventId(Long userId, Long eventId);

    List<Participation> findByUserId(Long userId);

    List<Participation> findByEventId(Long eventId);

}
