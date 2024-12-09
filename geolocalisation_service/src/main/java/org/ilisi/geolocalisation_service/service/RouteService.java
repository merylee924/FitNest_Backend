package org.ilisi.geolocalisation_service.service;

import org.ilisi.geolocalisation_service.entities.Location;
import org.ilisi.geolocalisation_service.entities.Route;
import org.ilisi.geolocalisation_service.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Optional<Route> getRouteById(Long id) {
        return routeRepository.findById(id);
    }

    public Route saveRoute(Route route) {
        return routeRepository.save(route);
    }

    public void deleteRoute(Long id) {
        routeRepository.deleteById(id);
    }
    public Double calculateDistanceToRoute(Long routeId, Double latitude, Double longitude) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new IllegalArgumentException("Route introuvable"));

        // Appeler la méthode native pour calculer la distance
        return routeRepository.calculateDistanceToRoute(routeId, latitude, longitude);
    }
}
