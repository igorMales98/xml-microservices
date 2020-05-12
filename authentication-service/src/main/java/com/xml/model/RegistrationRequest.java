package com.xml.model;

import javax.persistence.*;

@Entity
public class RegistrationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    protected String username;

    @Column(nullable = false)
    protected String password;

    @Column(nullable = false)
    protected String firstName;

    @Column(nullable = false)
    protected String lastName;

    @Column(nullable = false)
    protected String country;

    @Column(nullable = false)
    protected String city;

    @Column(nullable = false)
    protected String email;

    @Column(nullable = false)
    protected String phone;

}
