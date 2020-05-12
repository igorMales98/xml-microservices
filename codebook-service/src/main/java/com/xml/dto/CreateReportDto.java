package com.xml.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CreateReportDto {

    private Long id;
    private CarDto car;
    private float km;
    private String additionalInformation;
    private RentRequestDto rentRequest;

    public CreateReportDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }

    public float getKm() {
        return km;
    }

    public void setKm(float km) {
        this.km = km;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public RentRequestDto getRentRequest() {
        return rentRequest;
    }

    public void setRentRequest(RentRequestDto rentRequest) {
        this.rentRequest = rentRequest;
    }
}
