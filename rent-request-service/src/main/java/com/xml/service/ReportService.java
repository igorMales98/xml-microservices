package com.xml.service;

import com.xml.dto.ReportDto;
import java.text.ParseException;

public interface ReportService {

    void createReport(ReportDto reportDto) throws ParseException;

    float getRentMileage(Long carId);
}
