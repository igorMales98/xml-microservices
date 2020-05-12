package com.xml.mapper;

import com.xml.dto.PricelistDto;
import com.xml.model.Pricelist;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.text.ParseException;

@Component
public class PricelistDtoMapper implements MapperInterface<Pricelist, PricelistDto> {

    private ModelMapper modelMapper;

    @Override
    public Pricelist toEntity(PricelistDto dto) throws ParseException {
        return modelMapper.map(dto, Pricelist.class);
    }

    @Override
    public PricelistDto toDto(Pricelist entity) {
        return modelMapper.map(entity, PricelistDto.class);
    }

    @Autowired
    public PricelistDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
