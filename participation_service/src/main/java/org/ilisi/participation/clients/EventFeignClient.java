package org.ilisi.participation.clients;
import org.ilisi.participation.model.Event;
import org.ilisi.participation.dto.EventDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "event-service")
public interface EventFeignClient {
    @GetMapping("/api/events/{id}/details")
    Event getEventById(@PathVariable("id") Long id);

    @PutMapping("/api/events/{id}/increment-participants")
    void incrementParticipants(@PathVariable("id") Long id);

    @PutMapping("/api/events/{id}/decrement-participants")
    void decrementParticipants(@PathVariable("id") Long id);
}
