package com.xml.model;

import com.xml.adapter.LocalDateAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Advertisement", namespace = "http://localhost:8085/advertisement-service-schema")
@XmlRootElement(name = "advertisementClass")
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    @XmlElement
    private Car car;

    @Column
    @XmlElement
    private Long advertiserId;

    @Column
    @XmlElement
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDateTime availableFrom;

    @Column
    @XmlElement
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDateTime availableTo;

    @OneToMany(mappedBy = "advertisement")
    @XmlElement
    private Set<Comment> comments;

    @Column
    @XmlElement
    private Long pricelistId;

    @Column(nullable = false)
    private boolean valid;

    @ElementCollection
    @MapKeyColumn(name="days")
    @Column(name="discount")
    @CollectionTable(name="advertisement_discount", joinColumns=@JoinColumn(name="advertisement_id"))
    @XmlElement
    private Map<String, String> discount;

    public Advertisement() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Long getAdvertiserId() {
        return advertiserId;
    }

    public void setAdvertiserId(Long advertiserId) {
        this.advertiserId = advertiserId;
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

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Long getPricelistId() {
        return pricelistId;
    }

    public void setPricelistId(Long pricelistId) {
        this.pricelistId = pricelistId;
    }

    public Map<String, String> getDiscount() {
        return discount;
    }

    public void setDiscount(Map<String, String> discount) {
        this.discount = discount;
    }

    public boolean getValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
