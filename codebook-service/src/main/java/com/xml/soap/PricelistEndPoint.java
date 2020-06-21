package com.xml.soap;

import com.xml.dto.PricelistDto;
import com.xml.service.PricelistService;
import localhost._8084.codebook_service_schema.PricelistRequest;
import localhost._8084.codebook_service_schema.PricelistResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.text.ParseException;

@Endpoint
public class PricelistEndPoint {
    private static final String NAMESPACE_URI = "http://localhost:8084/codebook-service-schema";

    @Autowired
    private PricelistService pricelistService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "pricelistRequest")
    @ResponsePayload
    public PricelistResponse createPricelist(@RequestPayload PricelistRequest request) throws ParseException {
        System.out.println("SOAP - Create pricelist.");

        PricelistResponse response = new PricelistResponse();
        PricelistDto pricelistDto = new PricelistDto();
        pricelistDto.setPriceForCDW(request.getPriceForCDW());
        pricelistDto.setPricePerDay(request.getPricePerDay());
        pricelistDto.setPricePerKm(request.getPricePerKm());

        Long id = this.pricelistService.create(pricelistDto);
        response.setPricelistId(id);
        return response;
    }
}
