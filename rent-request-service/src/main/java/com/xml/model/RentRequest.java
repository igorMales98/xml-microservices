package com.xml.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xml.adapter.LocalDateAdapter;
import com.xml.enummeration.RentRequestStatus;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RentRequest", namespace = "http://localhost:8089/rent-request-service-schema")
@XmlRootElement(name = "rentRequestClass")
public class RentRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement
    private Long id;

    @Column(nullable = false)
    @XmlElement
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDateTime reservedFrom;

    @Column(nullable = false)
    @XmlElement
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDateTime reservedTo;

    @ElementCollection
    @Column(name = "advertisement_id")
    @CollectionTable(name = "rented_advertisements", joinColumns = @JoinColumn(name = "rent_request_id"))
    @XmlElement
    private Set<Long> advertisementsForRent;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @XmlElement
    private RentRequestStatus rentRequestStatus;

    @Column(nullable = false)
    @XmlElement
    private Long customerId;

    @JsonIgnore
    @OneToMany(mappedBy = "rentRequest")
    @XmlElement
    private Set<Report> reports;

    @Column(nullable = false)
    @XmlElement
    LocalDateTime created;

    @Column(nullable = false)
    @XmlElement
    Long advertiserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getReservedFrom() {
        return reservedFrom;
    }

    public void setReservedFrom(LocalDateTime reservedFrom) {
        this.reservedFrom = reservedFrom;
    }

    public LocalDateTime getReservedTo() {
        return reservedTo;
    }

    public void setReservedTo(LocalDateTime reservedTo) {
        this.reservedTo = reservedTo;
    }

    public RentRequestStatus getRentRequestStatus() {
        return rentRequestStatus;
    }

    public void setRentRequestStatus(RentRequestStatus rentRequestStatus) { this.rentRequestStatus = rentRequestStatus; }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Set<Report> getReports() {
        return reports;
    }

    public void setReports(Set<Report> reports) {
        this.reports = reports;
    }

    public Set<Long> getAdvertisementsForRent() { return advertisementsForRent; }

    public void setAdvertisementsForRent(Set<Long> advertisementsForRent) { this.advertisementsForRent = advertisementsForRent; }

    public LocalDateTime getCreated() { return created; }

    public void setCreated(LocalDateTime created) { this.created = created; }

    public Long getAdvertiserId() { return advertiserId; }

    public void setAdvertiserId(Long advertiserId) { this.advertiserId = advertiserId; }
}
