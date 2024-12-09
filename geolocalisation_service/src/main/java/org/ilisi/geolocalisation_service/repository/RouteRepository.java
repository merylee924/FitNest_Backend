package org.ilisi.geolocalisation_service.repository;

import org.ilisi.geolocalisation_service.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository  extends JpaRepository<Route, Long> {
    @Query(value = "SELECT ST_Distance(" +
            "ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326)::geography, " +
            "path::geography) " +
            "FROM route WHERE id = :routeId", nativeQuery = true)
    Double calculateDistanceToRoute(@Param("routeId") Long routeId,
                                    @Param("latitude") Double latitude,
                                    @Param("longitude") Double longitude);
}
