package com.xml.service;

import com.xml.dto.CarDto;
import com.xml.model.Car;

import java.util.List;

public interface CarService {
    void save(Car newCar);

    void rate(CarDto carDto);

    Car getOne(Long id);

    List<CarDto> getAdvertiserCars(Long advertiserId);
}
