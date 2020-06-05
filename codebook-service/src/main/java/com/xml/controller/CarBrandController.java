package com.xml.controller;

import com.xml.dto.CarBrandDto;
import com.xml.mapper.CarBrandDtoMapper;
import com.xml.service.CarBrandService;
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
@RequestMapping(value = "/api/car-brand", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarBrandController {

    @Autowired
    private CarBrandService carBrandService;

    @Autowired
    private CarBrandDtoMapper carBrandDtoMapper;

    @GetMapping(value = "/all")
    public ResponseEntity<List<CarBrandDto>> getAll() {
        try {
            List<CarBrandDto> carBrandDtos = carBrandService.getAll().stream().map(carBrandDtoMapper::toDto).
                    collect(Collectors.toList());
            return new ResponseEntity<>(carBrandDtos, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/addCarBrand")
    public ResponseEntity<?> addCarBrand(@Valid @RequestBody CarBrandDto carBrandDto) {
        System.out.println("Stampa: " + carBrandDto.getName());
        try {
            this.carBrandService.saveCarBrand(carBrandDto);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/deleteCarBrand/{id}")
    public ResponseEntity<?> deleteCarBrand(@PathVariable Long id) throws ParseException {

        try {
            this.carBrandService.deleteCarBrand(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/editCarBrand")
    public ResponseEntity<?> editCarBrand(@Valid @RequestBody CarBrandDto carBrandDto) {
        System.out.println(carBrandDto.toString());
        try {
            this.carBrandService.editCarBrand(carBrandDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Car brand already exists.", HttpStatus.BAD_REQUEST);
        }
    }
}
