package com.xml.controller;

import com.xml.dto.AdvertisementDto;
import com.xml.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "https://localhost:4200")
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<AdvertisementDto>> getAll() {
        try {
            List<AdvertisementDto> advertisementDtos = this.advertisementService.getAll();
            return new ResponseEntity<>(advertisementDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
