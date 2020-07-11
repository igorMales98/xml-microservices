package com.xml.controller;

import com.xml.config.RabbitMQConfig;
import com.xml.dto.CarBrandDto;
import com.xml.mapper.CarBrandDtoMapper;
import com.xml.model.Email;
import com.xml.model.EmailBinding;
import com.xml.service.CarBrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
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
@RequestMapping(value = "/api/car-brands", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarBrandController {

    @Autowired
    private CarBrandService carBrandService;

    @Autowired
    private CarBrandDtoMapper carBrandDtoMapper;

    Logger logger = LoggerFactory.getLogger(CarBrandController.class);

    @GetMapping(value = "")
    @PreAuthorize("hasAuthority('READ_CAR_BRANDS')")
    public ResponseEntity<List<CarBrandDto>> getAll() {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to get all car brands.", LocalDateTime.now(), userDetails.getUsername());
            List<CarBrandDto> carBrandDtos = carBrandService.getAll().stream().map(carBrandDtoMapper::toDto).
                    collect(Collectors.toList());
            logger.info("Date : {}, Successfully returned list of all car brands.", LocalDateTime.now());
            return new ResponseEntity<>(carBrandDtos, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to get all car brands. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "")
    @PreAuthorize("hasAuthority('CREATE_CAR_BRANDS')")
    public ResponseEntity<?> addCarBrand(@Valid @RequestBody CarBrandDto carBrandDto) {
        System.out.println("Stampa: " + carBrandDto.getName());
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to add car brand.", LocalDateTime.now(), userDetails.getUsername());
            this.carBrandService.saveCarBrand(carBrandDto);
            logger.info("Date : {}, Successfully added car brand.", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to add car brand. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('EDIT_CAR_BRANDS')")
    public ResponseEntity<?> deleteCarBrand(@PathVariable Long id) throws ParseException {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to delete car brand.", LocalDateTime.now(), userDetails.getUsername());
            this.carBrandService.deleteCarBrand(id);
            logger.info("Date : {}, Successfully deleted car brand.", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to delete car brand. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "")
    @PreAuthorize("hasAuthority('EDIT_CAR_BRANDS')")
    public ResponseEntity<?> editCarBrand(@Valid @RequestBody CarBrandDto carBrandDto) {
        System.out.println(carBrandDto.toString());
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to edit car brand.", LocalDateTime.now(), userDetails.getUsername());
            this.carBrandService.editCarBrand(carBrandDto);
            logger.info("Date : {}, Successfully edited car brand.", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to edit car brand. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>("Car brand already exists.", HttpStatus.BAD_REQUEST);
        }
    }
}
