package com.xml.service;

import com.xml.model.CarClass;

import java.util.List;

public interface CarClassService {

    CarClass findById(Long id);

    List<CarClass> getAll();
}
