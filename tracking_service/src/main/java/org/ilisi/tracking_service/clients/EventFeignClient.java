package org.ilisi.tracking_service.clients;

import org.ilisi.tracking_service.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "event-service")
public interface EventFeignClient {
    @GetMapping("/api/events/{id}/details")
    Event getEventById(@PathVariable("id") Long id);

}
