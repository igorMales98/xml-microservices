package com.xml.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xml.enummeration.RentRequestStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class RentRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime reservedFrom;

    @Column(nullable = false)
    private LocalDateTime reservedTo;

   // @ManyToMany
   // @JoinTable(name = "rented_advertisements", joinColumns = @JoinColumn(name = "rent_request_id", referencedColumnName = "id"),
   //        inverseJoinColumns = @JoinColumn(name = "advertisement_id", referencedColumnName = "id"))
    @Column
    private Set<Long> advertisementsForRent;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RentRequestStatus rentRequestStatus;

    @Column(nullable = false)
    private Long customerId;

    @JsonIgnore
    @OneToMany(mappedBy = "rentRequest")
    private Set<Report> reports;

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

    public Set<Long> getAdvertisementsForRent() {
        return advertisementsForRent;
    }

    public void setAdvertisementsForRent(Set<Long> advertisementsForRent) {
        this.advertisementsForRent = advertisementsForRent;
    }

    public RentRequestStatus getRentRequestStatus() {
        return rentRequestStatus;
    }

    public void setRentRequestStatus(RentRequestStatus rentRequestStatus) {
        this.rentRequestStatus = rentRequestStatus;
    }

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
}
