package com.xml.dto;

import com.xml.model.AdditionalBill;

import java.util.Set;

public class CustomerDto extends UserDto {

    private short advertisementsPosted;
    private Set<AdditionalBill> additionalBills;

    public CustomerDto() {
    }

    public short getAdvertisementsPosted() {
        return advertisementsPosted;
    }

    public void setAdvertisementsPosted(short advertisementsPosted) {
        this.advertisementsPosted = advertisementsPosted;
    }

    public Set<AdditionalBill> getAdditionalBills() {
        return additionalBills;
    }

    public void setAdditionalBills(Set<AdditionalBill> additionalBills) {
        this.additionalBills = additionalBills;
    }
}

