package com.xml.controller;

import com.xml.dto.AdvertisementDto;
import com.xml.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "https://localhost:4200")
@RequestMapping(value = "/api/advertisement", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<AdvertisementDto>> getAll(@RequestHeader("Authorization") String token) {
        try {
            List<AdvertisementDto> advertisementDtos = this.advertisementService.getAll(token);
            return new ResponseEntity<>(advertisementDtos, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getAdvertisementsPhotos/{id}")
    public ResponseEntity<?> getAdvertisementsPhotos(@PathVariable("id") Long id) {
        try {
            List<String> allEncodedImages = this.advertisementService.getAdvertisementPhotos(id);
            return new ResponseEntity<>(allEncodedImages, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/all/{userId}")
    public ResponseEntity<List<AdvertisementDto>> getUserAdvertisements(@PathVariable("userId") Long userId,
                                                                        @RequestHeader("Authorization") String token) {
        try {
            List<AdvertisementDto> advertisementDtos = this.advertisementService.getUserAdvertisements(userId, token);
            return new ResponseEntity<>(advertisementDtos, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
