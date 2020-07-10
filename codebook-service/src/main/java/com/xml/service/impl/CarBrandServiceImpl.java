package com.xml.service.impl;

import com.xml.dto.CarBrandDto;
import com.xml.mapper.CarBrandDtoMapper;
import com.xml.model.CarBrand;
import com.xml.model.CarClass;
import com.xml.repository.CarBrandRepository;
import com.xml.service.CarBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarBrandServiceImpl implements CarBrandService {

    @Autowired
    private CarBrandDtoMapper carBrandDtoMapper;

    @Autowired
    private CarBrandRepository carBrandRepository;

    @Override
    public CarBrand findById(Long id) {
        return this.carBrandRepository.getOne(id);
    }

    @Override
    public List<CarBrand> getAll() {
        return this.enabledCarBrands(this.carBrandRepository.findAll());
    }

    @Override
    public void saveCarBrand(CarBrandDto carBrandDto) {
        CarBrand carBrandFromDatabase = this.carBrandRepository.getByName(carBrandDto.getName());
        if (carBrandFromDatabase != null) {
            if (carBrandFromDatabase.isDeleted()) {
                carBrandFromDatabase.setDeleted(false);
                this.carBrandRepository.save(carBrandFromDatabase);
                return;
            }
        }
        CarBrand newCarBrand = new CarBrand();
        newCarBrand.setName(carBrandDto.getName());
        this.carBrandRepository.save(newCarBrand);
    }

    @Override
    public void deleteCarBrand(Long id) {

        CarBrand carBrandForDelete = this.carBrandRepository.getOne(id);
        carBrandForDelete.setDeleted(true);
        this.carBrandRepository.save(carBrandForDelete);

    }

    @Override
    public void editCarBrand(CarBrandDto carBrandDto) {

        CarBrand carBrandToEdit = this.carBrandRepository.getOne(carBrandDto.getId());

        carBrandToEdit.setName(carBrandDto.getName());
        this.carBrandRepository.save(carBrandToEdit);

    }

    private List<CarBrand> enabledCarBrands(List<CarBrand> allCarBrands) {
        List<CarBrand> temp = new ArrayList<>();
        for (CarBrand carBrand : allCarBrands) {
            if (!carBrand.isDeleted()) {
                temp.add(carBrand);
            }
        }
        return temp;
    }
}
