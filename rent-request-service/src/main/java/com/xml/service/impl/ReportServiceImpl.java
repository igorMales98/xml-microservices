package com.xml.service.impl;

import com.xml.dto.AdvertisementDto;
import com.xml.dto.ReportDto;
import com.xml.mapper.RentRequestDtoMapper;
import com.xml.model.AdditionalBill;
import com.xml.model.RentRequest;
import com.xml.model.Report;
import com.xml.repository.AdditionalBillRepository;
import com.xml.repository.ReportRepository;
import com.xml.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private RentRequestDtoMapper rentRequestDtoMapper;

    @Autowired
    private AdditionalBillRepository additionalBillRepository;

    @Override
    public void createReport(ReportDto reportDto) throws ParseException {
        Report report = new Report();
        RentRequest request = new RentRequest();
        request.setId(reportDto.getRentRequest().getId());
        request.setCustomerId(reportDto.getRentRequest().getCustomer().getId());
        request.setReservedFrom(reportDto.getRentRequest().getReservedFrom());
        request.setReservedTo(reportDto.getRentRequest().getReservedTo());
        request.setRentRequestStatus(reportDto.getRentRequest().getRentRequestStatus());

        report.setRentRequest(request);
        report.setAdditionalInformation(reportDto.getAdditionalInformation());
        report.setCarId(reportDto.getCar().getId());
        report.setKm(reportDto.getKm());

        this.reportRepository.save(report);

        if (reportDto.getKm() > reportDto.getCar().getAllowedDistance()) {
            AdditionalBill additionalBill = new AdditionalBill();
            additionalBill.setCustomerId(reportDto.getRentRequest().getCustomer().getId());
            AdvertisementDto advertisementDto = null;
            for (AdvertisementDto advertisementDto1 : reportDto.getRentRequest().getAdvertisementsForRent()) {
                if (advertisementDto1.getCar().getId().equals(reportDto.getCar().getId())) {
                    advertisementDto = advertisementDto1;
                    break;
                }
            }
            float distance = reportDto.getKm() - reportDto.getCar().getAllowedDistance();
            float price = advertisementDto.getPricelist().getPricePerKm();
            additionalBill.setPrice(distance * price);
            this.additionalBillRepository.save(additionalBill);
        }
    }


}
