package com.xml.controller;

import com.xml.dto.UserDto;
import com.xml.mapper.UserDtoMapper;
import com.xml.model.User;
import com.xml.security.TokenUtils;
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
import sun.security.validator.ValidatorException;

import javax.xml.bind.ValidationException;
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

    @Autowired
    private TokenUtils tokenUtils;

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
            e.printStackTrace();
            logger.error("Date : {}, There was an error finding a requested user. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/whoami", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('WHO_AM_I')")
    public User user(Principal user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user1 = (User) auth.getPrincipal();
        logger.info("Date : {}, A user : {} has requested information about himself. ", LocalDateTime.now(), user1.getUsername());
        return this.userService.findByUsername(user1.getUsername());
    }

    @PutMapping(value = "/updateTimesPosted/{id}")
    @PreAuthorize("hasAuthority('UPDATE_POSTS')")
    public ResponseEntity<?> updateTimesPosted(@PathVariable("id") Long id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Date : {}, A user with username : {} has posted an advertisement and require update on times posted.", LocalDateTime.now(),
                userDetails.getUsername());
        try {
            this.userService.updateTimesPosted(id);
            logger.info("Date : {}, A user : {} has successfully updated number of times posted.", LocalDateTime.now(),
                    userDetails.getUsername());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Date : {}, There was an error updating number of times rated. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/physical")
    @PreAuthorize("hasAuthority('CREATE_PHYSICAL_USER')")
    public ResponseEntity<?> createPhysicalUser(@RequestBody UserDto userDto, @RequestHeader("Authorization") String token) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Date : {}, A user : {} has created physical rent and want to save information on physical customer.", LocalDateTime.now(),
                userDetails.getUsername());
        try {
            Long newUserId = this.userService.createPhysicalUser(userDto, token);
            logger.info("Date : {}, A user : {} has created physical rent and successfully saved new physical user.", LocalDateTime.now(),
                    userDetails.getUsername());
            System.out.println("Id novi je" + newUserId);
            return new ResponseEntity<>(newUserId, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error saving new physical user. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/customers")
    @PreAuthorize("hasAuthority('READ_CUSTOMERS')")
    public ResponseEntity<List<UserDto>> getAllCustomers() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Date : {}, A user : {} has requested a list of all customers.", LocalDateTime.now(),
                userDetails.getUsername());
        try {
            List<UserDto> userDtos = this.userService.getAllCustomers();
            logger.info("Date : {}, A user : {} has successfully retrieved a list of all customers.", LocalDateTime.now(),
                    userDetails.getUsername());
            return new ResponseEntity<>(userDtos, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error while retrieving a list of all customers. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('EDIT_CUSTOMERS')")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Date : {}, A user : {} has requested a deletion of a customer with id {}.", LocalDateTime.now(),
                userDetails.getUsername(), id);
        try {
            this.userService.deleteCustomer(id);
            logger.info("Date : {}, A user : {} has successfully deleted a customer with id {}.", LocalDateTime.now(),
                    userDetails.getUsername(), id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error while deleting a customer with id {}. " +
                    "Error : {}.", LocalDateTime.now(), id, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/block/{id}")
    @PreAuthorize("hasAuthority('EDIT_CUSTOMERS')")
    public ResponseEntity<?> blockCustomer(@PathVariable Long id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Date : {}, A user : {} has requested a blocking of a customer with id {}.", LocalDateTime.now(),
                userDetails.getUsername(), id);
        try {
            this.userService.blockUser(id);
            logger.info("Date : {}, A user : {} has successfully blocked a customer with id {}.", LocalDateTime.now(),
                    userDetails.getUsername(), id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error while deleting a customer with id {}. " +
                    "Error : {}.", LocalDateTime.now(), id, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/activate/{id}")
    @PreAuthorize("hasAuthority('EDIT_CUSTOMERS')")
    public ResponseEntity<?> activateCustomer(@PathVariable Long id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Date : {}, A user : {} has requested an activation of a customer with id {}.", LocalDateTime.now(),
                userDetails.getUsername(), id);
        try {
            this.userService.activateUser(id);
            logger.info("Date : {}, A user : {} has successfully activated a customer with id {}.", LocalDateTime.now(),
                    userDetails.getUsername(), id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error while activating a customer with id {}. " +
                    "Error : {}.", LocalDateTime.now(), id, e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/activateUser/{token:.+}")
    @PreAuthorize("hasAuthority('EDIT_CUSTOMERS')")
    public ResponseEntity<?> activateUser(@PathVariable("token") String token) {
        String username = this.tokenUtils.getUsernameFromToken(token);
        try {
            this.userService.activateUserEmail(username);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/agent")
    @PreAuthorize("hasAuthority('CREATE_AGENTS')")
    public ResponseEntity<?> agentRegister(@RequestBody UserDto userDto) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Date : {}, A user : {} has requested a creation of an agent.", LocalDateTime.now(),
                userDetails.getUsername());
        try {
            this.userService.registerAgent(userDto);
            logger.info("Date : {}, A user : {} has successfully created an agent.", LocalDateTime.now(),
                    userDetails.getUsername());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error while creating an agent. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/checkPassword")
    public ResponseEntity<Boolean> checkPassword(@RequestBody String password) {
        System.out.println("sifra" + password);
        password = password.replace("\"", "");
        password = password.replace("{", "");
        password = password.replace("}", "");
        password = password.split(":")[1];
        System.out.println("sad je sifra" + password);

        try {
            boolean valid = this.userService.checkPassword(password);
            return new ResponseEntity<>(valid, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody String password) {
        System.out.println("sifra za menjanje je" + password);
        password = password.replace("\"", "");
        password = password.replace("{", "");
        password = password.replace("}", "");
        password = password.split(":")[1];
        System.out.println("sad je sifra" + password);
        try {
            this.userService.changePassword(password);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ve) {
            return new ResponseEntity<>("Password is not valid.", HttpStatus.BAD_REQUEST);
        }
    }

}
