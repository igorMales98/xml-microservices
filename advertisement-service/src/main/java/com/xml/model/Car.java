package com.xml.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.Set;


@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Car", namespace = "http://localhost:8085/advertisement-service")
@XmlRootElement(name = "carClass")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement
    private Long id;

    @Column
    @XmlElement
    private Long carBrandId;

    @Column
    @XmlElement
    private Long carModelId;

    @Column
    @XmlElement
    private Long fuelTypeId;

    @Column
    @XmlElement
    private Long transmissionTypeId;

    @Column
    @XmlElement
    private Long carClassId;

    @Column(nullable = false)
    @Range(min = 0, max = 1000000)
    @XmlElement
    private float mileage;

    @Column(nullable = false)
    @XmlElement
    private float allowedDistance = 1000000;

    @Column(nullable = false)
    @XmlElement
    private boolean collisionDamageWaiverExists = false;

    @Column(nullable = false)
    @Range(min = 0, max = 9)
    @XmlElement
    private int childSeats = 0;

    @Column
    @XmlElement
    private int timesRated = 0;

    @Column
    @XmlElement
    private float averageRating = 0;

    @Column
    @XmlElement
    private boolean hasAndroid = false;

    @Column
    private String androidToken;

    public Car() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarBrandId() {
        return carBrandId;
    }

    public void setCarBrandId(Long carBrandId) {
        this.carBrandId = carBrandId;
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

    public Long getCarClassId() {
        return carClassId;
    }

    public void setCarClassId(Long carClassId) {
        this.carClassId = carClassId;
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

    public Long getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(Long carModelId) {
        this.carModelId = carModelId;
    }

    public String getAndroidToken() {
        return androidToken;
    }

    public void setAndroidToken(String androidToken) {
        this.androidToken = androidToken;
    }
}
