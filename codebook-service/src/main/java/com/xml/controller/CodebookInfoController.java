package com.xml.controller;

import com.xml.dto.CodebookInfoDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "https://localhost:4200")
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CodebookInfoController {

    @GetMapping(value = "/getMoreInfo/{carBrandId}/{carModelId}/{carClassId}/{fuelTypeId}/{transmissionTypeId}/{pricelistId}")
    public ResponseEntity<CodebookInfoDto> getMoreInfo(@PathVariable("carBrandId") Long carBrandId, @PathVariable("carModelId") Long carModelId,
                                                       @PathVariable("carClassId") Long carClassId, @PathVariable("fuelTypeId") Long fuelTypeId,
                                                       @PathVariable("transmissionTypeId") Long transmissionTypeId,
                                                       @PathVariable("pricelistId") Long pricelistId) {
        return null;
    }
}
