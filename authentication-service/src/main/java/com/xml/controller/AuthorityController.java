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

import java.time.LocalDate;
import java.time.LocalDateTime;

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
            logger.info("Date : {}, A user tried to login with username : {}.", LocalDateTime.now(), authenticationRequest.getUsername());
            UserTokenState userTokenState = authorityService.login(authenticationRequest);
            if (userTokenState == null) {
                logger.error("Date : {}, A user with username : {} does not exist or is blocked.", LocalDateTime.now(), authenticationRequest.getUsername());
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            logger.info("Date : {}, A user with username : {} has successfully logged in.", LocalDateTime.now(), authenticationRequest.getUsername());
            return new ResponseEntity<>(userTokenState, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.error("Date : {}, Unsuccessful log in. A user with username : {} does not exist.", LocalDate.now(), authenticationRequest.getUsername());
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/verify/{token:.+}")
    public ResponseEntity<?> verify(@PathVariable("token") String token) throws NotFoundException {
        System.out.println(token);
        try {
            logger.info("Date : {}, Checking if user with token exists from api gateway.", LocalDateTime.now());
            return new ResponseEntity<>(this.authorityService.verify(token), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, User is not authorized, token is invalid.", LocalDateTime.now());
            return new ResponseEntity<>(this.authorityService.verify(token), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping(value = "/getUser/{token:.+}")
    public ResponseEntity<Long> getLoggedInUserId(@PathVariable("token") String token) {
        System.out.println(token);
        try {
            logger.info("Date : {}, Returning user id after verifying it, from api gateway.", LocalDateTime.now());
            Long userId = this.authorityService.getLoggedInUserId(token);
            logger.info("Date : {}, Successfully returned user id from token.", LocalDateTime.now());
            return new ResponseEntity<>(userId, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, Unsuccessfully returned user id." +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequestDto registrationRequest) {
        try {
            logger.info("Date : {}, A new user has requested registration with username : {}.", LocalDateTime.now(),
                    registrationRequest.getUsername());
            this.authorityService.register(registrationRequest);
            logger.info("Date : {}, Registration request has been successfully saved.", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Date : {}, An error has occurred during registration. Request has not been saved." +
                    " Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
