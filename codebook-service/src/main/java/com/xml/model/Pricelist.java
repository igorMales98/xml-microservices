package com.xml.model;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.Set;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Pricelist", namespace = "http://localhost:8084/codebook-service-schema")
@XmlRootElement(name = "pricelistClass")
public class Pricelist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement
    private Long id;

    @Column
    @XmlElement
    private Float pricePerDay;

    @Column
    @XmlElement
    private Float pricePerKm;

    @Column
    @XmlElement
    private Float priceForCDW;

    @Column
    private boolean deleted;

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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}
