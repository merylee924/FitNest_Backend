package org.ilisi.tracking_service.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "participation-service", url = "http://participation-service/api/participation")
public interface ParticipationClient {

    @GetMapping("/validate")
    boolean validateParticipation(@RequestParam Long participantId, @RequestParam Long eventId);
}
