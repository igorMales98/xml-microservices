package com.xml.controller;

import com.xml.dto.RentRequestDto;
import com.xml.dto.ReportDto;
import com.xml.service.RentRequestService;
import com.xml.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/report", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping(value = "")
    public ResponseEntity<?> createReport(@Valid @RequestBody ReportDto reportDto) {
        try {
            this.reportService.createReport(reportDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getRentMileage/{id}")
    public ResponseEntity<Float> getRentMileage(@PathVariable("id") Long carId) {
        try {
            float mileage = this.reportService.getRentMileage(carId);
            return new ResponseEntity<>(mileage, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
