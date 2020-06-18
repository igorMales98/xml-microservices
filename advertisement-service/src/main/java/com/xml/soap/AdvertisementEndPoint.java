package com.xml.soap;

import com.xml.dto.CreateAdvertisementDto;
import com.xml.model.Advertisement;
import com.xml.model.Car;
import com.xml.service.AdvertisementService;
import localhost._8085.advertisement_service_schema.AdvertisementRequest;
import localhost._8085.advertisement_service_schema.AdvertisementResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.time.LocalDateTime;


@Endpoint
public class AdvertisementEndPoint {
    private static final String NAMESPACE_URI = "http://localhost:8085/advertisement-service-schema";

    @Autowired
    private AdvertisementService advertisementService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "advertisementRequest")
    @ResponsePayload
    public AdvertisementResponse createAdvertisement(@RequestPayload AdvertisementRequest request) {
        System.out.println("SOAP - Create Advertisement");
        AdvertisementResponse response = new AdvertisementResponse();
        CreateAdvertisementDto advertisementDto = new CreateAdvertisementDto();

        advertisementDto.setAvailableFrom(LocalDateTime.parse(request.getAvailableFrom()));
        advertisementDto.setAvailableTo(LocalDateTime.parse(request.getAvailableTo()));
        advertisementDto.setAdvertiserId(request.getAdvertiserId());
        advertisementDto.setAllowedDistance(request.getCar().getAllowedDistance());
        advertisementDto.setHasACDW(request.getCar().isCollisionDamageWaiverExists());






        return response;
    }
}
