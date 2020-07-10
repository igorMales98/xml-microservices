package com.xml.feignClients;

import com.xml.dto.RentRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;


@FeignClient(name = "rent-request-service")
public interface RentRequestFeignClient {

    @GetMapping(value = "/api/rent-requests/allPaid", headers = {"Authorities=[READ_RENT_REQUESTS]", "Authorization={token}"})
    List<RentRequestDto> getPaidRentRequests(@RequestHeader("Authorization") String token);

    @GetMapping(value = "/api/rent-requests/timesRented/{advertisementId}", headers = {"Authorities=[READ_RENT_REQUESTS]"})
    Integer timesRented(@PathVariable("advertisementId") Long advertisementId);

    @GetMapping(value = "/api/rent-requests/rentMileage/{advertisementId}", headers = {"Authorities=[READ_RENT_REQUESTS]"})
    Integer rentMileage(@PathVariable("advertisementId") Long advertisementId);

}
