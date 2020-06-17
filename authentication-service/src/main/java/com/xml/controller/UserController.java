package com.xml.controller;

import com.xml.dto.UserDto;
import com.xml.mapper.UserDtoMapper;
import com.xml.model.User;
import com.xml.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(value = "https://localhost:4200")
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDtoMapper userDtoMapper;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('READ_USER')")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Date : {}, A user with username : {} has requested information about a user.", LocalDateTime.now(),
                userDetails.getUsername());
        try {
            UserDto userDto = this.userDtoMapper.toDto(this.userService.findById(id));
            logger.info("Date : {}, Requested user was : {}.", LocalDateTime.now(), userDto.getUsername());
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error finding a requested user.", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/whoami", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('WHO_AM_I')")
    public User user(Principal user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user1 = (User) auth.getPrincipal();
        logger.info("Date : {}, A user : {} has requested information about himself.", LocalDateTime.now(), user1.getUsername());
        return this.userService.findByUsername(user1.getUsername());
    }

    @PutMapping(value = "/updateTimesPosted/{id}")
    public ResponseEntity<?> updateTimesPosted(@PathVariable("id") Long id) {
        try {
            this.userService.updateTimesPosted(id);
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

    @GetMapping(value = "/customers")
    public ResponseEntity<List<UserDto>> getAllCustomers() {
        try {
            List<UserDto> userDtos = this.userService.getAllCustomers();
            return new ResponseEntity<>(userDtos, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        try {
            this.userService.deleteCustomer(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/block/{id}")
    public ResponseEntity<?> blockCustomer(@PathVariable Long id) {
        try {
            this.userService.blockUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/activate/{id}")
    public ResponseEntity<?> activateCustomer(@PathVariable Long id) {
        try {
            this.userService.activateUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/agent")
    public ResponseEntity<?> agentRegister(@RequestBody UserDto userDto) {
        try {
            this.userService.registerAgent(userDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
