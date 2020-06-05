package com.xml.service;

import com.xml.dto.RentRequestDto;

import java.util.List;

public interface RentRequestService {
    void createRentRequest(RentRequestDto rentRequestDto, String token);

    List<RentRequestDto> getReservedRentRequests(String token);
}
