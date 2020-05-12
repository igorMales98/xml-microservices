package com.xml.model;

import javax.persistence.*;

@Entity
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean canRent;

    @Column
    private boolean canAdvertise;

}
