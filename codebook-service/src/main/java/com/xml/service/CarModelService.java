package com.xml.service;

import com.xml.dto.CarModelDto;
import com.xml.model.CarModel;

import java.text.ParseException;
import java.util.List;

public interface CarModelService {

    CarModel findById(Long id);

    List<CarModel> getAll();

    List<CarModel> getBrandModels(Long brandId);

    void saveCarModel(CarModelDto carModelDto) throws ParseException;
    void deleteCarModel(Long id);
    void editCarModel(CarModelDto carModelDto);
}
