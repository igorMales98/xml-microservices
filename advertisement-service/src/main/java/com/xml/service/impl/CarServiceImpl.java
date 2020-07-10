package com.xml.service.impl;

import com.xml.dto.CarDto;
import com.xml.dto.CodebookInfoDto;
import com.xml.feignClients.CodebookFeignClient;
import com.xml.feignClients.RentRequestFeignClient;
import com.xml.model.Advertisement;
import com.xml.model.Car;
import com.xml.repository.AdvertisementRepository;
import com.xml.repository.CarRepository;
import com.xml.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private CodebookFeignClient codebookFeignClient;

    @Autowired
    private RentRequestFeignClient rentRequestFeignClient;

    @Override
    public void save(Car newCar) {
        this.carRepository.save(newCar);
    }

    @Override
    public void rate(CarDto carDto) {
        Car car = this.carRepository.getOne(carDto.getId());
        System.out.println("car:" + car.getId() + " " + car.getAverageRating() + " " + car.getTimesRated());
        car.setAverageRating(carDto.getAverageRating());
        car.setTimesRated(carDto.getTimesRated());
        this.carRepository.save(car);
        System.out.println("car:" + car.getId() + " " + car.getAverageRating() + " " + car.getTimesRated());
    }

    @Override
    public List<CarDto> getAdvertiserCars(Long advertiserId) {
        List<Advertisement> advertisements = this.advertisementRepository.getAllByAdvertiserId(advertiserId);
        List<CarDto> carDtos = new ArrayList<>();
        for (Advertisement a : advertisements) {
            CodebookInfoDto codebookInfoDto = this.codebookFeignClient.getMoreInfo(a.getCar().getCarBrandId(),
                    a.getCar().getCarModelId(), a.getCar().getCarClassId(), a.getCar().getFuelTypeId(),
                    a.getCar().getTransmissionTypeId(), a.getPricelistId());
            Integer timesRented = this.rentRequestFeignClient.timesRented(a.getId());
            float rentMileage = this.rentRequestFeignClient.rentMileage(a.getId());
            CarDto carDto = new CarDto();
            carDto.setId(a.getCar().getId());
            carDto.setCarBrand(codebookInfoDto.getCarBrandDto());
            carDto.setCarModel(codebookInfoDto.getCarModelDto());
            carDto.setCarClass(codebookInfoDto.getCarClassDto());
            carDto.setFuelType(codebookInfoDto.getFuelTypeDto());
            carDto.setTransmissionType(codebookInfoDto.getTransmissionTypeDto());
            carDto.setMileage(a.getCar().getMileage());
            carDto.setAllowedDistance(a.getCar().getAllowedDistance());
            carDto.setCollisionDamageWaiverExists(a.getCar().isCollisionDamageWaiverExists());
            carDto.setChildSeats(a.getCar().getChildSeats());
            carDto.setTimesRated(a.getCar().getTimesRated());
            carDto.setAverageRating(a.getCar().getAverageRating());
            carDto.setHasAndroid(a.getCar().isHasAndroid());
            carDto.setTimesRented(timesRented);
            carDto.setRentMileage(rentMileage);
            carDtos.add(carDto);
        }
        return carDtos;
    }

    @Override
    public Car getOne(Long id) {
        return carRepository.getOne(id);
    }
}
