package com.xml.service.impl;

import com.xml.model.Car;
import com.xml.repository.CarRepository;
import com.xml.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public void save(Car newCar) {
        this.carRepository.save(newCar);
    }
}
