package com.xml.controller;

import com.xml.dto.CarBrandDto;
import com.xml.mapper.CarBrandDtoMapper;
import com.xml.service.CarBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = "")
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
}
