package com.xml.dto;

import com.xml.model.CarBrand;

public class CarModelDto {

    private Long id;
    private String name;
    private CarBrandDto carBrand;

    public CarModelDto() {
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

    public CarBrandDto getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(CarBrandDto carBrand) {
        this.carBrand = carBrand;
    }

    @Override
    public String toString() {
        return "CarModelDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", carBrandDto=" + carBrand +
                '}';
    }
}
