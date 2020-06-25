package com.xml.controller;

import com.xml.dto.RegistrationRequestDto;
import com.xml.model.Customer;
import com.xml.service.RegistrationRequestService;
import com.xml.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(value = "https://localhost:4200")
@RequestMapping(value = "/api/registration-requests", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistrationRequestController {

    @Autowired
    private RegistrationRequestService registrationRequestService;

    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(RegistrationRequestController.class);

    @GetMapping(value = "")
    @PreAuthorize("hasAuthority('READ_REGISTRATION_REQUESTS')")
    public ResponseEntity<List<RegistrationRequestDto>> getAll() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Date : {}, A user with username : {} has requested all registration requests.", LocalDateTime.now(),
                userDetails.getUsername());
        try {
            List<RegistrationRequestDto> requestDtos = this.registrationRequestService.getAll();
            logger.info("Date : {}, Successfully returned list of all registration requests.", LocalDateTime.now());
            return new ResponseEntity<>(requestDtos, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, Error while returning list of all registration requests. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('DELETE_REGISTRATION_REQUESTS')")
    public ResponseEntity<?> deleteRegistrationRequest(@PathVariable Long id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Date : {}, A user with username : {} has tried deleting registration request with id {}.", LocalDateTime.now(),
                userDetails.getUsername(), id);
        try {
            this.registrationRequestService.deleteRegistrationRequest(id);
            logger.info("Date : {}, Successfully deleted registration request.", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, Error while deleting registration request. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "")
    @PreAuthorize("hasAuthority('CONFIRM_REGISTRATION_REQUESTS')")
    public ResponseEntity<?> confirmRegistrationRequest(@Valid @RequestBody RegistrationRequestDto requestDto) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Date : {}, A user with username : {} has accepted registration request.", LocalDateTime.now(),
                userDetails.getUsername());
        try {
            Customer newCustomer = this.userService.createCustomerFromRequest(requestDto);
            this.registrationRequestService.deleteRegistrationRequest(requestDto.getId());
            logger.info("Date : {}, Registration request has been deleted.", LocalDateTime.now());
            this.userService.saveCustomer(newCustomer);
            logger.info("Date : {}, A customer has been created.", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Date : {}, Error while accepting registration request. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
