package com.xml.soap;

import com.xml.dto.CodebookInfoDto;
import com.xml.dto.CreateAdvertisementDto;
import com.xml.feignClients.CodebookFeignClient;
import com.xml.model.Car;
import com.xml.repository.CarRepository;
import com.xml.service.AdvertisementService;

import com.xml.soap.code.AdvertisementRequest;
import com.xml.soap.code.AdvertisementResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;


@Endpoint
public class AdvertisementEndPoint {
    private static final String NAMESPACE_URI = "http://localhost:8085/advertisement-service-schema";

    @Autowired
    private AdvertisementService advertisementService;

    @Autowired
    private CodebookFeignClient codebookFeignClient;

    @Autowired
    private CarRepository carRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "advertisementRequest")
    @ResponsePayload
    public AdvertisementResponse createAdvertisement(@RequestPayload AdvertisementRequest request) throws ParseException, IOException {
        System.out.println("SOAP - Create Advertisement");
        AdvertisementResponse response = new AdvertisementResponse();
        CreateAdvertisementDto advertisementDto = new CreateAdvertisementDto();

        advertisementDto.setAvailableFrom(LocalDateTime.parse(request.getAvailableFrom()));
        advertisementDto.setAvailableTo(LocalDateTime.parse(request.getAvailableTo()));
        advertisementDto.setAdvertiserId(request.getAdvertiserId());
        advertisementDto.setAllowedDistance(request.getCar().getAllowedDistance());

        CodebookInfoDto codebookInfo = this.codebookFeignClient.getMoreInfo(request.getCar().getCarBrandId(),
                request.getCar().getCarModelId(), request.getCar().getCarClassId(), request.getCar().getFuelTypeId(),
                request.getCar().getTransmissionTypeId(), request.getPricelistId());

        advertisementDto.setCarBrand(codebookInfo.getCarBrandDto());
        advertisementDto.setCarModel(codebookInfo.getCarModelDto());
        advertisementDto.setCarClass(codebookInfo.getCarClassDto());
        advertisementDto.setFuelType(codebookInfo.getFuelTypeDto());
        advertisementDto.setTransmissionType(codebookInfo.getTransmissionTypeDto());
        advertisementDto.setPricelist(codebookInfo.getPricelistDto());
        advertisementDto.setHasACDW(request.getCar().isCollisionDamageWaiverExists());
        advertisementDto.setMileage(request.getCar().getMileage());
        advertisementDto.setHasAndroid(request.getCar().isHasAndroid());
        advertisementDto.setDiscount(request.getDiscount());
        advertisementDto.setUserRole("");

        // Fale Android i ChildSeats i slike
        long advertisementId = advertisementService.saveAdvertisement(advertisementDto, "");
        Car car = this.carRepository.findTopByOrderByIdDesc();
        response.setAdvertisementId(advertisementId);
        response.setCarId(car.getId());

        return response;
    }
}
