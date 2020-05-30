package com.xml.service;

import com.xml.model.CarModel;

import java.util.List;

public interface CarModelService {

    CarModel findById(Long id);

    List<CarModel> getAll();

    List<CarModel> getBrandModels(Long brandId);
}
