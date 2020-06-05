package com.xml.service.impl;

import com.xml.model.CarModel;
import com.xml.repository.CarModelRepository;
import com.xml.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarModelServiceImpl implements CarModelService {

    @Autowired
    private CarModelRepository carModelRepository;

    @Override
    public CarModel findById(Long id) {
        return this.carModelRepository.getOne(id);
    }

    @Override
    public List<CarModel> getAll() {
        return this.carModelRepository.findAll();
    }

    @Override
    public List<CarModel> getBrandModels(Long brandId) {
        return this.carModelRepository.getByCarBrandId(brandId);
    }
}
