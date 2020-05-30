package com.xml.service.impl;

import com.xml.model.CarBrand;
import com.xml.repository.CarBrandRepository;
import com.xml.service.CarBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarBrandServiceImpl implements CarBrandService {

    @Autowired
    private CarBrandRepository carBrandRepository;

    @Override
    public CarBrand findById(Long id) {
        return this.carBrandRepository.getOne(id);
    }

    @Override
    public List<CarBrand> getAll() {
        return this.carBrandRepository.findAll();
    }
}
