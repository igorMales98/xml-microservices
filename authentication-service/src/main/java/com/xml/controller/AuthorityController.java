package com.xml.controller;

import com.xml.dto.RegistrationRequestDto;
import com.xml.model.UserTokenState;
import com.xml.security.auth.JwtAuthenticationRequest;
import com.xml.service.AuthorityService;
import javassist.NotFoundException;
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


    @PostMapping(value = "/login")
    public ResponseEntity<UserTokenState> login(@RequestBody JwtAuthenticationRequest authenticationRequest) {
        try {
            UserTokenState userTokenState = authorityService.login(authenticationRequest);
            if (userTokenState == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(userTokenState, HttpStatus.OK);
        } catch (Exception e) {
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
