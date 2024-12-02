package org.ilisi.tracking_service.repository;

import org.ilisi.tracking_service.entities.Tracking;
import org.ilisi.tracking_service.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TrackingRepository extends JpaRepository<Tracking, Long> {

    @Query("SELECT ST_DistanceSphere(t.location, ST_ClosestPoint(r.path, t.location)) " +
            "FROM Tracking t JOIN Event e ON t.eventId = e.id " +
            "JOIN Route r ON e.route.id = r.id " +
            "WHERE t.id = :trackingId")
    Double getDistanceToRoute(@Param("trackingId") Long trackingId);

    @Query("SELECT ST_LineLocatePoint(r.path, t.location) " +
            "FROM Tracking t JOIN Event e ON t.eventId = e.id " +
            "JOIN Route r ON e.route.id = r.id " +
            "WHERE t.id = :trackingId")
    Double getProgression(@Param("trackingId") Long trackingId);
}
