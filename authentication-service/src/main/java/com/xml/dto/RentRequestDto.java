package com.xml.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xml.enummeration.RentRequestStatus;
import com.xml.model.Advertisement;

import java.time.LocalDateTime;
import java.util.Set;

public class RentRequestDto {

    private Long id;
    private LocalDateTime reservedFrom;
    private LocalDateTime reservedTo;
    private Set<AdvertisementDto> advertisementsForRent;
    private RentRequestStatus rentRequestStatus;
    private UserDto customer;
    private Set<ReportDto> reports;

    public RentRequestDto() {
    }

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

    public Set<AdvertisementDto> getAdvertisementsForRent() {
        return advertisementsForRent;
    }

    public void setAdvertisementsForRent(Set<AdvertisementDto> advertisementsForRent) {
        this.advertisementsForRent = advertisementsForRent;
    }

    public RentRequestStatus getRentRequestStatus() {
        return rentRequestStatus;
    }

    public void setRentRequestStatus(RentRequestStatus rentRequestStatus) {
        this.rentRequestStatus = rentRequestStatus;
    }

    public UserDto getCustomer() {
        return customer;
    }

    public void setCustomer(UserDto customer) {
        this.customer = customer;
    }

    public Set<ReportDto> getReports() {
        return reports;
    }

    public void setReports(Set<ReportDto> reports) {
        this.reports = reports;
    }
}
