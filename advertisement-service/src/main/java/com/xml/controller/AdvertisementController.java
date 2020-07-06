package com.xml.controller;

import com.xml.dto.AdvertisementDto;
import com.xml.dto.CreateAdvertisementDto;
import com.xml.service.AdvertisementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(value = "https://localhost:4200")
@RequestMapping(value = "/api/advertisements", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    Logger logger = LoggerFactory.getLogger(AdvertisementController.class);

    @GetMapping(value = "")
    @PreAuthorize("hasAuthority('READ_ADVERTISEMENTS')")
    public ResponseEntity<List<AdvertisementDto>> getAll(@RequestHeader("Authorization") String token) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to get all advertisements.", LocalDateTime.now(), userDetails.getUsername());
            List<AdvertisementDto> advertisementDtos = this.advertisementService.getAll(token);
            for (AdvertisementDto advertisementDto : advertisementDtos) {
                logger.info("Date: {}, Image successfully set on advertisement. ");
                advertisementDto.setImg(this.advertisementService.getAdvertisementPhotos(advertisementDto.getId()));
            }
            logger.info("Date: {}, Successfully returned list of all advertisements.", LocalDateTime.now());
            return new ResponseEntity<>(advertisementDtos, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to get all advertisements. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /*@GetMapping(value = "/getAdvertisementsPhotos/{id}")
    public ResponseEntity<?> getAdvertisementsPhotos(@PathVariable("id") Long id) {
        try {
            List<String> allEncodedImages = this.advertisementService.getAdvertisementPhotos(id);
            return new ResponseEntity<>(allEncodedImages, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }*/

    @GetMapping(value = "/{id}")
    // @PreAuthorize("hasAuthority('READ_ADVERTISEMENTS')")
    public ResponseEntity<AdvertisementDto> getOne(@PathVariable("id") Long id, @RequestHeader("Authorization") String token) {
        try {
            System.out.println("token je " + token);
            token = token.substring(1);
            System.out.println("token je " + token);

            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to get an advertisement.", LocalDateTime.now(), userDetails.getUsername());
            AdvertisementDto advertisementDto = this.advertisementService.getOne(id, token);
            logger.info("Date: {}, Image successfully set on advertisement.", LocalDateTime.now());
            advertisementDto.setImg(this.advertisementService.getAdvertisementPhotos(advertisementDto.getId()));
            logger.info("Date: {}, An advertisement got successfully.", LocalDateTime.now());
            return new ResponseEntity<>(advertisementDto, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to get an advertisement. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/user/{userId}")
    @PreAuthorize("hasAuthority('READ_USER_ADVERTISEMENTS')")
    public ResponseEntity<List<AdvertisementDto>> getUserAdvertisements(@PathVariable("userId") Long userId,
                                                                        @RequestHeader("Authorization") String token) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to get user advertisement.", LocalDateTime.now(), userDetails.getUsername());
            List<AdvertisementDto> advertisementDtos = this.advertisementService.getUserAdvertisements(userId, token);
            for (AdvertisementDto advertisementDto : advertisementDtos) {
                logger.info("Date: {}, Image successfully set on user advertisement.", LocalDateTime.now());
                advertisementDto.setImg(this.advertisementService.getAdvertisementPhotos(advertisementDto.getId()));
            }
            logger.info("Date: {}, User advertisement got successfully.", LocalDateTime.now());
            return new ResponseEntity<>(advertisementDtos, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to get user advertisement. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "")
    @PreAuthorize("hasAuthority('CREATE_ADVERTISEMENTS')")
    public ResponseEntity<Long> createAdvertisement(@Valid @RequestBody CreateAdvertisementDto createAdvertisementDto,
                                                    @RequestHeader("Authorization") String token) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to create an advertisement.", LocalDateTime.now(), userDetails.getUsername());
            Long advertisementId = this.advertisementService.saveAdvertisement(createAdvertisementDto, token);
            logger.info("Date: {}, An advertisement created successfully.", LocalDateTime.now());
            return new ResponseEntity<>(advertisementId, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to create an advertisement. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping(value = "/uploadPhotos/{id}")
    @PreAuthorize("hasAuthority('UPLOAD_PHOTOS')")
    public ResponseEntity<?> uploadPhotosForAdvertisement(@RequestPart("myFile") MultipartFile[] files, @PathVariable("id") Long advertisementId) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to upload photo for advertisement.", LocalDateTime.now(), userDetails.getUsername());
            this.advertisementService.uploadPhotos(files, advertisementId);
            logger.info("Date: {}, Photo for advertisement uploaded successfully.", LocalDateTime.now());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            logger.error("Date : {}, There was an error to upload photo for advertisement. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/basicSearch/{dateFrom}/{dateTo}/{place}")
    @PreAuthorize("hasAuthority('BASIC_SEARCH')")
    public ResponseEntity<List<AdvertisementDto>> getInPeriod(@PathVariable("dateFrom") String dateFrom,
                                                              @PathVariable("dateTo") String dateTo,
                                                              @PathVariable("place") String place,
                                                              @RequestHeader("Authorization") String token) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to get advertisement in period from {} to {}.", LocalDateTime.now(), userDetails.getUsername(), dateFrom, dateTo);
            List<AdvertisementDto> advertisementDtos = this.advertisementService.basicSearch(dateFrom, dateTo, place, token);
            for (AdvertisementDto advertisementDto : advertisementDtos) {
                logger.info("Date: {}, Image successfully set on a advertisement.", LocalDateTime.now());
                advertisementDto.setImg(this.advertisementService.getAdvertisementPhotos(advertisementDto.getId()));
            }
            logger.info("Date: {}, A advertisement in period got successfully.", LocalDateTime.now());
            return new ResponseEntity<>(advertisementDtos, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to get advertisement in period. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/basicSearchForMyAdvertisements/{dateFrom}/{dateTo}/{id}")
    @PreAuthorize("hasAuthority('BASIC_SEARCH_USER')")
    public ResponseEntity<List<AdvertisementDto>> getInPeriodForMyAdvertisements(@PathVariable("dateFrom") String dateFrom,
                                                                                 @PathVariable("dateTo") String dateTo,
                                                                                 @PathVariable("id") Long id,
                                                                                 @RequestHeader("Authorization") String token) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to get his advertisement in period from {} to {}.", LocalDateTime.now(), userDetails.getUsername(), dateFrom, dateTo);
            List<AdvertisementDto> advertisementDtos = this.advertisementService.basicSearchForMyAdvertisements(dateFrom, dateTo, id, token);
            for (AdvertisementDto advertisementDto : advertisementDtos) {
                logger.info("Date: {}, Image successfully set on his advertisement.", LocalDateTime.now());
                advertisementDto.setImg(this.advertisementService.getAdvertisementPhotos(advertisementDto.getId()));
            }
            return new ResponseEntity<>(advertisementDtos, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Date : {}, There was an error to get a advertisement. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
