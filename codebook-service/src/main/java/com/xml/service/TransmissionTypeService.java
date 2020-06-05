package com.xml.service;

import com.xml.dto.TransmissionTypeDto;
import com.xml.model.TransmissionType;

import java.util.List;

public interface TransmissionTypeService {

    TransmissionType findById(Long id);

    List<TransmissionType> getAll();

    void saveTransmissionType(TransmissionTypeDto transmissionTypeDto);
    void deleteTransmissionType(Long id);
    void editTransmissionType(TransmissionTypeDto transmissionTypeDto);
}
