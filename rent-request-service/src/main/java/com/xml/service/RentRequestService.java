package com.xml.service;

import com.xml.dto.RentRequestDto;

import java.util.List;

public interface RentRequestService {
    void createRentRequest(RentRequestDto rentRequestDto, String token);

    List<Long> getPeople(Long id, String token);
}
