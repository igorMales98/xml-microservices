package com.xml.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("AGENT")
public class Agent extends User {

    @Column
    private String businessSocialNumber;
}
