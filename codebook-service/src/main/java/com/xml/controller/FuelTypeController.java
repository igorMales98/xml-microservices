package com.xml.controller;

import com.xml.dto.FuelTypeDto;
import com.xml.mapper.FuelTypeDtoMapper;
import com.xml.service.FuelTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(value = "https://localhost:4200")
@RequestMapping(value = "/api/fuel-type", produces = MediaType.APPLICATION_JSON_VALUE)
public class FuelTypeController {

    @Autowired
    private FuelTypeService fuelTypeService;

    @Autowired
    private FuelTypeDtoMapper fuelTypeDtoMapper;

    @GetMapping(value = "/all")
    public ResponseEntity<List<FuelTypeDto>> getAll() {
        try {
            List<FuelTypeDto> fuelTypeDtos = this.fuelTypeService.getAll().stream()
                    .map(fuelTypeDtoMapper::toDto).collect(Collectors.toList());
            return new ResponseEntity<>(fuelTypeDtos, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
