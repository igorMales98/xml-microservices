package com.xml.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("FIRM")
public class Firm extends User {

    @Column
    private String firmName;

    @Column
    private String businessSocialNumber;
}
