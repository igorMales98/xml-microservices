package com.xml.service.impl;

import com.xml.model.CarClass;
import com.xml.repository.CarClassRepository;
import com.xml.service.CarClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarClassServiceImpl implements CarClassService {

    @Autowired
    private CarClassRepository carClassRepository;

    @Override
    public CarClass findById(Long id) {
        return this.carClassRepository.getOne(id);
    }
}
