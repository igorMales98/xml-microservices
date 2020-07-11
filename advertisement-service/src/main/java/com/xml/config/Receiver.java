package com.xml.config;

import com.xml.dto.CodebookMQDto;
import com.xml.model.Advertisement;
import com.xml.repository.AdvertisementRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {
    private CountDownLatch latch = new CountDownLatch(1);

    @Autowired
    private AdvertisementRepository advertisementRepository;


    @RabbitListener(queues = RabbitMQConfig.queueNameForAdvertisement)
    public void receiveMessage(CodebookMQDto codebookMQDto) {
        System.out.println("Received <" + codebookMQDto.getAdvertisementId() + ">");
        if (codebookMQDto.getCarBrandId() == null || codebookMQDto.getCarModelId() == null || codebookMQDto.getCarClassId() == null ||
                codebookMQDto.getFuelTypeId() == null || codebookMQDto.getTransmissionTypeId() == null || codebookMQDto.getPricalistId() == null) {
            System.out.println("Nesto nije valjalo");
        } else {
            System.out.println("Sve u redu");
            Advertisement advertisement = this.advertisementRepository.findById(codebookMQDto.getAdvertisementId()).get();
            advertisement.setValid(true);
            this.advertisementRepository.save(advertisement);
        }
        //latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
