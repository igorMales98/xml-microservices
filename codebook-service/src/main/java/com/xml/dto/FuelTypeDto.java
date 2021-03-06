package com.xml.dto;

public class FuelTypeDto {

    private Long id;
    private String name;

    public FuelTypeDto() {
    }

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

    @Override
    public String toString() {
        return "FuelTypeDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
