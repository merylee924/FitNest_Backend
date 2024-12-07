package org.ilisi.tracking_service.clients;
import org.ilisi.tracking_service.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service")
public interface UserFeignClient {

    @GetMapping("/user/getUserById/{userId}")
    User getUserById(@PathVariable("userId") Long userId);

}