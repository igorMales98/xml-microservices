package com.xml.service;

import com.xml.dto.CarClassDto;
import com.xml.model.CarClass;

import java.util.List;

public interface CarClassService {

    CarClass findById(Long id);

    List<CarClass> getAll();

    void saveCarClass(CarClassDto carClassDto);
    void deleteCarClass(Long id);
    void editCarClass(CarClassDto carClassDto);
}
