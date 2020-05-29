package com.xml.dto;

public class CodebookInfoDto {

    private CarBrandDto carBrandDto;
    private CarModelDto carModelDto;
    private CarClassDto carClassDto;
    private FuelTypeDto fuelTypeDto;
    private TransmissionTypeDto transmissionTypeDto;
    private PricelistDto pricelistDto;

    public CodebookInfoDto() {
    }

    public CarBrandDto getCarBrandDto() {
        return carBrandDto;
    }

    public void setCarBrandDto(CarBrandDto carBrandDto) {
        this.carBrandDto = carBrandDto;
    }

    public CarModelDto getCarModelDto() {
        return carModelDto;
    }

    public void setCarModelDto(CarModelDto carModelDto) {
        this.carModelDto = carModelDto;
    }

    public CarClassDto getCarClassDto() {
        return carClassDto;
    }

    public void setCarClassDto(CarClassDto carClassDto) {
        this.carClassDto = carClassDto;
    }

    public FuelTypeDto getFuelTypeDto() {
        return fuelTypeDto;
    }

    public void setFuelTypeDto(FuelTypeDto fuelTypeDto) {
        this.fuelTypeDto = fuelTypeDto;
    }

    public TransmissionTypeDto getTransmissionTypeDto() {
        return transmissionTypeDto;
    }

    public void setTransmissionTypeDto(TransmissionTypeDto transmissionTypeDto) {
        this.transmissionTypeDto = transmissionTypeDto;
    }

    public PricelistDto getPricelistDto() {
        return pricelistDto;
    }

    public void setPricelistDto(PricelistDto pricelistDto) {
        this.pricelistDto = pricelistDto;
    }
}
