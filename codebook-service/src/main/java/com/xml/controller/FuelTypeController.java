package com.xml.controller;

import com.xml.dto.FuelTypeDto;
import com.xml.mapper.FuelTypeDtoMapper;
import com.xml.service.FuelTypeService;
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

import javax.validation.Valid;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(value = "https://localhost:4200")
@RequestMapping(value = "/api/fuel-types", produces = MediaType.APPLICATION_JSON_VALUE)
public class FuelTypeController {

    @Autowired
    private FuelTypeService fuelTypeService;

    @Autowired
    private FuelTypeDtoMapper fuelTypeDtoMapper;

    Logger logger = LoggerFactory.getLogger(FuelTypeController.class);


    @GetMapping(value = "")
    @PreAuthorize("hasAuthority('READ_FUEL_TYPES')")
    public ResponseEntity<List<FuelTypeDto>> getAll() {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to get all fuel types.", LocalDateTime.now(), userDetails.getUsername());
            List<FuelTypeDto> fuelTypeDtos = this.fuelTypeService.getAll().stream()
                    .map(fuelTypeDtoMapper::toDto).collect(Collectors.toList());
            logger.info("Date : {}, Successfully returned list of all fuel types.", LocalDateTime.now());
            return new ResponseEntity<>(fuelTypeDtos, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to get all fuel types. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "")
    @PreAuthorize("hasAuthority('CREATE_FUEL_TYPES')")
    public ResponseEntity<?> addFuelType(@Valid @RequestBody FuelTypeDto fuelTypeDto) {
        System.out.println("Stampa: " + fuelTypeDto.getName());
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to add fuel type.", LocalDateTime.now(), userDetails.getUsername());
            this.fuelTypeService.saveFuelType(fuelTypeDto);
            logger.info("Date : {}, Successfully added fuel type.", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to add fuel type. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('EDIT_FUEL_TYPES')")
    public ResponseEntity<?> deleteFuelType(@PathVariable Long id) throws ParseException {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to delete fuel type.", LocalDateTime.now(), userDetails.getUsername());
            this.fuelTypeService.deleteFuelType(id);
            logger.info("Date : {}, Successfully deleted fuel type.", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to delete fuel type. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "")
    @PreAuthorize("hasAuthority('EDIT_FUEL_TYPES')")
    public ResponseEntity<?> editFuelType(@Valid @RequestBody FuelTypeDto fuelTypeDto) {
        System.out.println(fuelTypeDto.toString());
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to edit fuel type.", LocalDateTime.now(), userDetails.getUsername());
            this.fuelTypeService.editFuelType(fuelTypeDto);
            logger.info("Date : {}, Successfully edited fuel type.", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to edit fuel type. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>("Fuel type already exists.", HttpStatus.BAD_REQUEST);
        }
    }
}
