package com.xml.mapper;

import com.xml.dto.CommentDto;
import com.xml.model.Comment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class CommentDtoMapper implements MapperInterface<Comment, CommentDto> {

    private ModelMapper modelMapper;

    @Override
    public Comment toEntity(CommentDto dto) throws ParseException {
        return modelMapper.map(dto, Comment.class);
    }

    @Override
    public CommentDto toDto(Comment entity) {
        return modelMapper.map(entity, CommentDto.class);
    }

    @Autowired
    public CommentDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
