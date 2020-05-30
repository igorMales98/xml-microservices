package com.xml.service;

import com.xml.model.CarBrand;

import java.util.List;

public interface CarBrandService {

    CarBrand findById(Long id);

    List<CarBrand> getAll();
}
