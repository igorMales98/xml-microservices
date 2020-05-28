package com.xml;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

@FeignClient(name = "authentication-service")
public interface AuthClient {

    @GetMapping(value = "/api/auth/verify/{token}")
    Collection<Permission> verify(@PathVariable("token") String token);
}
