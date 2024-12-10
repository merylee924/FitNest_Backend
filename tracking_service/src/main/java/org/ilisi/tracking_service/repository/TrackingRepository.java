package org.ilisi.tracking_service.repository;

import org.ilisi.tracking_service.entities.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackingRepository extends JpaRepository<Tracking, Long> {
    // Ajoutez des méthodes personnalisées si nécessaire
}
