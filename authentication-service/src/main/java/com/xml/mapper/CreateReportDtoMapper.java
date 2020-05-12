package com.xml.mapper;

import com.xml.dto.CreateReportDto;
import com.xml.dto.ReportDto;
import com.xml.model.Report;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class CreateReportDtoMapper implements MapperInterface<Report, CreateReportDto> {

    private ModelMapper modelMapper;

    @Override
    public Report toEntity(CreateReportDto dto) throws ParseException {
        return modelMapper.map(dto, Report.class);
    }

    @Override
    public CreateReportDto toDto(Report entity) {
        return modelMapper.map(entity, CreateReportDto.class);
    }

    @Autowired
    public CreateReportDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
