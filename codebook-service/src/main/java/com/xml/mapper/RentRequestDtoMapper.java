package com.xml.mapper;
import com.xml.dto.RentRequestDto;
import com.xml.model.RentRequest;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import java.text.ParseException;

@Component
public class RentRequestDtoMapper implements MapperInterface<RentRequest, RentRequestDto> {

    private ModelMapper modelMapper;

    @Override
    public RentRequest toEntity(RentRequestDto dto) throws ParseException {
        return modelMapper.map(dto, RentRequest.class);
    }

    @Override
    public RentRequestDto toDto(RentRequest entity) {
        return modelMapper.map(entity, RentRequestDto.class);
    }

    @Autowired
    public RentRequestDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
