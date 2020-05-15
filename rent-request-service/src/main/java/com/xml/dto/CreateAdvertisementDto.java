package com.xml.dto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class CreateAdvertisementDto {

    private CarBrandDto carBrand;
    private CarModelDto carModel;
    private CarClassDto carClass;
    private FuelTypeDto fuelType;
    private TransmissionTypeDto transmissionType;
    private PricelistDto pricelist;
    private LocalDateTime availableFrom;
    private LocalDateTime availableTo;
    private float mileage;
    private int childSeats;
    private boolean hasACDW;
    private float allowedDistance;
    private String discount;

    public CreateAdvertisementDto() {
    }

    public CarBrandDto getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(CarBrandDto carBrand) {
        this.carBrand = carBrand;
    }

    public CarModelDto getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModelDto carModel) {
        this.carModel = carModel;
    }

    public CarClassDto getCarClass() {
        return carClass;
    }

    public void setCarClass(CarClassDto carClass) {
        this.carClass = carClass;
    }

    public FuelTypeDto getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelTypeDto fuelType) {
        this.fuelType = fuelType;
    }

    public TransmissionTypeDto getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionTypeDto transmissionType) {
        this.transmissionType = transmissionType;
    }

    public PricelistDto getPricelist() {
        return pricelist;
    }

    public void setPricelist(PricelistDto pricelist) {
        this.pricelist = pricelist;
    }

    public LocalDateTime getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(LocalDateTime availableFrom) {
        this.availableFrom = availableFrom;
    }

    public LocalDateTime getAvailableTo() {
        return availableTo;
    }

    public void setAvailableTo(LocalDateTime availableTo) {
        this.availableTo = availableTo;
    }

    public float getMileage() {
        return mileage;
    }

    public void setMileage(float mileage) {
        this.mileage = mileage;
    }

    public int getChildSeats() {
        return childSeats;
    }

    public void setChildSeats(int childSeats) {
        this.childSeats = childSeats;
    }

    public boolean isHasACDW() {
        return hasACDW;
    }

    public void setHasACDW(boolean hasACDW) {
        this.hasACDW = hasACDW;
    }

    public float getAllowedDistance() {
        return allowedDistance;
    }

    public void setAllowedDistance(float allowedDistance) {
        this.allowedDistance = allowedDistance;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "CreateAdvertisementDto{" +
                "carBrand=" + carBrand +
                ", carModel=" + carModel +
                ", carClass=" + carClass +
                ", fuelType=" + fuelType +
                ", transmissionType=" + transmissionType +
                ", pricelist=" + pricelist +
                ", availableFrom=" + availableFrom +
                ", availableTo=" + availableTo +
                ", mileage=" + mileage +
                ", childSeats=" + childSeats +
                ", hasACDW=" + hasACDW +
                ", allowedDistance=" + allowedDistance +
                ", discount=" + discount +
                '}';
    }

    public Map<String, String> convertToHashMap(String discounts) {
        Map<String, String> temp = new HashMap<>();
        String[] discountsList = discounts.split(":");
        try {
            for (String discount : discountsList) {
                temp.put(discount.split("\\?")[0], discount.split("\\?")[1]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return temp;
        }
        return temp;
    }
}

