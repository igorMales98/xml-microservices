package com.xml.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@DiscriminatorValue("CUSTOMER")
public class Customer extends User {

    public Customer() {}

    public Customer(String username, String password, String firstName, String lastName, String country, String city, String address, String email, String phone) {
        super(username, password, firstName, lastName, country, city, address, email, phone);
    }
}
