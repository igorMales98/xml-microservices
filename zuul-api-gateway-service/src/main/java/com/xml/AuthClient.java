package com.xml;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="authentication-service")
public interface AuthClient {

    @GetMapping(value = "/verify/{token}")
    public ResponseEntity<?> verify(@PathVariable("token") String token);
}
