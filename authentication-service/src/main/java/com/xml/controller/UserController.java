package com.xml.controller;

import com.xml.dto.UserDto;
import com.xml.mapper.UserDtoMapper;
import com.xml.model.User;
import com.xml.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(value = "https://localhost:4200")
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
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
    public ResponseEntity<?> agentRegister(@RequestBody UserDto userDto){
        try {
            this.userService.registerAgent(userDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
