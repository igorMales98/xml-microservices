package com.xml.service.impl;

import com.xml.dto.TransmissionTypeDto;
import com.xml.mapper.TransmissionTypeDtoMapper;
import com.xml.model.FuelType;
import com.xml.model.TransmissionType;
import com.xml.repository.TransmissionTypeRepository;
import com.xml.service.TransmissionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransmissionTypeServiceImpl implements TransmissionTypeService {

    @Autowired
    private TransmissionTypeRepository transmissionTypeRepository;

    @Autowired
    private TransmissionTypeDtoMapper transmissionTypeDtoMapper;

    @Override
    public TransmissionType findById(Long id) {
        return this.transmissionTypeRepository.getOne(id);
    }

    @Override
    public List<TransmissionType> getAll() {
        return this.enabledTransmissionTypes(this.transmissionTypeRepository.findAll());
    }

    @Override
    public void saveTransmissionType(TransmissionTypeDto transmissionTypeDto) {
        TransmissionType transmissionTypeFromDatabase = this.transmissionTypeRepository.getByName(transmissionTypeDto.getName());
        if (transmissionTypeFromDatabase != null) {
            if (transmissionTypeFromDatabase.isDeleted()) {
                transmissionTypeFromDatabase.setDeleted(false);
                this.transmissionTypeRepository.save(transmissionTypeFromDatabase);
                return;
            }
        }
        TransmissionType newTransmissionType = new TransmissionType();

        newTransmissionType.setName(transmissionTypeDto.getName());

        this.transmissionTypeRepository.save(newTransmissionType);
    }

    @Override
    public void deleteTransmissionType(Long id) {

        TransmissionType transmissionTypeForDelete = this.transmissionTypeRepository.getOne(id);
        transmissionTypeForDelete.setDeleted(true);
        this.transmissionTypeRepository.save(transmissionTypeForDelete);

    }

    @Override
    public void editTransmissionType(TransmissionTypeDto transmissionTypeDto) {

        TransmissionType transmissionTypeToEdit = this.transmissionTypeRepository.getOne(transmissionTypeDto.getId());

        transmissionTypeToEdit.setName(transmissionTypeDto.getName());
        this.transmissionTypeRepository.save(transmissionTypeToEdit);

    }

    private List<TransmissionType> enabledTransmissionTypes(List<TransmissionType> allTransmissionTypes) {
        List<TransmissionType> temp = new ArrayList<>();
        for (TransmissionType transmissionType : allTransmissionTypes) {
            if (!transmissionType.isDeleted()) {
                temp.add(transmissionType);
            }
        }
        return temp;
    }
}
