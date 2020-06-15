package com.xml.controller;

import com.xml.dto.RegistrationRequestDto;
import com.xml.model.UserTokenState;
import com.xml.security.auth.JwtAuthenticationRequest;
import com.xml.service.AuthorityService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "https://localhost:4200")
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorityController {

    @Autowired
    private AuthorityService authorityService;

    Logger logger = LoggerFactory.getLogger(AuthorityController.class);

    @PostMapping(value = "/login")
    public ResponseEntity<UserTokenState> login(@RequestBody JwtAuthenticationRequest authenticationRequest) {
        try {
            logger.info("E brate radi ovo, znaci usao sam hehic :) ");
            UserTokenState userTokenState = authorityService.login(authenticationRequest);
            if (userTokenState == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            logger.warn("stvatno ne znam sto ne pise u fajl");
            return new ResponseEntity<>(userTokenState, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("e ne radi jbg ");
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/verify/{token:.+}")
    public ResponseEntity<?> verify(@PathVariable("token") String token) throws NotFoundException {
        System.out.println(token);
        try {
            return new ResponseEntity<>(this.authorityService.verify(token), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(this.authorityService.verify(token), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping(value = "/getUser/{token:.+}")
    public ResponseEntity<Long> getLoggedInUserId(@PathVariable("token") String token) {
        System.out.println(token);
        try {
            Long userId = this.authorityService.getLoggedInUserId(token);
            return new ResponseEntity<>(userId, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequestDto registrationRequest) {
        try {
            this.authorityService.register(registrationRequest);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
