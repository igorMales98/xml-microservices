package com.xml.soap;

import com.xml.dto.RentRequestDto;
import com.xml.dto.UserDto;
import com.xml.model.RentRequest;
import com.xml.repository.RentRequestRepository;
import com.xml.service.RentRequestService;
import localhost._8089.rent_request_service_schema.RentRequestRequest;
import localhost._8089.rent_request_service_schema.RentRequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;

@Endpoint
public class RentRequestEndpoint {
    private static final String NAMESPACE_URI = "http://localhost:8089/rent-request-service-schema";

    @Autowired
    private RentRequestService rentRequestService;

    @Autowired
    private RentRequestRepository rentRequestRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "rentRequestRequest")
    @ResponsePayload
    public RentRequestResponse createRentRequest(@RequestPayload RentRequestRequest request) throws ParseException, IOException {
        System.out.println("SOAP - Create Rent Request");
        RentRequestResponse response = new RentRequestResponse();
        RentRequestDto rentRequestDto = new RentRequestDto();

        rentRequestDto.setReservedFrom(LocalDateTime.parse(request.getReservedFrom()));
        rentRequestDto.setReservedTo(LocalDateTime.parse(request.getReservedTo()));
        //rentRequestDto.setAdvertisementsForRent(request.getAdvertisementsForRent());

        UserDto userDto = new UserDto();
        userDto.setType(request.getUser().getType());
        userDto.setFirstName(request.getUser().getFirstName());
        userDto.setLastName(request.getUser().getLastName());
        userDto.setCountry(request.getUser().getCountry());
        userDto.setCity(request.getUser().getCity());
        userDto.setAddress(request.getUser().getEmail());
        userDto.setEmail(request.getUser().getEmail());
        userDto.setPhone(request.getUser().getPhone());

        rentRequestDto.setCustomer(userDto);

        // Dovrsiti i skontati kako pozvati auth service jer nemamo token

        return response;
    }

}
