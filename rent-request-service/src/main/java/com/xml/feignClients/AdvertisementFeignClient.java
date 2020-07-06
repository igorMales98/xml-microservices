package com.xml.feignClients;

import com.xml.dto.AdvertisementDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "advertisement-service")
public interface AdvertisementFeignClient {
    @GetMapping(value = "/api/advertisements", headers = {"Authorities=[TEST],Authorization={token}"})
    List<AdvertisementDto> getAll(@RequestHeader("Authorization") String token);
    @GetMapping(value = "api/advertisements/{id}", headers = {"Authorization={token}"})
    AdvertisementDto getOne(@PathVariable("id") Long id, @RequestHeader("Authorization") String token);
}
