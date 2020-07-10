package com.xml.service.impl;

import com.xml.config.RabbitMQConfig;
import com.xml.config.RabbitMQReceiverConfig;
import com.xml.dto.CoordinatesDto;
import com.xml.service.LocationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
public class LocationServiceImpl implements LocationService {

    public static CoordinatesDto coordinates = new CoordinatesDto();
    public static String seconds = "0";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public CoordinatesDto getLocation(String androidToken) {
        String message = androidToken+" "+seconds;
        System.out.println(message);
        rabbitTemplate.convertAndSend(RabbitMQConfig.locationExchangeName,RabbitMQConfig.locationRoutingKey, message);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return coordinates;
    }

    @Override
    public void resetSeconds() {
        seconds = "0";
        coordinates.setSeconds("0");
    }

    @RabbitListener(queues = RabbitMQReceiverConfig.latlngQueueName)
    public void receiveMessage(CoordinatesDto coordinatesDto) {
        if (!coordinatesDto.getSeconds().equals("-1")) {
            Integer secondsInt = Integer.valueOf(seconds) + 2;
            seconds = secondsInt.toString();
            System.out.println("Received <" + coordinatesDto.getLat() + " " + coordinatesDto.getLng() + " "+ coordinatesDto.getSeconds()+">");
        }
        coordinatesDto.setSeconds(seconds);
        coordinates = coordinatesDto;
    }
}
