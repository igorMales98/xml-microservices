package com.xml.dto;

public class CodebookMQDto {
    private  Long advertisementId;
    private Long carBrandId;
    private Long carModelId;
    private Long carClassId;
    private Long fuelTypeId;
    private Long transmissionTypeId;
    private Long pricalistId;

    public CodebookMQDto() {
    }

    public CodebookMQDto(Long advertisementId, Long carBrandId, Long carModelId, Long carClassId, Long fuelTypeId, Long transmissionTypeId, Long pricalistId) {
        this.advertisementId = advertisementId;
        this.carBrandId = carBrandId;
        this.carModelId = carModelId;
        this.carClassId = carClassId;
        this.fuelTypeId = fuelTypeId;
        this.transmissionTypeId = transmissionTypeId;
        this.pricalistId = pricalistId;
    }

    public Long getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(Long advertisementId) {
        this.advertisementId = advertisementId;
    }

    public Long getCarBrandId() {
        return carBrandId;
    }

    public void setCarBrandId(Long carBrandId) {
        this.carBrandId = carBrandId;
    }

    public Long getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(Long carModelId) {
        this.carModelId = carModelId;
    }

    public Long getCarClassId() {
        return carClassId;
    }

    public void setCarClassId(Long carClassId) {
        this.carClassId = carClassId;
    }

    public Long getFuelTypeId() {
        return fuelTypeId;
    }

    public void setFuelTypeId(Long fuelTypeId) {
        this.fuelTypeId = fuelTypeId;
    }

    public Long getTransmissionTypeId() {
        return transmissionTypeId;
    }

    public void setTransmissionTypeId(Long transmissionTypeId) {
        this.transmissionTypeId = transmissionTypeId;
    }

    public Long getPricalistId() {
        return pricalistId;
    }

    public void setPricalistId(Long pricalistId) {
        this.pricalistId = pricalistId;
    }
}
