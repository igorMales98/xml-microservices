package com.xml.controller;

import com.xml.dto.RentRequestDto;
import com.xml.model.RentRequest;
import com.xml.service.RentRequestService;
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
@RequestMapping(value = "/api/rent-requests", produces = MediaType.APPLICATION_JSON_VALUE)
public class RentRequestController {

    @Autowired
    private RentRequestService rentRequestService;

    Logger logger = LoggerFactory.getLogger(RentRequestController.class);

    @PostMapping(value = "")
    @PreAuthorize("hasAuthority('CREATE_RENT_REQUESTS')")
    public ResponseEntity<?> createRentRequest(@Valid @RequestBody RentRequestDto rentRequestDto,
                                               @RequestHeader("Authorization") String token) {
        System.out.println(rentRequestDto.toString());
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to create rent request.", LocalDateTime.now(), userDetails.getUsername());
            this.rentRequestService.createRentRequest(rentRequestDto, token);
            logger.info("Date : {}, Successfully created rent request.", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to create rent request. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/allPaid")
    @PreAuthorize("hasAuthority('READ_RENT_REQUESTS')")
    public ResponseEntity<List<RentRequestDto>> getPaidRentRequests(@RequestHeader("Authorization") String token) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to get paid rent request.", LocalDateTime.now(), userDetails.getUsername());
            List<RentRequestDto> rentRequestDtos = this.rentRequestService.getPaidRentRequests(token);
            logger.info("Date : {}, Successfully returned list of paid rent requests.", LocalDateTime.now());
            return new ResponseEntity<>(rentRequestDtos, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to get paid rent request. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/customer/{id}")
    @PreAuthorize("hasAuthority('READ_RENT_REQUESTS')")
    public ResponseEntity<List<RentRequestDto>> getCustomerRentRequests(@RequestHeader("Authorization") String token,
                                                                        @PathVariable("id") Long id) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to get customer for rent request.", LocalDateTime.now(), userDetails.getUsername());
            List<RentRequestDto> rentRequestDtos = this.rentRequestService.getCustomerRentRequests(token, id);
            logger.info("Date : {}, Successfully returned list of customer rent requests.", LocalDateTime.now());
            return new ResponseEntity<>(rentRequestDtos, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to get customer rent request. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('READ_RENT_REQUESTS')")
    public ResponseEntity<?> getPeople(@PathVariable("id") Long id, @RequestHeader("Authorization") String token) {
        List<Long> retVal = this.rentRequestService.getPeople(id, token);
        logger.info("Date : {}, Successfully returned list of available people for communication.", LocalDateTime.now());
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @GetMapping(value = "/all/{id}")
    @PreAuthorize("hasAuthority('READ_RENT_REQUESTS')")
    public ResponseEntity<?> getAdvertiserRequests(@PathVariable("id") Long id, @RequestHeader("Authorization") String token) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to get advertiser of rent request.", LocalDateTime.now(), userDetails.getUsername());
            List<RentRequestDto> rentRequestDtos = this.rentRequestService.getUserRentRequests(id, token);
            logger.info("Date : {}, Successfully returned list of advertisers rent requests.", LocalDateTime.now());
            return new ResponseEntity<>(rentRequestDtos, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to get advertiser of rent request. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('EDIT_RENT_REQUESTS')")
    public ResponseEntity<?> cancelRentRequest(@PathVariable("id") Long id) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to cancel rent request.", LocalDateTime.now(), userDetails.getUsername());
            this.rentRequestService.cancelRentRequest(id);
            logger.info("Date : {}, Successfully canceled rent requests.", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to cancel rent request. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('EDIT_RENT_REQUESTS')")
    public ResponseEntity<?> acceptRentRequest(@PathVariable("id") Long id) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to accept rent request.", LocalDateTime.now(), userDetails.getUsername());
            this.rentRequestService.acceptRentRequest(id);
            logger.info("Date : {}, Successfully accepted rent requests.", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to accept rent request. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/customer/pending/{id}")
    public ResponseEntity<List<RentRequestDto>> getCustomerPendingRentRequests(@RequestHeader("Authorization") String token,
                                                                               @PathVariable("id") Long id) {
        try {
            List<RentRequestDto> rentRequestDtos = this.rentRequestService.getCustomerPendingRentRequests(token, id);
            return new ResponseEntity<>(rentRequestDtos, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/timesRented/{advertisementId}")
    public ResponseEntity<?> timesRented(@PathVariable("advertisementId") Long advertisementId) {
        try {
            Integer timesRented = this.rentRequestService.getTimesRented(advertisementId);
            return new ResponseEntity<>(timesRented, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/rentMileage/{advertisementId}")
    public ResponseEntity<?> rentMileage(@PathVariable("advertisementId") Long advertisementId) {
        try {
            float mileage = this.rentRequestService.getRentMileage(advertisementId);
            return new ResponseEntity<>(mileage, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
