package com.xml.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("AGENT")
public class Agent extends User {

    @Column
    private String businessSocialNumber;

    public Agent() {}

    public Agent(String username, String password, String firstName, String lastName, String country, String city, String address, String email, String phone, String businessSocialNumber) {
        super(username, password, firstName, lastName, country, city, address, email, phone);
        this.businessSocialNumber = businessSocialNumber;
    }
}
