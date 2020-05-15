package com.xml.mapper;

import com.xml.dto.TransmissionTypeDto;
import com.xml.model.TransmissionType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class TransmissionTypeDtoMapper implements MapperInterface<TransmissionType, TransmissionTypeDto> {

    private ModelMapper modelMapper;

    @Override
    public TransmissionType toEntity(TransmissionTypeDto dto) throws ParseException {
        return modelMapper.map(dto, TransmissionType.class);
    }

    @Override
    public TransmissionTypeDto toDto(TransmissionType entity) {
        return modelMapper.map(entity, TransmissionTypeDto.class);
    }

    @Autowired
    public TransmissionTypeDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
