package com.xml.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "car")
    private Set<Report> reports;

    @ManyToOne
    @JoinColumn(name = "car_brand_id", nullable = false)
    private CarBrand carBrand;

    @ManyToOne
    @JoinColumn(name = "car_model_id", nullable = false)
    private CarModel carModel;

    @ManyToOne
    @JoinColumn(name = "fuel_type_id", nullable = false)
    private FuelType fuelType;

    @ManyToOne
    @JoinColumn(name = "transmission_type_id", nullable = false)
    private TransmissionType transmissionType;

    @ManyToOne
    @JoinColumn(name = "car_class_id", nullable = false)
    private CarClass carClass;

    @Column(nullable = false)
    @Range(min = 0, max = 1000000)
    private float mileage;

    @Column(nullable = false)
    private float allowedDistance = 1000000;

    @Column(nullable = false)
    private boolean collisionDamageWaiverExists = false;

    @Column(nullable = false)
    @Range(min = 0, max = 9)
    private int childSeats = 0;

    @Column
    private int timesRated = 0;

    @Column
    private float averageRating = 0;

    @Column
    private boolean hasAndroid = false;

    public Car() {
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

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }
}
