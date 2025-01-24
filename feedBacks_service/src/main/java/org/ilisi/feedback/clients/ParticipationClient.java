package org.ilisi.feedback.clients;


import org.ilisi.feedback.dto.EventEndedDTO;
import org.ilisi.feedback.dto.ParticipationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "participation-service")
public interface ParticipationClient {

    @GetMapping("/api/participations/eventEnded/{eventId}")
    List<ParticipationDTO> getParticipationsByEventEndedId(@RequestParam("eventId") Long eventId);

    @GetMapping("/api/participations/user/{userId}/event/{eventId}")
    Long getParticipationIdByUserAndEvent(@PathVariable("userId") Long userId, @PathVariable("eventId") Long eventId);

  /*  @GetMapping("/api/participations/eventEndedDetails/{eventId}")
    EventEndedDTO getEventByParticipationId(Long id);

    // Nouvelle méthode pour récupérer les participations par organisateur

    @GetMapping("/api/participations/organizer/{organizerId}")
    List<ParticipationDTO> getParticipationsByOrganizerId(@PathVariable("organizerId") Long organizerId);*/
}
