package com.xml.controller;

import com.xml.dto.CarClassDto;
import com.xml.mapper.CarClassDtoMapper;
import com.xml.service.CarClassService;
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
@RequestMapping(value = "/api/car-classes", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarClassController {

    @Autowired
    private CarClassService carClassService;

    @Autowired
    private CarClassDtoMapper carClassDtoMapper;

    @GetMapping(value = "")
    public ResponseEntity<List<CarClassDto>> getAll() {
        try {
            List<CarClassDto> carClassDtos = this.carClassService.getAll().stream()
                    .map(carClassDtoMapper::toDto).collect(Collectors.toList());
            return new ResponseEntity<>(carClassDtos, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/addCarClass")
    public ResponseEntity<?> addCarClass(@Valid @RequestBody CarClassDto carClassDto) {
        System.out.println("Stampa: " + carClassDto.getName());
        try {
            this.carClassService.saveCarClass(carClassDto);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/deleteCarClass/{id}")
    public ResponseEntity<?> deleteCarClass(@PathVariable Long id) throws ParseException {
        try {
            this.carClassService.deleteCarClass(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/editCarClass")
    public ResponseEntity<?> editCarClass(@Valid @RequestBody CarClassDto carClassDto) {
        System.out.println(carClassDto.toString());
        try {
            this.carClassService.editCarClass(carClassDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Car class already exists.", HttpStatus.BAD_REQUEST);
        }
    }
}
