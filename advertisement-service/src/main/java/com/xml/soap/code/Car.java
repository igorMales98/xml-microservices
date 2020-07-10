//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.07.01 at 06:39:45 PM CEST 
//


package com.xml.soap.code;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for car complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="car"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="carBrandId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="carModelId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="carClassId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="transmissionTypeId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="fuelTypeId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="collisionDamageWaiverExists" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="hasAndroid" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="mileage" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/&gt;
 *         &lt;element name="allowedDistance" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/&gt;
 *         &lt;element name="averageRating" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/&gt;
 *         &lt;element name="timesRated" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "car", propOrder = {
    "id",
    "carBrandId",
    "carModelId",
    "carClassId",
    "transmissionTypeId",
    "fuelTypeId",
    "collisionDamageWaiverExists",
    "hasAndroid",
    "mileage",
    "allowedDistance",
    "averageRating",
    "timesRated"
})
public class Car {

    protected Long id;
    protected Long carBrandId;
    protected Long carModelId;
    protected Long carClassId;
    protected Long transmissionTypeId;
    protected Long fuelTypeId;
    protected Boolean collisionDamageWaiverExists;
    protected Boolean hasAndroid;
    protected Float mileage;
    protected Float allowedDistance;
    protected Float averageRating;
    protected Integer timesRated;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the carBrandId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCarBrandId() {
        return carBrandId;
    }

    /**
     * Sets the value of the carBrandId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCarBrandId(Long value) {
        this.carBrandId = value;
    }

    /**
     * Gets the value of the carModelId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCarModelId() {
        return carModelId;
    }

    /**
     * Sets the value of the carModelId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCarModelId(Long value) {
        this.carModelId = value;
    }

    /**
     * Gets the value of the carClassId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCarClassId() {
        return carClassId;
    }

    /**
     * Sets the value of the carClassId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCarClassId(Long value) {
        this.carClassId = value;
    }

    /**
     * Gets the value of the transmissionTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTransmissionTypeId() {
        return transmissionTypeId;
    }

    /**
     * Sets the value of the transmissionTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTransmissionTypeId(Long value) {
        this.transmissionTypeId = value;
    }

    /**
     * Gets the value of the fuelTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFuelTypeId() {
        return fuelTypeId;
    }

    /**
     * Sets the value of the fuelTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFuelTypeId(Long value) {
        this.fuelTypeId = value;
    }

    /**
     * Gets the value of the collisionDamageWaiverExists property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCollisionDamageWaiverExists() {
        return collisionDamageWaiverExists;
    }

    /**
     * Sets the value of the collisionDamageWaiverExists property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCollisionDamageWaiverExists(Boolean value) {
        this.collisionDamageWaiverExists = value;
    }

    /**
     * Gets the value of the hasAndroid property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHasAndroid() {
        return hasAndroid;
    }

    /**
     * Sets the value of the hasAndroid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHasAndroid(Boolean value) {
        this.hasAndroid = value;
    }

    /**
     * Gets the value of the mileage property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getMileage() {
        return mileage;
    }

    /**
     * Sets the value of the mileage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setMileage(Float value) {
        this.mileage = value;
    }

    /**
     * Gets the value of the allowedDistance property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getAllowedDistance() {
        return allowedDistance;
    }

    /**
     * Sets the value of the allowedDistance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setAllowedDistance(Float value) {
        this.allowedDistance = value;
    }

    /**
     * Gets the value of the averageRating property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getAverageRating() {
        return averageRating;
    }

    /**
     * Sets the value of the averageRating property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setAverageRating(Float value) {
        this.averageRating = value;
    }

    /**
     * Gets the value of the timesRated property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTimesRated() {
        return timesRated;
    }

    /**
     * Sets the value of the timesRated property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTimesRated(Integer value) {
        this.timesRated = value;
    }

}
