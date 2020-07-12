package com.xml.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ReportDto {

    private Long id;
    private CarDto car;
    private float km;
    private String additionalInformation;
    private RentRequestDto rentRequest;
    private Long carId;


    public ReportDto() {
    }

    public ReportDto(Long carId)
    {
        this.carId = carId;
    }


    public ReportDto(Long id, CarDto car, float km, String additionalInformation, RentRequestDto rentRequest) {
        this.id = id;
        this.car = car;
        this.km = km;
        this.additionalInformation = additionalInformation;
        this.rentRequest = rentRequest;

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

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }
}
