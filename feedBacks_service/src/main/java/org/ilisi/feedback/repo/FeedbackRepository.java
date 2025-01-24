package org.ilisi.feedback.repo;



import org.ilisi.feedback.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire

    // Vérifie si un feedback existe pour un ID de participation donné
    boolean existsByParticipationId(Long participationId);
    List<Feedback> findByParticipationId(Long participationId);
}