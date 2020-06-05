package com.xml.service.impl;

import com.xml.dto.TransmissionTypeDto;
import com.xml.mapper.TransmissionTypeDtoMapper;
import com.xml.model.TransmissionType;
import com.xml.repository.TransmissionTypeRepository;
import com.xml.service.TransmissionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return this.transmissionTypeRepository.findAll();
    }

    @Override
    public void saveTransmissionType(TransmissionTypeDto transmissionTypeDto) {
        TransmissionType newTransmissionType = new TransmissionType();

        newTransmissionType.setName(transmissionTypeDto.getName());

        this.transmissionTypeRepository.save(newTransmissionType);
    }

    @Override
    public void deleteTransmissionType(Long id) {

        TransmissionType transmissionTypeForDelete = this.transmissionTypeRepository.getOne(id);

        this.transmissionTypeRepository.delete(transmissionTypeForDelete);

    }

    @Override
    public void editTransmissionType(TransmissionTypeDto transmissionTypeDto) {

        TransmissionType transmissionTypeToEdit = this.transmissionTypeRepository.getOne(transmissionTypeDto.getId());

        transmissionTypeToEdit.setName(transmissionTypeDto.getName());
        this.transmissionTypeRepository.save(transmissionTypeToEdit);

    }
}
