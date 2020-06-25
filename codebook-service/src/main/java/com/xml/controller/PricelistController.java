package com.xml.controller;

import com.xml.dto.PricelistDto;
import com.xml.mapper.PricelistDtoMapper;
import com.xml.service.PricelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = "")
    @PreAuthorize("hasAuthority('READ_PRICELISTS')")
    public ResponseEntity<List<PricelistDto>> getAll() {
        try {
            List<PricelistDto> pricelistDtos = this.pricelistService.getAll().stream()
                    .map(pricelistDtoMapper::toDto).collect(Collectors.toList());
            return new ResponseEntity<>(pricelistDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
