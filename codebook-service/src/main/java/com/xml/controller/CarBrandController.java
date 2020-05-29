package com.xml.controller;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(value = "https://localhost:4200")
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarBrandController {

    @GetMapping(value = "/test")
    @PreAuthorize("hasAuthority('TEST')")
    public String test() {
        System.out.println("iz codebooka " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return "Hello svet";
    }
}
