package com.xml.controller;

import com.xml.dto.CarModelDto;
import com.xml.dto.PricelistDto;
import com.xml.mapper.PricelistDtoMapper;
import com.xml.service.PricelistService;
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
@RequestMapping(value = "/api/pricelists", produces = MediaType.APPLICATION_JSON_VALUE)
public class PricelistController {

    @Autowired
    private PricelistService pricelistService;

    @Autowired
    private PricelistDtoMapper pricelistDtoMapper;

    Logger logger = LoggerFactory.getLogger(PricelistController.class);

    @GetMapping(value = "")
    @PreAuthorize("hasAuthority('READ_PRICELISTS')")
    public ResponseEntity<List<PricelistDto>> getAll() {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to get all pricelists.", LocalDateTime.now(), userDetails.getUsername());
            List<PricelistDto> pricelistDtos = this.pricelistService.getAll().stream()
                    .map(pricelistDtoMapper::toDto).collect(Collectors.toList());
            logger.info("Date : {}, Successfully returned list of all pricelists.", LocalDateTime.now());
            return new ResponseEntity<>(pricelistDtos, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to get all pricelists. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(value = "")
    public ResponseEntity<?> addPricelist(@Valid @RequestBody PricelistDto pricelistDto) {
        System.out.println("Stampa: " + pricelistDto.toString());
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to add pricelist.", LocalDateTime.now(), userDetails.getUsername());
            this.pricelistService.savePricelist(pricelistDto);
            logger.info("Date : {}, Successfully added pricelist.", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error when adding pricelist. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletePricelist(@PathVariable Long id) throws ParseException {

        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to delete pricelist.", LocalDateTime.now(), userDetails.getUsername());
            this.pricelistService.deletePricelist(id);
            logger.info("Date : {}, Successfully deleted pricelist.", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error when deleting pricelist. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "")
    public ResponseEntity<?> editPricelist(@Valid @RequestBody PricelistDto pricelistDto) {
        System.out.println(pricelistDto.toString());
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to edit pricelist.", LocalDateTime.now(), userDetails.getUsername());
            this.pricelistService.editPricelist(pricelistDto);
            logger.info("Date : {}, Successfully edited priccelist.", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error when editing pricelist. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>("Pricelist already exists.", HttpStatus.BAD_REQUEST);
        }
    }
}
