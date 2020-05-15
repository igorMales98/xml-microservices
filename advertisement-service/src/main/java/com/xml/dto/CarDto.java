package com.xml.dto;

import com.xml.model.*;

public class CarDto {

    private Long id;
    private CarBrand carBrand;
    private CarModel carModel;
    private FuelType fuelType;
    private TransmissionType transmissionType;
    private CarClass carClass;
    private float mileage;
    private float allowedDistance;
    private boolean collisionDamageWaiverExists;
    private int childSeats;
    private int timesRated;
    private float averageRating;
    private boolean hasAndroid;

    public CarDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarBrand getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(CarBrand carBrand) {
        this.carBrand = carBrand;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public CarClass getCarClass() {
        return carClass;
    }

    public void setCarClass(CarClass carClass) {
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
}
