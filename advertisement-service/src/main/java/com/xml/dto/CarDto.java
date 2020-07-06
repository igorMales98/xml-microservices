package com.xml.dto;

public class CarDto {

    private Long id;
    private CarBrandDto carBrand;
    private CarModelDto carModel;
    private FuelTypeDto fuelType;
    private TransmissionTypeDto transmissionType;
    private CarClassDto carClass;
    private float mileage;
    private float allowedDistance;
    private boolean collisionDamageWaiverExists;
    private int childSeats;
    private int timesRated;
    private float averageRating;
    private boolean hasAndroid;
    private int timesRented;
    private float rentMileage;

    public CarDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public CarClassDto getCarClass() {
        return carClass;
    }

    public void setCarClass(CarClassDto carClass) {
        this.carClass = carClass;
    }

    public float getMileage() {
        return mileage;
    }

    public void setMileage(float mileage) {
        this.mileage = mileage;
    }

    public float getAllowedDistance() {
        return allowedDistance;
    }

    public void setAllowedDistance(float allowedDistance) {
        this.allowedDistance = allowedDistance;
    }

    public boolean isCollisionDamageWaiverExists() {
        return collisionDamageWaiverExists;
    }

    public void setCollisionDamageWaiverExists(boolean collisionDamageWaiverExists) {
        this.collisionDamageWaiverExists = collisionDamageWaiverExists;
    }

    public int getChildSeats() {
        return childSeats;
    }

    public void setChildSeats(int childSeats) {
        this.childSeats = childSeats;
    }

    public int getTimesRated() {
        return timesRated;
    }

    public void setTimesRated(int timesRated) {
        this.timesRated = timesRated;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public boolean isHasAndroid() {
        return hasAndroid;
    }

    public void setHasAndroid(boolean hasAndroid) {
        this.hasAndroid = hasAndroid;
    }

    public int getTimesRented() {
        return timesRented;
    }

    public void setTimesRented(int timesRented) {
        this.timesRented = timesRented;
    }

    public float getRentMileage() {
        return rentMileage;
    }

    public void setRentMileage(float rentMileage) {
        this.rentMileage = rentMileage;
    }
}
