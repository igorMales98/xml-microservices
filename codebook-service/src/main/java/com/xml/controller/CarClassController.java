package com.xml.controller;

import com.xml.dto.CarClassDto;
import com.xml.mapper.CarClassDtoMapper;
import com.xml.service.CarClassService;
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
}
