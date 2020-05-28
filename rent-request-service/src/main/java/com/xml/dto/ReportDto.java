package com.xml.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ReportDto {

    private Long id;
    private Long carId;
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

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
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
