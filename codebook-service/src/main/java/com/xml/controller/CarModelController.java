package com.xml.controller;

import com.xml.dto.CarClassDto;
import com.xml.dto.CarModelDto;
import com.xml.mapper.CarModelDtoMapper;
import com.xml.model.CarModel;
import com.xml.service.CarModelService;
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
@RequestMapping(value = "/api/car-models", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarModelController {

    @Autowired
    private CarModelService carModelService;

    @Autowired
    private CarModelDtoMapper carModelDtoMapper;

    Logger logger = LoggerFactory.getLogger(CarModelController.class);


    @GetMapping(value = "")
    @PreAuthorize("hasAuthority('READ_CAR_MODELS')")
    public ResponseEntity<List<CarModelDto>> getAll() {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to get all car models.", LocalDateTime.now(), userDetails.getUsername());
            List<CarModelDto> carModelDtos = this.carModelService.getAll().stream().map(carModelDtoMapper::toDto)
                    .collect(Collectors.toList());
            logger.info("Date : {}, Successfully returned list of all car models.", LocalDateTime.now());
            return new ResponseEntity<>(carModelDtos, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to get all car models. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{brandId}")
    @PreAuthorize("hasAuthority('READ_CAR_MODELS')")
    public ResponseEntity<List<CarModelDto>> getBrandModels(@PathVariable("brandId") Long brandId) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to get all car brands model.", LocalDateTime.now(), userDetails.getUsername());
            List<CarModelDto> carModelDtos = this.carModelService.getBrandModels(brandId).stream()
                    .map(carModelDtoMapper::toDto).collect(Collectors.toList());
            logger.info("Date : {}, Successfully returned list of all car brands model.", LocalDateTime.now());
            return new ResponseEntity<>(carModelDtos, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to get car brand model. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "")
    @PreAuthorize("hasAuthority('CREATE_CAR_MODELS')")
    public ResponseEntity<?> addCarModel(@Valid @RequestBody CarModelDto carModelDto) {
        System.out.println("Stampa: " + carModelDto.toString());
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to add car model.", LocalDateTime.now(), userDetails.getUsername());
            this.carModelService.saveCarModel(carModelDto);
            logger.info("Date : {}, Successfully added car class.", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to add car model. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('EDIT_CAR_MODELS')")
    public ResponseEntity<?> deleteCarModel(@PathVariable Long id) throws ParseException {

        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to delete car model.", LocalDateTime.now(), userDetails.getUsername());
            this.carModelService.deleteCarModel(id);
            logger.info("Date : {}, Successfully deleted car model.", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to delete car model. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "")
    @PreAuthorize("hasAuthority('EDIT_CAR_MODELS')")
    public ResponseEntity<?> editCarModel(@Valid @RequestBody CarModelDto carModelDto) {
        System.out.println(carModelDto.toString());
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to edit car model.", LocalDateTime.now(), userDetails.getUsername());
            this.carModelService.editCarModel(carModelDto);
            logger.info("Date : {}, Successfully edited car model.", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to edit car model. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>("Car model already exists.", HttpStatus.BAD_REQUEST);
        }
    }
}
