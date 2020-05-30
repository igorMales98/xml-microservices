package com.xml.service;

import com.xml.model.TransmissionType;

import java.util.List;

public interface TransmissionTypeService {

    TransmissionType findById(Long id);

    List<TransmissionType> getAll();
}
