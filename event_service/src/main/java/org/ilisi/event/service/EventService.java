package org.ilisi.event.service;

import org.ilisi.event.clients.AuthServiceFeignClient;
import org.ilisi.event.clients.GeolocationFeignClient;
import org.ilisi.event.dtos.EventDto;
import org.ilisi.event.entities.Event;
import org.ilisi.event.entities.SportCategory;
import org.ilisi.event.exceptions.LocationNotFoundException;
import org.ilisi.event.exceptions.RouteNotFoundException;
import org.ilisi.event.exceptions.SportCategoryNotFoundException;
import org.ilisi.event.repository.EventRepository;
import org.ilisi.event.repository.SportCategoryRepository;
import org.ilisi.event.model.Location;
import org.ilisi.event.model.Route;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final SportCategoryRepository sportCategoryRepository;
    private final GeolocationFeignClient geoFeignClient;
    private final AuthServiceFeignClient authServiceClient;


    @Autowired
    public EventService(EventRepository eventRepository, SportCategoryRepository sportCategoryRepository, GeolocationFeignClient geoFeignClient, AuthServiceFeignClient authServiceClient) {
        this.eventRepository = eventRepository;
        this.sportCategoryRepository = sportCategoryRepository;
        this.geoFeignClient = geoFeignClient;
        this.authServiceClient = authServiceClient;
    }

    public Event createEvent(Event event) {
        SportCategory sportCategory = getSportCategoryById(event.getSportCategory().getId());
        event.setSportCategory(sportCategory);
        if (sportCategory.isRequiresRoute()) {
            event = handleRoute(event);
        } else {
            event = handleLocation(event);
        }
       if (event.getOrganizerId() != null) {
            try {
                event.setUser(authServiceClient.getUserById(event.getOrganizerId()));
            } catch (Exception e) {
                throw new RuntimeException("Impossible de récupérer les informations de l'organisateur : " + e.getMessage());
            }
        }
        return eventRepository.save(event);
    }

    private Event handleLocation(Event event) {
        if (event.getLocationId() != null) {
            Location location = geoFeignClient.getLocationById(event.getLocationId());
            if (location == null) {
                throw new LocationNotFoundException("Location not found for event ID: " + event.getId());
            }
            event.setLocation(location);
        }
        return event;
    }

    private Event handleRoute(Event event) {
        if (event.getRouteId() != null) {
            Route route = geoFeignClient.getRouteById(event.getRouteId());
            if (route == null) {
                throw new RouteNotFoundException("Route not found for event ID: " + event.getId());
            }
            event.setRoute(route);
        }
        return event;
    }

    private SportCategory getSportCategoryById(Long sportCategoryId) {
        return sportCategoryRepository.findById(sportCategoryId)
                .orElseThrow(() -> new SportCategoryNotFoundException("Sport category not found: " + sportCategoryId));
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public List<Event> getEventsByCategoryName(String categoryName) {
        SportCategory sportCategory = sportCategoryRepository.findByName(categoryName)
                .orElseThrow(() -> new SportCategoryNotFoundException("Sport category not found: " + categoryName));

        List<Event> events = eventRepository.findBySportCategoryName(categoryName);

        events.forEach(event -> {
            event.setSportCategory(sportCategory);
            if (sportCategory.isRequiresRoute()) {
                event = handleRoute(event);
            } else {
                event = handleLocation(event);
            }
        });

        return events;
    }

    public List<Event> getEventsForToday() {
        List<Event> events = eventRepository.findEventsForToday();
        return enrichEventsWithDetails(events);
    }

    public List<Event> getEventsForTomorrow() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        List<Event> events = eventRepository.findEventsForTomorrow(tomorrow);
        return enrichEventsWithDetails(events);
    }

    public List<Event> getEventsThisWeek() {
        LocalDate oneWeekLater = LocalDate.now().plusDays(7);
        List<Event> events = eventRepository.findEventsForThisWeek(oneWeekLater);
        return enrichEventsWithDetails(events);
    }

    public List<Event> getEventsAfterThisWeek() {
        LocalDate nextMonday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        List<Event> events = eventRepository.findEventsAfterThisWeek(nextMonday);
        return enrichEventsWithDetails(events);
    }

    public List<Event> findEventsByPartOfDay(String partOfDay) {
        LocalTime startTime;
        LocalTime endTime;
        List<Event> events;

        switch (partOfDay.toLowerCase()) {
            case "morning":
                startTime = LocalTime.of(5, 0);
                endTime = LocalTime.of(11, 59);
                break;
            case "afternoon":
                startTime = LocalTime.of(12, 0);
                endTime = LocalTime.of(16, 59);
                break;
            case "evening":
                startTime = LocalTime.of(17, 0);
                endTime = LocalTime.of(20, 59);
                break;
            case "night":
                // Gestion spéciale pour deux plages horaires pour la nuit
                events = eventRepository.findByTimeRange(LocalTime.of(21, 0), LocalTime.of(23, 59));
                events.addAll(eventRepository.findByTimeRange(LocalTime.of(0, 0), LocalTime.of(4, 59)));
                return enrichEventsWithDetails(events);
            default:
                throw new IllegalArgumentException("Invalid part of day: " + partOfDay);
        }

        events = eventRepository.findByTimeRange(startTime, endTime);
        return enrichEventsWithDetails(events);
    }


    private List<Event> enrichEventsWithDetails(List<Event> events) {
        events.forEach(event -> {
            SportCategory sportCategory = getSportCategoryById(event.getSportCategory().getId());
            event.setSportCategory(sportCategory);
            if (sportCategory.isRequiresRoute()) {
                handleRoute(event);
            } else {
                handleLocation(event);
            }
        });
        return events;
    }

    public List<Event> getAllEventsWithDetails() {
        List<Event> events = eventRepository.findAll();
        return enrichEventsWithDetails(events);
    }
    public Event getEventWithDetails(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + eventId));

        SportCategory sportCategory = getSportCategoryById(event.getSportCategory().getId());
        event.setSportCategory(sportCategory);

        if (sportCategory.isRequiresRoute()) {
            event = handleRoute(event);
        } else {
            event = handleLocation(event);
        }

        return event;
    }
    public List<Event> getEventsByUserId(Long userId) {
        List<Event> event = eventRepository.findByOrganizerId(userId);
        return enrichEventsWithDetails(event);
    }
    public Event incrementParticipants(Long id) {
        Optional<Event> eventOpt = eventRepository.findById(id);
        if (eventOpt.isPresent()) {
            Event event = eventOpt.get();
            event.setCurrentNumParticipants(event.getCurrentNumParticipants() + 1);  // Increment the count
            return eventRepository.save(event);
        } else {
            throw new IllegalArgumentException("Event not found with id: " + id);
        }
    }
    public Event decrementParticipants(Long id) {
        Optional<Event> eventOpt = eventRepository.findById(id);
        if (eventOpt.isPresent()) {
            Event event = eventOpt.get();
            event.setCurrentNumParticipants(event.getCurrentNumParticipants() - 1);  // Increment the count
            return eventRepository.save(event);
        } else {
            throw new IllegalArgumentException("Event not found with id: " + id);
        }
    }

    private Point createUserLocation(double lon, double lat) {
        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate coordinate = new Coordinate(lon, lat);
        return geometryFactory.createPoint(coordinate);
    }
    private double parseRadius(String radius) {
        // Nettoyer la chaîne et supprimer les espaces inutiles
        radius = radius.trim().toLowerCase();

        try {
            // Vérifier si la chaîne contient "km"
            if (radius.endsWith("km")) {
                // Extraire la partie numérique et convertir de kilomètres à mètres
                return Double.parseDouble(radius.replace("km", "").trim()) * 1000;
            }

            // Si la chaîne est au format uniquement numérique, l'interpréter en mètres
            return Double.parseDouble(radius);
        } catch (NumberFormatException e) {
            // Lever une exception si le format est incorrect
            throw new IllegalArgumentException("Invalid radius format. Expected format: '150 km' or numeric value in meters.");
        }
    }

    public List<Event> getNearbyEvents(double latitude, double longitude, String radius) {
        // Convertir le rayon en une valeur numérique
        Double rad = parseRadius(radius);

        if (rad <= 0) {
            throw new IllegalArgumentException("Radius must be greater than zero.");
        }

        // Appeler le repository avec la distance numérique
        return eventRepository.findNearbyEvents(latitude, longitude, rad);
    }

}
