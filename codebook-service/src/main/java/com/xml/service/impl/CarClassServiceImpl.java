package com.xml.service.impl;

import com.xml.dto.CarClassDto;
import com.xml.mapper.CarClassDtoMapper;
import com.xml.model.CarBrand;
import com.xml.model.CarClass;
import com.xml.model.CarModel;
import com.xml.repository.CarClassRepository;
import com.xml.service.CarClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarClassServiceImpl implements CarClassService {

    @Autowired
    private CarClassDtoMapper carClassDtoMapper;

    @Autowired
    private CarClassRepository carClassRepository;

    @Override
    public CarClass findById(Long id) {
        return this.carClassRepository.getOne(id);
    }

    @Override
    public List<CarClass> getAll() {
        return this.enabledCarClasses(this.carClassRepository.findAll());
    }

    @Override
    public void saveCarClass(CarClassDto carClassDto) {
        CarClass carClassFromDatabase = this.carClassRepository.getByName(carClassDto.getName());
        if (carClassFromDatabase != null) {
            if (carClassFromDatabase.isDeleted()) {
                carClassFromDatabase.setDeleted(false);
                this.carClassRepository.save(carClassFromDatabase);
                return;
            }
        }
        CarClass newCarClass = new CarClass();

        newCarClass.setName(carClassDto.getName());

        this.carClassRepository.save(newCarClass);
    }

    @Override
    public void deleteCarClass(Long id) {

        CarClass carClassForDelete = this.carClassRepository.getOne(id);
        carClassForDelete.setDeleted(true);
        this.carClassRepository.save(carClassForDelete);
    }

    @Override
    public void editCarClass(CarClassDto carClassDto) {

        CarClass carClassToEdit = this.carClassRepository.getOne(carClassDto.getId());

        carClassToEdit.setName(carClassDto.getName());
        this.carClassRepository.save(carClassToEdit);

    }

    private List<CarClass> enabledCarClasses(List<CarClass> allCarClasses) {
        List<CarClass> temp = new ArrayList<>();
        for (CarClass carClass : allCarClasses) {
            if (!carClass.isDeleted()) {
                temp.add(carClass);
            }
        }
        return temp;
    }
}
