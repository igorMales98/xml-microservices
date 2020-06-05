package com.xml.service;

import com.xml.dto.FuelTypeDto;
import com.xml.model.FuelType;

import java.util.List;

public interface FuelTypeService {

    FuelType findById(Long id);

    List<FuelType> getAll();

    void saveFuelType(FuelTypeDto fuelTypeDto);
    void deleteFuelType(Long id);
    void editFuelType(FuelTypeDto fuelTypeDto);
}
