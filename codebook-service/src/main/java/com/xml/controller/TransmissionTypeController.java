package com.xml.controller;

import com.xml.dto.FuelTypeDto;
import com.xml.dto.TransmissionTypeDto;
import com.xml.mapper.TransmissionTypeDtoMapper;
import com.xml.service.TransmissionTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(value = "https://localhost:4200")
@RequestMapping(value = "/api/transmission-types", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransmissionTypeController {

    @Autowired
    private TransmissionTypeService transmissionTypeService;

    @Autowired
    private TransmissionTypeDtoMapper transmissionTypeDtoMapper;

    Logger logger = LoggerFactory.getLogger(TransmissionTypeController.class);

    @GetMapping(value = "")
    public ResponseEntity<List<TransmissionTypeDto>> getAll() {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to get all transmission types.", LocalDateTime.now(), userDetails.getUsername());
            List<TransmissionTypeDto> transmissionTypeDtos = this.transmissionTypeService.getAll().stream()
                    .map(transmissionTypeDtoMapper::toDto).collect(Collectors.toList());
            logger.info("Date : {}, Successfully returned list of all transmission types.", LocalDateTime.now());
            return new ResponseEntity<>(transmissionTypeDtos, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to get all transmission types. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> addTransmissionType(@Valid @RequestBody TransmissionTypeDto transmissionTypeDto) {
        System.out.println("Stampa: " + transmissionTypeDto.getName());
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to add transmission type.", LocalDateTime.now(), userDetails.getUsername());
            this.transmissionTypeService.saveTransmissionType(transmissionTypeDto);
            logger.info("Date : {}, Successfully added transmission type.", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to add transmission type. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteTransmissionType(@PathVariable Long id) throws ParseException {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to delete transmission type.", LocalDateTime.now(), userDetails.getUsername());
            this.transmissionTypeService.deleteTransmissionType(id);
            logger.info("Date : {}, Successfully deleted transmission type.", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to delete transmission type. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "")
    public ResponseEntity<?> editTransmissionType(@Valid @RequestBody TransmissionTypeDto transmissionTypeDto) {
        System.out.println(transmissionTypeDto.toString());
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to edit transmission type.", LocalDateTime.now(), userDetails.getUsername());
            this.transmissionTypeService.editTransmissionType(transmissionTypeDto);
            logger.info("Date : {}, Successfully edited transmission type.", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to edit transmission type. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>("Transmission type already exists.", HttpStatus.BAD_REQUEST);
        }
    }
}
