package com.xml.controller;

import com.xml.dto.*;
import com.xml.mapper.*;
import com.xml.model.FuelType;
import com.xml.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@CrossOrigin(value = "https://localhost:4200")
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CodebookInfoController {

    @Autowired
    private CarBrandService carBrandService;

    @Autowired
    private CarModelService carModelService;

    @Autowired
    private CarClassService carClassService;

    @Autowired
    private FuelTypeService fuelTypeService;

    @Autowired
    private TransmissionTypeService transmissionTypeService;

    @Autowired
    private PricelistService pricelistService;

    @Autowired
    private CarBrandDtoMapper carBrandDtoMapper;

    @Autowired
    private CarModelDtoMapper carModelDtoMapper;

    @Autowired
    private CarClassDtoMapper carClassDtoMapper;

    @Autowired
    private FuelTypeDtoMapper fuelTypeDtoMapper;

    @Autowired
    private TransmissionTypeDtoMapper transmissionTypeDtoMapper;

    @Autowired
    private PricelistDtoMapper pricelistDtoMapper;

    Logger logger = LoggerFactory.getLogger(CodebookInfoController.class);


    @GetMapping(value = "/getMoreInfo/{carBrandId}/{carModelId}/{carClassId}/{fuelTypeId}/{transmissionTypeId}/{pricelistId}")
    public CodebookInfoDto getMoreInfo(@PathVariable("carBrandId") Long carBrandId, @PathVariable("carModelId") Long carModelId,
                                       @PathVariable("carClassId") Long carClassId, @PathVariable("fuelTypeId") Long fuelTypeId,
                                       @PathVariable("transmissionTypeId") Long transmissionTypeId,
                                       @PathVariable("pricelistId") Long pricelistId) {

        CodebookInfoDto codebookInfoDto = new CodebookInfoDto();

        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            logger.info("Date: {}, A user with username: {} try to get more info.", LocalDateTime.now(), userDetails.getUsername());
            CarBrandDto carBrandDto = this.carBrandDtoMapper.toDto(this.carBrandService.findById(carBrandId));
            CarModelDto carModelDto = this.carModelDtoMapper.toDto(this.carModelService.findById(carModelId));
            CarClassDto carClassDto = this.carClassDtoMapper.toDto(this.carClassService.findById(carClassId));
            FuelTypeDto fuelTypeDto = this.fuelTypeDtoMapper.toDto(this.fuelTypeService.findById(fuelTypeId));
            TransmissionTypeDto transmissionTypeDto = this.transmissionTypeDtoMapper.toDto(this.transmissionTypeService.findById(transmissionTypeId));
            PricelistDto pricelistDto = this.pricelistDtoMapper.toDto(this.pricelistService.findById(pricelistId));

            codebookInfoDto.setCarBrandDto(carBrandDto);
            codebookInfoDto.setCarModelDto(carModelDto);
            codebookInfoDto.setCarClassDto(carClassDto);
            codebookInfoDto.setFuelTypeDto(fuelTypeDto);
            codebookInfoDto.setTransmissionTypeDto(transmissionTypeDto);
            codebookInfoDto.setPricelistDto(pricelistDto);

            System.out.println(codebookInfoDto.toString());

        } catch (Exception e) {
            logger.error("Date : {}, There was an error to get more info. " +
                    "Error : {}.", LocalDateTime.now(), e.toString());
            e.printStackTrace();
        }
        logger.info("Date : {}, Successfully returned list of info.", LocalDateTime.now());
        return codebookInfoDto;
    }
}
