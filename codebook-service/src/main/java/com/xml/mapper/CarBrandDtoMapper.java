package com.xml.mapper;

import com.xml.dto.CarBrandDto;
import com.xml.model.CarBrand;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class CarBrandDtoMapper implements MapperInterface<CarBrand, CarBrandDto> {

    private ModelMapper modelMapper;

    @Override
    public CarBrand toEntity(CarBrandDto dto) throws ParseException {
        return modelMapper.map(dto, CarBrand.class);
    }

    @Override
    public CarBrandDto toDto(CarBrand entity) {
        return modelMapper.map(entity, CarBrandDto.class);
    }

    @Autowired
    public CarBrandDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
