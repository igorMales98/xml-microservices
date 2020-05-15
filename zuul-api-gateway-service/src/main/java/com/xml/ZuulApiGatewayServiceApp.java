package com.xml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients
@EnableZuulProxy
@SpringBootApplication
public class ZuulApiGatewayServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApiGatewayServiceApp.class, args);
    }
}