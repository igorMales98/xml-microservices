package com.xml.service.impl;

import com.xml.dto.CarModelDto;
import com.xml.dto.FuelTypeDto;
import com.xml.mapper.CarModelDtoMapper;
import com.xml.mapper.FuelTypeDtoMapper;
import com.xml.model.CarModel;
import com.xml.model.FuelType;
import com.xml.repository.FuelTypeRepository;
import com.xml.service.FuelTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuelTypeServiceImpl implements FuelTypeService {

    @Autowired
    private FuelTypeRepository fuelTypeRepository;

    @Autowired
    private FuelTypeDtoMapper fuelTypeDtoMapper;

    @Override
    public FuelType findById(Long id) {
        return this.fuelTypeRepository.getOne(id);
    }

    @Override
    public List<FuelType> getAll() {
        return this.fuelTypeRepository.findAll();
    }

    @Override
    public void saveFuelType(FuelTypeDto fuelTypeDto) {
        FuelType newFuelType = new FuelType();

        newFuelType.setName(fuelTypeDto.getName());

        this.fuelTypeRepository.save(newFuelType);
    }

    @Override
    public void deleteFuelType(Long id) {

        FuelType fuelTypeForDelete = this.fuelTypeRepository.getOne(id);

        this.fuelTypeRepository.delete(fuelTypeForDelete);

    }

    @Override
    public void editFuelType(FuelTypeDto fuelTypeDto) {

        FuelType fuelTypeToEdit = this.fuelTypeRepository.getOne(fuelTypeDto.getId());
        fuelTypeToEdit.setName(fuelTypeDto.getName());
        this.fuelTypeRepository.save(fuelTypeToEdit);

    }
}
