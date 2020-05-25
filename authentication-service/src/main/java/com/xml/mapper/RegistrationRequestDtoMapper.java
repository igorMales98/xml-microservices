package com.xml.mapper;

import com.netflix.discovery.converters.Auto;
import com.xml.dto.RegistrationRequestDto;
import com.xml.model.RegistrationRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class RegistrationRequestDtoMapper implements MapperInterface<RegistrationRequest, RegistrationRequestDto> {

    private ModelMapper modelMapper;

    @Override
    public RegistrationRequest toEntity(RegistrationRequestDto dto) throws ParseException {
        return modelMapper.map(dto, RegistrationRequest.class);
    }

    @Override
    public RegistrationRequestDto toDto(RegistrationRequest entity) {
        return modelMapper.map(entity, RegistrationRequestDto.class);
    }

    @Autowired
    public RegistrationRequestDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
