package org.ilisi.participation.clients;
import org.ilisi.participation.model.User;
import org.ilisi.participation.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "auth-service")
public interface UserFeignClient {
    @GetMapping("/user/getUserById/{userId}")
    User getUserById(@PathVariable("userId") Long userId);

}