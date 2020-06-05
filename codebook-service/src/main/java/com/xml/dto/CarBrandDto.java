package com.xml.dto;

public class CarBrandDto {

    private Long id;
    private String name;

    public CarBrandDto() {

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
        return "CarBrandDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
