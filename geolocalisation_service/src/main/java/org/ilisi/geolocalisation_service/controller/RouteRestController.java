package org.ilisi.geolocalisation_service.controller;

import org.ilisi.geolocalisation_service.entities.Route;
import org.ilisi.geolocalisation_service.service.RouteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/routes")
public class RouteRestController {

    private final RouteService routeService;

    // Injection par constructeur
    public RouteRestController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping
    public List<Route> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Route> getRouteById(@PathVariable Long id) {
        Optional<Route> route = routeService.getRouteById(id);
        return route.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Route> createRoute(@RequestBody List<List<Double>> coordinatesList) {
        Route route = new Route();
        route.setPathFromCoordinates(coordinatesList);
        Route savedRoute = routeService.saveRoute(route);
        return ResponseEntity.ok(savedRoute);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        if (routeService.getRouteById(id).isPresent()) {
            routeService.deleteRoute(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
