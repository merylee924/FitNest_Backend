package org.ilisi.participation.service;
import lombok.RequiredArgsConstructor;
import org.ilisi.participation.clients.EventFeignClient;
import org.ilisi.participation.clients.UserFeignClient;
import org.ilisi.participation.model.*;
import org.ilisi.participation.entities.*;
import org.ilisi.participation.repository.ParticipationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipationService {

    private final ParticipationRepository participationRepository;
    private final UserFeignClient userFeignClient;
    private final EventFeignClient eventFeignClient;

    @Transactional
    public Participation createParticipation(Long userId, Long eventId) {
        // Vérifier si la participation existe déjà
        if (participationRepository.existsByUserIdAndEventId(userId, eventId)) {
            throw new IllegalArgumentException("User already participates in this event.");
        }

        // Récupérer l'utilisateur et l'événement via Feign
        User user = userFeignClient.getUserById(userId);
        Event event = eventFeignClient.getEventById(eventId);

        if (event.getCurrentNumParticipants() >= event.getMaxParticipants()) {
            throw new IllegalStateException("Event is full.");
        }

        // Créer une participation
        Participation participation = Participation.builder()
                .userId(userId)
                .eventId(eventId)
                .date_participation(LocalDate.now())
                .status_participation(StatusParticipation.ACTIVE)
                .build();

        // Sauvegarder la participation
        participationRepository.save(participation);

        return participation;
    }

    public Participation getParticipationDetails(Long participationId) {
        Participation participation = participationRepository.findById(participationId)
                .orElseThrow(() -> new IllegalArgumentException("Participation not found."));

        // Charger les données transientes via Feign
        participation.setUser(userFeignClient.getUserById(participation.getUserId()));
        participation.setEvent(eventFeignClient.getEventById(participation.getEventId()));

        return participation;
    }

    @Transactional
    public void cancelParticipation(Long userId, Long eventId) {
        Participation participation = participationRepository
                .findByUserIdAndEventId(userId, eventId)
                .orElseThrow(() -> new IllegalArgumentException("Participation not found."));

        // Mettre à jour le statut de la participation à REFUSED au lieu de la supprimer
        participation.setStatus_participation(StatusParticipation.REFUSED);

        // Sauvegarder la participation avec le nouveau statut
        participationRepository.save(participation);

        eventFeignClient.decrementParticipants(eventId);
    }

    @Transactional
    public void acceptParticipation(Long userId, Long eventId) {
        Participation participation = participationRepository
                .findByUserIdAndEventId(userId, eventId)
                .orElseThrow(() -> new IllegalArgumentException("Participation not found."));

        // Mettre à jour le statut de la participation à REFUSED au lieu de la supprimer
        participation.setStatus_participation(StatusParticipation.ACCEPTED);

        // Sauvegarder la participation avec le nouveau statut
        participationRepository.save(participation);

        // Incrémenter le nombre de participants
        eventFeignClient.incrementParticipants(eventId);
    }

    public List<Event> getParticipantionEventsByUserId(Long userId) {
        List<Participation> participations = participationRepository.findByUserId(userId);
        List<Event> events = new ArrayList<>();

        for (Participation participation : participations) {
            Event event = eventFeignClient.getEventById(participation.getEventId());
            events.add(event);
        }
        return events;
    }
    public List<Participation> getParticipationsByEventId(Long eventId) {
        List<Participation> participations = participationRepository.findByEventId(eventId);

        for (Participation participation : participations) {
            User user = userFeignClient.getUserById(participation.getUserId());
            participation.setUser(user);

            Event event = eventFeignClient.getEventById(participation.getEventId());
            participation.setEvent(event);
        }

        return participations;
    }



}
