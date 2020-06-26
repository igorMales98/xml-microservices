package com.xml.controller;

import com.xml.dto.CarClassDto;
import com.xml.mapper.CarClassDtoMapper;
import com.xml.model.CarClass;
import com.xml.service.CarClassService;
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
@RequestMapping(value = "/api/car-classes", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarClassController {

    @Autowired
    private CarClassService carClassService;

    @Autowired
    private CarClassDtoMapper carClassDtoMapper;

    Logger logger = LoggerFactory.getLogger(CarClassController.class);


    @GetMapping(value = "")
    @PreAuthorize("hasAuthority('READ_CAR_CLASSES')")
    public ResponseEntity<List<CarClassDto>> getAll() {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to get all car classes.", LocalDateTime.now(), userDetails.getUsername());
            List<CarClassDto> carClassDtos = this.carClassService.getAll().stream()
                    .map(carClassDtoMapper::toDto).collect(Collectors.toList());
            logger.info("Date : {}, Successfully returned list of all car classes.", LocalDateTime.now());
            return new ResponseEntity<>(carClassDtos, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to get all car brands. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "")
    @PreAuthorize("hasAuthority('CREATE_CAR_CLASSES')")
    public ResponseEntity<?> addCarClass(@Valid @RequestBody CarClassDto carClassDto) {
        System.out.println("Stampa: " + carClassDto.getName());
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to add car class.", LocalDateTime.now(), userDetails.getUsername());
            this.carClassService.saveCarClass(carClassDto);
            logger.info("Date : {}, Successfully added car class.", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to add car class. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('EDIT_CAR_CLASSES')")
    public ResponseEntity<?> deleteCarClass(@PathVariable Long id) throws ParseException {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to delete car class.", LocalDateTime.now(), userDetails.getUsername());
            this.carClassService.deleteCarClass(id);
            logger.info("Date : {}, Successfully deleted car class.", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to delete car class. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "")
    @PreAuthorize("hasAuthority('EDIT_CAR_CLASSES')")
    public ResponseEntity<?> editCarClass(@Valid @RequestBody CarClassDto carClassDto) {
        System.out.println(carClassDto.toString());
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to edit car class.", LocalDateTime.now(), userDetails.getUsername());
            this.carClassService.editCarClass(carClassDto);
            logger.info("Date : {}, Successfully edited car class.", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to edit car class. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>("Car class already exists.", HttpStatus.BAD_REQUEST);
        }
    }
}
