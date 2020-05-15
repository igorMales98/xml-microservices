package com.xml.mapper;

import com.xml.dto.CarDto;
import com.xml.model.Car;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class CarDtoMapper implements MapperInterface<Car, CarDto> {

    private ModelMapper modelMapper;

    @Override
    public Car toEntity(CarDto dto) throws ParseException {
        return modelMapper.map(dto, Car.class);
    }

    @Override
    public CarDto toDto(Car entity) {
        return modelMapper.map(entity, CarDto.class);
    }

    @Autowired
    public CarDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
