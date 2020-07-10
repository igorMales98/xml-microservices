package com.xml.feignClients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "advertisement-service")
public interface CarFeignClient {

}
