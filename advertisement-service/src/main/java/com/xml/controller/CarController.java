package com.xml.controller;

import com.xml.dto.CarDto;
import com.xml.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@CrossOrigin(value = "https://localhost:4200")
@RequestMapping(value = "/api/cars", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarController {
    @Autowired
    private CarService carService;

    Logger logger = LoggerFactory.getLogger(CarController.class);

    @PutMapping(value = "")
    @PreAuthorize("hasAuthority('RATE_CAR')")
    public ResponseEntity<?> rate(@RequestBody CarDto carDto) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username : {} tried to rate a car.", LocalDateTime.now(), userDetails.getUsername());
            this.carService.rate(carDto);
        } catch(Exception e) {
            logger.error("Date : {}, There was an error rating car. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("Date: {}, Successfully rated car.", LocalDateTime.now());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
