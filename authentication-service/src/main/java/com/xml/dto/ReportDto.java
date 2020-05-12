package com.xml.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xml.model.Advertisement;
import com.xml.model.RentRequest;

public class ReportDto {

    private Long id;
    private CarDto car;
    private float km;
    private String additionalInformation;
    private RentRequestDto rentRequest;

    public ReportDto() {
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

    @JsonIgnore
    public RentRequestDto getRentRequest() {
        return rentRequest;
    }

    public void setRentRequest(RentRequestDto rentRequest) {
        this.rentRequest = rentRequest;
    }
}
