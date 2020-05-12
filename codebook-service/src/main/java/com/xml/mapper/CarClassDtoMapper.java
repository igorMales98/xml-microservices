package com.xml.mapper;

import com.xml.dto.CarClassDto;
import com.xml.model.CarClass;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class CarClassDtoMapper implements MapperInterface<CarClass, CarClassDto> {

    private ModelMapper modelMapper;

    @Override
    public CarClass toEntity(CarClassDto dto) throws ParseException {
        return modelMapper.map(dto, CarClass.class);
    }

    @Override
    public CarClassDto toDto(CarClass entity) {
        return modelMapper.map(entity, CarClassDto.class);
    }

    @Autowired
    public CarClassDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
