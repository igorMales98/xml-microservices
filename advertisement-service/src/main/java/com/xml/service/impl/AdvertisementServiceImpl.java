package com.xml.service.impl;

import com.xml.dto.AdvertisementDto;
import com.xml.dto.CarDto;
import com.xml.dto.CodebookInfoDto;
import com.xml.dto.UserDto;
import com.xml.feignClients.CodebookFeignClient;
import com.xml.feignClients.UserFeignClient;
import com.xml.mapper.CommentDtoMapper;
import com.xml.model.Advertisement;
import com.xml.repository.AdvertisementRepository;
import com.xml.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private CodebookFeignClient codebookFeignClient;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private CommentDtoMapper commentDtoMapper;

    @Override
    public List<AdvertisementDto> getAll(String token) {
        List<AdvertisementDto> advertisementDtos = new ArrayList<>();

        List<Advertisement> allAdvertisements = this.advertisementRepository.findAll();

        for (Advertisement advertisement : allAdvertisements) {
            CodebookInfoDto codebookInfoDto = this.codebookFeignClient.getMoreInfo(advertisement.getCar().getCarBrandId(),
                    advertisement.getCar().getCarModelId(), advertisement.getCar().getCarClassId(), advertisement.getCar().getFuelTypeId(),
                    advertisement.getCar().getTransmissionTypeId(), advertisement.getPricelistId());

            System.out.println(codebookInfoDto.toString());
            System.out.println("token je " + token);
            UserDto advertiserDto = this.userFeignClient.getUserById(advertisement.getAdvertiserId(), token);
            System.out.println(advertiserDto);

            AdvertisementDto advertisementDto = new AdvertisementDto();
            advertisementDto.setId(advertisement.getId());
            advertisementDto.setAvailableFrom(advertisement.getAvailableFrom());
            advertisementDto.setAvailableTo(advertisement.getAvailableTo());
            advertisementDto.setPricelist(codebookInfoDto.getPricelistDto());

            CarDto carDto = new CarDto();
            carDto.setId(advertisement.getCar().getId());
            carDto.setAllowedDistance(advertisement.getCar().getAllowedDistance());
            carDto.setAverageRating(advertisement.getCar().getAverageRating());
            carDto.setCarBrand(codebookInfoDto.getCarBrandDto());
            carDto.setCarClass(codebookInfoDto.getCarClassDto());
            carDto.setCarModel(codebookInfoDto.getCarModelDto());
            carDto.setChildSeats(advertisement.getCar().getChildSeats());
            carDto.setCollisionDamageWaiverExists(advertisement.getCar().isCollisionDamageWaiverExists());
            carDto.setFuelType(codebookInfoDto.getFuelTypeDto());
            carDto.setHasAndroid(advertisement.getCar().isHasAndroid());
            carDto.setMileage(advertisement.getCar().getMileage());
            carDto.setTimesRated(advertisement.getCar().getTimesRated());
            carDto.setTransmissionType(codebookInfoDto.getTransmissionTypeDto());

            advertisementDto.setCar(carDto);
            advertisementDto.setComments(advertisement.getComments().stream().map(commentDtoMapper::toDto).collect(Collectors.toSet()));
            advertisementDto.setAdvertiser(advertiserDto);

            advertisementDtos.add(advertisementDto);
        }

        return advertisementDtos;
    }
}
