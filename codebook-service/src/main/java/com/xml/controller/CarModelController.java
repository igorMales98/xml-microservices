package com.xml.controller;

import com.xml.dto.CarClassDto;
import com.xml.dto.CarModelDto;
import com.xml.mapper.CarModelDtoMapper;
import com.xml.service.CarModelService;
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
@RequestMapping(value = "/api/car-model", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarModelController {

    @Autowired
    private CarModelService carModelService;

    @Autowired
    private CarModelDtoMapper carModelDtoMapper;

    @GetMapping(value = "/all")
    public ResponseEntity<List<CarModelDto>> getAll() {
        try {
            List<CarModelDto> carModelDtos = this.carModelService.getAll().stream().map(carModelDtoMapper::toDto)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(carModelDtos, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/brand/{brandId}")
    public ResponseEntity<List<CarModelDto>> getBrandModels(@PathVariable("brandId") Long brandId) {
        try {
            List<CarModelDto> carModelDtos = this.carModelService.getBrandModels(brandId).stream()
                    .map(carModelDtoMapper::toDto).collect(Collectors.toList());
            return new ResponseEntity<>(carModelDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/addCarModel")
    public ResponseEntity<?> addCarModel(@Valid @RequestBody CarModelDto carModelDto) {
        System.out.println("Stampa: " + carModelDto.toString());
        try {
            this.carModelService.saveCarModel(carModelDto);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/deleteCarModel/{id}")
    public ResponseEntity<?> deleteCarModel(@PathVariable Long id) throws ParseException {

        try {
            this.carModelService.deleteCarModel(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/editCarModel")
    public ResponseEntity<?> editCarModel(@Valid @RequestBody CarModelDto carModelDto) {
        System.out.println(carModelDto.toString());
        try {
            this.carModelService.editCarModel(carModelDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Car model already exists.", HttpStatus.BAD_REQUEST);
        }
    }
}
