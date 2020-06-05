package com.xml.controller;

import com.netflix.discovery.converters.Auto;
import com.xml.dto.UserDto;
import com.xml.mapper.UserDtoMapper;
import com.xml.model.User;
import com.xml.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin(value = "https://localhost:4200")
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDtoMapper userDtoMapper;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(this.userDtoMapper.toDto(this.userService.findById(id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/whoami", method = RequestMethod.GET)
    public User user(Principal user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user1 = (User) auth.getPrincipal();
        return this.userService.findByUsername(user1.getUsername());
    }

    @PutMapping(value = "/updateTimesRated/{id}")
    public ResponseEntity<?> updateTimesPosted(@PathVariable("id") Long id) {
        try {
            this.userService.updateTimesRated(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/physical")
    public ResponseEntity<?> createPhysicalUser(@RequestBody UserDto userDto, @RequestHeader("Authorization") String token) {
        try {
            Long newUserId = this.userService.createPhysicalUser(userDto, token);
            System.out.println("Id novi je" + newUserId);
            return new ResponseEntity<>(newUserId, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
