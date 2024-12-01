package org.ilisi.participation.clients;

import org.ilisi.participation.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "auth-service")
public interface UserServiceFeignClient {

    @GetMapping("/users/{id}")
    UserDTO getUserById(@PathVariable Long id);

    @GetMapping("/users")
    List<UserDTO> getUsersByIds(@RequestParam("ids") List<Long> ids);
}
