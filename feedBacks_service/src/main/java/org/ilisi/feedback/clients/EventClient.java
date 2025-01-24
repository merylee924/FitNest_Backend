package org.ilisi.feedback.clients;


import org.ilisi.feedback.dto.EventEndedDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "event-service")
public interface EventClient {

    @GetMapping("api/events/completed")
    List<EventEndedDTO> getCompletedEvents();
}

