package org.ilisi.participation.clients;

import org.ilisi.participation.dto.EventDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "event-service")
public interface EventServiceFeignClient {

    @GetMapping("/events/{id}")
    EventDTO getEventById(@PathVariable Long id);

    @PutMapping("/events/{id}/increment")
    void incrementParticipants(@PathVariable Long id);
}
