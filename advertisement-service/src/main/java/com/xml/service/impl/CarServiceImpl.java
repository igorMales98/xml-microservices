package com.xml.service.impl;

import com.xml.dto.CarDto;
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

    @Override
    public void rate(CarDto carDto) {
        Car car = this.carRepository.getOne(carDto.getId());
        System.out.println("car:"+car.getId()+" "+car.getAverageRating()+" "+car.getTimesRated());
        car.setAverageRating(carDto.getAverageRating());
        car.setTimesRated(carDto.getTimesRated());
        this.carRepository.save(car);
        System.out.println("car:"+car.getId()+" "+car.getAverageRating()+" "+car.getTimesRated());
    }

    @Override
    public Car getOne(Long id) {
        return carRepository.getOne(id);
    }
}
