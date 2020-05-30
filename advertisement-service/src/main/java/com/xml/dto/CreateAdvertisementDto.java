package com.xml.dto;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class CreateAdvertisementDto {

    @NotNull(message = "Car brand cannot be null")
    private CarBrandDto carBrand;

    @NotNull(message = "Car model cannot be null")
    private CarModelDto carModel;

    @NotNull(message = "Car class cannot be null")
    private CarClassDto carClass;

    @NotNull(message = "Fuel type cannot be null")
    private FuelTypeDto fuelType;

    @NotNull(message = "Transmission type cannot be null")
    private TransmissionTypeDto transmissionType;

    @NotNull(message = "Pricelist cannot be null")
    private PricelistDto pricelist;

    @NotNull
    @FutureOrPresent(message = "Date cannot be in past")
    private LocalDateTime availableFrom;

    @NotNull
    @FutureOrPresent(message = "Date cannot be in past")
    private LocalDateTime availableTo;

    @PositiveOrZero(message = "Mileage cannot be negative")
    @Max(value = 1000000000)
    @NotNull(message = "Mileage cannot be empty")
    private float mileage;

    @NotNull(message = "Child seats cannot be empty")
    @Min(value = 0)
    @Max(value = 5)
    private int childSeats;

    @NotNull
    private boolean hasACDW;

    @NotNull(message = "Mileage cannot be empty")
    @Min(value = 0)
    @Max(value = 1000000)
    private float allowedDistance;

    private String discount;

    private Long advertiserId;

    private String userRole;

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

    public Long getAdvertiserId() {
        return advertiserId;
    }

    public void setAdvertiserId(Long advertiserId) {
        this.advertiserId = advertiserId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
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
                ", discount='" + discount + '\'' +
                ", advertiserId=" + advertiserId +
                ", userRole='" + userRole + '\'' +
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

