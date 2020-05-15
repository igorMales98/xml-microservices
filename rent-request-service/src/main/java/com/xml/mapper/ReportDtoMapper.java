package com.xml.mapper;

import com.xml.dto.ReportDto;
import com.xml.model.Report;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class ReportDtoMapper implements MapperInterface<Report, ReportDto>{

    private ModelMapper modelMapper;

    @Override
    public Report toEntity(ReportDto dto) throws ParseException {
        return modelMapper.map(dto, Report.class);
    }

    @Override
    public ReportDto toDto(Report entity) {
        return modelMapper.map(entity, ReportDto.class);
    }

    @Autowired
    public ReportDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
