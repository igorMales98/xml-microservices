package com.xml.service;

import com.xml.model.FuelType;

import java.util.List;

public interface FuelTypeService {

    FuelType findById(Long id);

    List<FuelType> getAll();
}
