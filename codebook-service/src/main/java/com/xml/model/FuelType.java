package com.xml.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.Set;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Advertisement", namespace = "http://localhost:8084/codebook-service")
@XmlRootElement(name = "codebookClass")
public class FuelType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement
    private Long id;

    @Column(nullable = false, unique = true)
    @XmlElement
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
