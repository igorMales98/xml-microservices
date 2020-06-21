package com.xml.service;

import com.xml.dto.CarDto;
import com.xml.model.Car;

public interface CarService {
    void save(Car newCar);

    void rate(CarDto carDto);
}
