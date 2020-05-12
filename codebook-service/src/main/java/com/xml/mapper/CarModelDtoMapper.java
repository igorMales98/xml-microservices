package com.xml.mapper;

import com.xml.dto.CarModelDto;
import com.xml.model.CarModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import java.text.ParseException;

@Component
public class CarModelDtoMapper implements MapperInterface<CarModel, CarModelDto> {

    private ModelMapper modelMapper;

    @Override
    public CarModel toEntity(CarModelDto dto) throws ParseException {
        return modelMapper.map(dto, CarModel.class);
    }

    @Override
    public CarModelDto toDto(CarModel entity) {
        return modelMapper.map(entity, CarModelDto.class);
    }

    @Autowired
    public CarModelDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
