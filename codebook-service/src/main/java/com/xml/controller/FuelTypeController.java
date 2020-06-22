package com.xml.controller;

import com.xml.dto.FuelTypeDto;
import com.xml.mapper.FuelTypeDtoMapper;
import com.xml.service.FuelTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
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

    @GetMapping(value = "")
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

    @PostMapping(value = "")
    public ResponseEntity<?> addFuelType(@Valid @RequestBody FuelTypeDto fuelTypeDto) {
        System.out.println("Stampa: " + fuelTypeDto.getName());
        try {
            this.fuelTypeService.saveFuelType(fuelTypeDto);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteFuelType(@PathVariable Long id) throws ParseException {

        try {
            this.fuelTypeService.deleteFuelType(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "")
    public ResponseEntity<?> editFuelType(@Valid @RequestBody FuelTypeDto fuelTypeDto) {
        System.out.println(fuelTypeDto.toString());
        try {
            this.fuelTypeService.editFuelType(fuelTypeDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Fuel type already exists.", HttpStatus.BAD_REQUEST);
        }
    }
}
