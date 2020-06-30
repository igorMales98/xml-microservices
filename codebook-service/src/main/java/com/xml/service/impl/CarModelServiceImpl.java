package com.xml.service.impl;

import com.xml.dto.CarModelDto;
import com.xml.mapper.CarBrandDtoMapper;
import com.xml.mapper.CarModelDtoMapper;
import com.xml.model.CarModel;
import com.xml.repository.CarModelRepository;
import com.xml.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarModelServiceImpl implements CarModelService {

    @Autowired
    private CarModelRepository carModelRepository;

    @Autowired
    private CarModelDtoMapper carModelDtoMapper;

    @Autowired
    private CarBrandDtoMapper carBrandDtoMapper;

    @Override
    public CarModel findById(Long id) {
        return this.carModelRepository.getOne(id);
    }

    @Override
    public List<CarModel> getAll() {
        return this.enabledCarModels(this.carModelRepository.findAll());
    }

    @Override
    public List<CarModel> getBrandModels(Long brandId) {
        return this.carModelRepository.getByCarBrandId(brandId);
    }

    @Override
    public void saveCarModel(CarModelDto carModelDto) throws ParseException {
        CarModel newCarModel = new CarModel();

        newCarModel.setName(carModelDto.getName());
        newCarModel.setCarBrand(carBrandDtoMapper.toEntity(carModelDto.getCarBrandDto()));
        this.carModelRepository.save(newCarModel);
    }

    @Override
    public void deleteCarModel(Long id) {

        CarModel carModelForDelete = this.carModelRepository.getOne(id);
        carModelForDelete.setDeleted(true);
        this.carModelRepository.save(carModelForDelete);
    }

    @Override
    public void editCarModel(CarModelDto carModelDto) {

        CarModel carModelToEdit = this.carModelRepository.getOne(carModelDto.getId());

        carModelToEdit.setName(carModelDto.getName());
        this.carModelRepository.save(carModelToEdit);

    }

    private List<CarModel> enabledCarModels(List<CarModel> allCarModels) {
        List<CarModel> temp = new ArrayList<>();
        for (CarModel carModel : allCarModels) {
            if (!carModel.isDeleted()) {
                temp.add(carModel);
            }
        }
        return temp;
    }
}
