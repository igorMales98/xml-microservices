package com.xml.config;

import com.xml.dto.CodebookMQDto;
import com.xml.model.TransmissionType;
import com.xml.repository.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {

    @Autowired
    private CarBrandRepository carBrandRepository;

    @Autowired
    private CarModelRepository carModelRepository;

    @Autowired
    private CarClassRepository carClassRepository;

    @Autowired
    private FuelTypeRepository fuelTypeRepository;

    @Autowired
    private TransmissionTypeRepository transmissionTypeRepository;

    @Autowired
    private PricelistRepository pricelistRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private CountDownLatch latch = new CountDownLatch(1);

    @RabbitListener(queues = RabbitMQConfig.queueName)
    public void receiveMessage(CodebookMQDto codebookMQDto) {
        System.out.println("Received <" + codebookMQDto.getAdvertisementId() + ">");
        if (this.carBrandRepository.existsById(codebookMQDto.getCarBrandId())) {
             if (!this.carBrandRepository.findById(codebookMQDto.getCarBrandId()).get().isDeleted()) {
                if (this.carModelRepository.existsById(codebookMQDto.getCarModelId())) {
                     if (!this.carModelRepository.findById(codebookMQDto.getCarModelId()).get().isDeleted()) {
                        if (this.carClassRepository.existsById(codebookMQDto.getCarClassId())) {
                             if (!this.carClassRepository.findById(codebookMQDto.getCarClassId()).get().isDeleted()) {
                                if (this.fuelTypeRepository.existsById(codebookMQDto.getFuelTypeId())) {
                                     if (!this.fuelTypeRepository.findById(codebookMQDto.getFuelTypeId()).get().isDeleted()) {
                                        if (this.transmissionTypeRepository.existsById(codebookMQDto.getTransmissionTypeId())) {
                                             if (!this.transmissionTypeRepository.findById(codebookMQDto.getTransmissionTypeId()).get().isDeleted()) {
                                                if (this.pricelistRepository.existsById(codebookMQDto.getPricalistId())) {
                                                    if (!this.pricelistRepository.findById(codebookMQDto.getPricalistId()).get().isDeleted()) {
                                                        rabbitTemplate.convertAndSend(RabbitMQConfig.exchangeNameForAdvertisement,RabbitMQConfig.routingKeyForAdvertisement,codebookMQDto);
                                                    } else {
                                                        codebookMQDto.setPricalistId(null);
                                                        rabbitTemplate.convertAndSend(RabbitMQConfig.topicExchangeName,RabbitMQConfig.routingKey,codebookMQDto);
                                                    }
                                                } else {
                                                    codebookMQDto.setPricalistId(null);
                                                    rabbitTemplate.convertAndSend(RabbitMQConfig.exchangeNameForAdvertisement,RabbitMQConfig.routingKeyForAdvertisement,codebookMQDto);
                                                }
                                            } else {
                                                codebookMQDto.setTransmissionTypeId(null);
                                                rabbitTemplate.convertAndSend(RabbitMQConfig.topicExchangeName,RabbitMQConfig.routingKey,codebookMQDto);
                                            }
                                        } else {
                                            codebookMQDto.setTransmissionTypeId(null);
                                            rabbitTemplate.convertAndSend(RabbitMQConfig.exchangeNameForAdvertisement,RabbitMQConfig.routingKeyForAdvertisement,codebookMQDto);
                                        }
                                    } else {
                                        codebookMQDto.setFuelTypeId(null);
                                        rabbitTemplate.convertAndSend(RabbitMQConfig.topicExchangeName,RabbitMQConfig.routingKey,codebookMQDto);
                                    }
                                } else {
                                    codebookMQDto.setFuelTypeId(null);
                                    rabbitTemplate.convertAndSend(RabbitMQConfig.exchangeNameForAdvertisement,RabbitMQConfig.routingKeyForAdvertisement,codebookMQDto);
                                }
                            } else {
                                codebookMQDto.setCarClassId(null);
                                rabbitTemplate.convertAndSend(RabbitMQConfig.topicExchangeName,RabbitMQConfig.routingKey,codebookMQDto);
                            }
                        } else {
                            codebookMQDto.setCarClassId(null);
                            rabbitTemplate.convertAndSend(RabbitMQConfig.exchangeNameForAdvertisement,RabbitMQConfig.routingKeyForAdvertisement,codebookMQDto);
                        }
                    } else {
                        codebookMQDto.setCarModelId(null);
                        rabbitTemplate.convertAndSend(RabbitMQConfig.topicExchangeName,RabbitMQConfig.routingKey,codebookMQDto);
                    }
                } else {
                    codebookMQDto.setCarModelId(null);
                    rabbitTemplate.convertAndSend(RabbitMQConfig.exchangeNameForAdvertisement,RabbitMQConfig.routingKeyForAdvertisement,codebookMQDto);
                }
            } else {
                codebookMQDto.setCarBrandId(null);
                rabbitTemplate.convertAndSend(RabbitMQConfig.topicExchangeName,RabbitMQConfig.routingKey,codebookMQDto);
            }
        } else {
            codebookMQDto.setCarBrandId(null);
            rabbitTemplate.convertAndSend(RabbitMQConfig.exchangeNameForAdvertisement,RabbitMQConfig.routingKeyForAdvertisement,codebookMQDto);
        }




        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
