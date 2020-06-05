package com.xml.feignClients;

import com.xml.dto.AdvertisementDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "advertisement-service")
public interface AdvertisementFeignClient {
    @GetMapping(value = "/api/advertisement/all", headers = {"Authorities=[TEST],Authorization={token}"})
    List<AdvertisementDto> all(@PathVariable("token") String token);
}
