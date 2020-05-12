package com.xml.mapper;

import com.xml.dto.MessageDto;
import com.xml.model.Message;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class MessageDtoMapper implements MapperInterface<Message, MessageDto> {

    private ModelMapper modelMapper;

    @Override
    public Message toEntity(MessageDto dto) throws ParseException {
        return modelMapper.map(dto, Message.class);
    }

    @Override
    public MessageDto toDto(Message entity) {
        return modelMapper.map(entity, MessageDto.class);
    }

    @Autowired
    public MessageDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
