package com.xml.feignClients;

import com.xml.dto.CodebookInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "codebook-service")
public interface CodebookFeignClient {

    @GetMapping(value = "/api/getMoreInfo/{carBrandId}/{carModelId}/{carClassId}/{fuelTypeId}/{transmissionTypeId}/{pricelistId}",
            headers = {"Authorities=[TEST]"})
    CodebookInfoDto getMoreInfo(@PathVariable("carBrandId") Long carBrandId, @PathVariable("carModelId") Long carModelId,
                                @PathVariable("carClassId") Long carClassId, @PathVariable("fuelTypeId") Long fuelTypeId,
                                @PathVariable("transmissionTypeId") Long transmissionTypeId,
                                @PathVariable("pricelistId") Long pricelistId);
}
