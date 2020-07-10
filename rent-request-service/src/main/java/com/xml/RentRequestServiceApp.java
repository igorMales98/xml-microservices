package com.xml;

import com.xml.model.EmailBinding;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

@EnableBinding(EmailBinding.class)
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class RentRequestServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(RentRequestServiceApp.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
