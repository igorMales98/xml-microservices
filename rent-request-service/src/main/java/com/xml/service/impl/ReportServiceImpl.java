package com.xml.service.impl;

import com.xml.dto.AdvertisementDto;
import com.xml.dto.ReportDto;
import com.xml.feignClients.AdvertisementFeignClient;
import com.xml.mapper.ReportDtoMapper;
import com.xml.model.AdditionalBill;
import com.xml.model.Car;
import com.xml.model.Report;
import com.xml.repository.AdditionalBillRepository;
import com.xml.repository.ReportRepository;
import com.xml.service.CarService;
import com.xml.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ReportDtoMapper reportDtoMapper;

    @Autowired
    private CarService carService;

    //private UserDtoMapper userDtoMapper; //nisam siguran za ovo

    @Autowired
    private AdvertisementFeignClient advertisementFeignClient;

    @Autowired
    private AdditionalBillRepository additionalBillRepository;

    @Override
    public void createReport(ReportDto reportDto) throws ParseException {
        Car car = this.carService.getOne(reportDto.getCar().getId()); //treba dobaviti car, ovo nije dobro, proveriti pom.xml (dodat je dependensi)
        car.setMileage(car.getMileage() + reportDto.getKm());
        this.carService.save(car);
        this.reportRepository.save(reportDtoMapper.toEntity(reportDto));

        if (reportDto.getKm() > car.getAllowedDistance()) {
            AdditionalBill additionalBill = new AdditionalBill();
           // additionalBill.setCustomer(userDtoMapper.toEntity(reportDto.getRentRequest().getCustomer())); //treba ispraviti
            AdvertisementDto advertisementDto = null;
            for (AdvertisementDto advertisementDto1 : reportDto.getRentRequest().getAdvertisementsForRent()) {
                if (advertisementDto1.getCar().getId().equals(car.getId())) {
                    advertisementDto = advertisementDto1;
                    break;
                }
            }
            float distance = reportDto.getKm() - car.getAllowedDistance();
            float price = advertisementDto.getPricelist().getPricePerKm();
            additionalBill.setPrice(distance * price);
            this.additionalBillRepository.save(additionalBill);
        }
    }

    @Override
    public float getRentMileage(Long carId) {
        List<Report> reports = this.reportRepository.getAllReportsForACar(carId);
        float milege = 0;
        for (Report report : reports) {
            milege += report.getKm();
        }
        return milege;
    }
}
