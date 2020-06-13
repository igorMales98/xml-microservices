package com.xml.controller;

import com.xml.dto.RentRequestDto;
import com.xml.service.RentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(value = "https://localhost:4200")
@RequestMapping(value = "/api/rent-request", produces = MediaType.APPLICATION_JSON_VALUE)
public class RentRequestController {

    @Autowired
    private RentRequestService rentRequestService;

    @PostMapping(value = "")
    public ResponseEntity<?> createRentRequest(@Valid @RequestBody RentRequestDto rentRequestDto,
                                               @RequestHeader("Authorization") String token) {
        System.out.println(rentRequestDto.toString());
        try {
            this.rentRequestService.createRentRequest(rentRequestDto, token);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/allPaid")
    public ResponseEntity<List<RentRequestDto>> getPaidRentRequests(@RequestHeader("Authorization") String token) {
        try {
            List<RentRequestDto> rentRequestDtos = this.rentRequestService.getPaidRentRequests(token);
            return new ResponseEntity<>(rentRequestDtos, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getPeople(@PathVariable("id") Long id, @RequestHeader("Authorization") String token) {
        List<Long> retVal = this.rentRequestService.getPeople(id,token);
        return new ResponseEntity<>(retVal,HttpStatus.OK);
    }
}
