package com.xml.controller;

import com.xml.dto.CoordinatesDto;
import com.xml.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RestController
@CrossOrigin(value = "https://localhost:4200")
@RequestMapping(value = "/api/locations", produces = MediaType.APPLICATION_JSON_VALUE)
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping(value = "/{androidToken}")
    public ResponseEntity<?> getLocation(@PathVariable("androidToken") String androidToken) {
        try {
            CoordinatesDto coordinatesDto = this.locationService.getLocation(androidToken);
            return new ResponseEntity<>(coordinatesDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(value = "")
    public ResponseEntity<?> resetSeconds() {
        try {
            this.locationService.resetSeconds();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
