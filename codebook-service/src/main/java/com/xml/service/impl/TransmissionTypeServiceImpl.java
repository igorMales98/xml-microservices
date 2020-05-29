package com.xml.service.impl;

import com.xml.model.TransmissionType;
import com.xml.repository.TransmissionTypeRepository;
import com.xml.service.TransmissionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransmissionTypeServiceImpl implements TransmissionTypeService {

    @Autowired
    private TransmissionTypeRepository transmissionTypeRepository;

    @Override
    public TransmissionType findById(Long id) {
        return this.transmissionTypeRepository.getOne(id);
    }
}