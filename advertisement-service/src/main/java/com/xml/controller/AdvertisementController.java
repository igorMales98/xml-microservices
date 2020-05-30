package com.xml.controller;

import com.xml.dto.AdvertisementDto;
import com.xml.dto.CreateAdvertisementDto;
import com.xml.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
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

    @PostMapping(value = "")
    public ResponseEntity<Long> createAdvertisement(@Valid @RequestBody CreateAdvertisementDto createAdvertisementDto) {
        System.out.println(createAdvertisementDto);
        try {
            Long advertisementId = this.advertisementService.saveAdvertisement(createAdvertisementDto);
            return new ResponseEntity<>(advertisementId, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping(value = "/uploadPhotos/{id}")
    public ResponseEntity<?> uploadPhotosForAdvertisement(@RequestPart("myFile") MultipartFile[] files, @PathVariable("id") Long advertisementId) {
        try {
            this.advertisementService.uploadPhotos(files, advertisementId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
