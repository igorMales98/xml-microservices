package com.xml.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

@Entity
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @Column
    private Long advertiserId;

    @Column
    private LocalDateTime availableFrom;

    @Column
    private LocalDateTime availableTo;

    @OneToMany(mappedBy = "advertisement")
    private Set<Comment> comments;

    @Column
    private Long pricelistId;

    @ElementCollection
    @MapKeyColumn(name="days")
    @Column(name="discount")
    @CollectionTable(name="advertisement_discount", joinColumns=@JoinColumn(name="advertisement_id"))
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
}
