package com.xml.mapper;

import com.xml.dto.UserDto;
import com.xml.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class UserDtoMapper implements MapperInterface<User, UserDto> {

    private ModelMapper modelMapper;

    @Override
    public User toEntity(UserDto dto) throws ParseException {
        return modelMapper.map(dto, User.class);
    }

    @Override
    public UserDto toDto(User entity) {
        return modelMapper.map(entity, UserDto.class);
    }

    @Autowired
    public UserDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
