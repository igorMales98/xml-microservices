package com.xml.service;

import com.xml.dto.RentRequestDto;

public interface RentRequestService {
    void createRentRequest(RentRequestDto rentRequestDto, String token);
}
