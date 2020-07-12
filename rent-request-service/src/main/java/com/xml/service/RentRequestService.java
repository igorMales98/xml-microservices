package com.xml.service;

import com.xml.dto.RentRequestDto;

import java.util.List;

public interface RentRequestService {
    void createRentRequest(RentRequestDto rentRequestDto, String token);

    List<Long> getPeople(Long id, String token);

    List<RentRequestDto> getPaidRentRequests(String token);

    List<RentRequestDto> getUserRentRequests(Long id, String token);

    void cancelRentRequest(Long id, String token);

    void acceptRentRequest(Long id, String token);

    List<RentRequestDto> getCustomerRentRequests(String token, Long id);

    List<RentRequestDto> getCustomerPendingRentRequests(String token, Long id);

    Integer getTimesRented(Long advertisementId);

    float getRentMileage(Long advertisementId);

    List<RentRequestDto> getAdvertiserPaid(String token, Long id);
}
