package com.xml.dto;

import com.xml.enummeration.RentRequestStatus;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

public class RentRequestDto {

    private Long id;

    @NotNull(message = "Date reserved from cannot be empty")
    private LocalDateTime reservedFrom;

    @NotNull(message = "Date reserved to cannot be empty")
    private LocalDateTime reservedTo;

    @NotNull
    private Set<AdvertisementDto> advertisementsForRent;

    private RentRequestStatus rentRequestStatus;

    @NotNull
    private UserDto customer;

    private Set<ReportDto> reports;

    private boolean bundle;

    private boolean physicalRent;

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

    public boolean isBundle() {
        return bundle;
    }

    public void setBundle(boolean bundle) {
        this.bundle = bundle;
    }

    public boolean isPhysicalRent() {
        return physicalRent;
    }

    public void setPhysicalRent(boolean physicalRent) {
        this.physicalRent = physicalRent;
    }

    @Override
    public String toString() {
        return "RentRequestDto{" +
                "id=" + id +
                ", reservedFrom=" + reservedFrom +
                ", reservedTo=" + reservedTo +
                ", advertisementsForRent=" + advertisementsForRent +
                ", rentRequestStatus=" + rentRequestStatus +
                ", customer=" + customer +
                ", reports=" + reports +
                ", bundle=" + bundle +
                ", physicalRent=" + physicalRent +
                '}';
    }
}
