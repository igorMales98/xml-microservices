package com.xml.feignClients;

import com.xml.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "authentication-service")
public interface UserFeignClient {

    @GetMapping(value = "/api/users/{id}", headers = {"Authorization={token}"})
    UserDto getUserById(@PathVariable("id") Long id, @PathVariable("token") String token);

    @PutMapping(value = "/api/users/updateTimesPosted/{id}", headers = {"Authorization={token}"})
    void updateTimesPosted(@PathVariable("id") Long id, @PathVariable("token") String token);
}
