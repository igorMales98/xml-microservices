package com.xml.mapper;

import com.xml.dto.FuelTypeDto;
import com.xml.model.FuelType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class FuelTypeDtoMapper implements MapperInterface<FuelType, FuelTypeDto> {

    private ModelMapper modelMapper;

    @Override
    public FuelType toEntity(FuelTypeDto dto) throws ParseException {
        return modelMapper.map(dto, FuelType.class);
    }

    @Override
    public FuelTypeDto toDto(FuelType entity) {
        return modelMapper.map(entity, FuelTypeDto.class);
    }

    @Autowired
    public FuelTypeDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
