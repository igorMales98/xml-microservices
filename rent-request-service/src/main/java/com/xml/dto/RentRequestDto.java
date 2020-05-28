package com.xml.dto;

import com.xml.enummeration.RentRequestStatus;

import java.time.LocalDateTime;
import java.util.Set;

public class RentRequestDto {

    private Long id;
    private LocalDateTime reservedFrom;
    private LocalDateTime reservedTo;
    private Set<Long> advertisementsForRent;
    private RentRequestStatus rentRequestStatus;
    private Long customerId;
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

    public Set<ReportDto> getReports() {
        return reports;
    }

    public void setReports(Set<ReportDto> reports) {
        this.reports = reports;
    }
}
