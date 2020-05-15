package com.xml.mapper;

import com.xml.dto.CustomerDto;
import com.xml.model.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class CustomerDtoMapper implements MapperInterface<Customer, CustomerDto> {

    private ModelMapper modelMapper;

    @Override
    public Customer toEntity(CustomerDto dto) throws ParseException {
        return this.modelMapper.map(dto, Customer.class);
    }

    @Override
    public CustomerDto toDto(Customer entity) {
        return this.modelMapper.map(entity, CustomerDto.class);
    }

    @Autowired
    public CustomerDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
