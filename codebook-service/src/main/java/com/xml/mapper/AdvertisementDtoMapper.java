package com.xml.mapper;

import com.xml.dto.AdvertisementDto;
import com.xml.model.Advertisement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class AdvertisementDtoMapper implements MapperInterface<Advertisement, AdvertisementDto> {

    private ModelMapper modelMapper;

    @Override
    public Advertisement toEntity(AdvertisementDto dto) throws ParseException {
        return modelMapper.map(dto, Advertisement.class);
    }

    @Override
    public AdvertisementDto toDto(Advertisement entity) {
        return modelMapper.map(entity, AdvertisementDto.class);
    }

    @Autowired
    public AdvertisementDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
