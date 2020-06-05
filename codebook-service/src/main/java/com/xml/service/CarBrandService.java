package com.xml.service;

import com.xml.dto.CarBrandDto;
import com.xml.dto.PricelistDto;
import com.xml.model.CarBrand;
import com.xml.model.Pricelist;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CarBrandService {

    CarBrand findById(Long id);

    List<CarBrand> getAll();

    void saveCarBrand(CarBrandDto carBrandDto);
    void deleteCarBrand(Long id);
    void editCarBrand(CarBrandDto carBrandDto);
}
