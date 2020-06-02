package com.xml.dto;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PricelistDto {

    private Long id;

    @NotNull(message = "Price per day cannot be empty")
    @Min(value = 1)
    @Max(value = 999999)
    private Float pricePerDay;

    @NotNull(message = "Price per km cannot be empty")
    @Min(value = 1)
    @Max(value = 999999)
    private Float pricePerKm;

    @NotNull(message = "Price for cdw cannot be empty")
    @Min(value = 1)
    @Max(value = 999999)
    private Float priceForCDW;

    public PricelistDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Float pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public Float getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(Float pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

    public Float getPriceForCDW() {
        return priceForCDW;
    }

    public void setPriceForCDW(Float priceForCDW) {
        this.priceForCDW = priceForCDW;
    }
}
