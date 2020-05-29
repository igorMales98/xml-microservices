package com.xml.service.impl;

import com.xml.dto.AdvertisementDto;
import com.xml.model.Advertisement;
import com.xml.repository.AdvertisementRepository;
import com.xml.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Override
    public List<AdvertisementDto> getAll() {
        List<AdvertisementDto> advertisementDtos = new ArrayList<>();

        List<Advertisement> allAdvertisements = this.advertisementRepository.findAll();

        return null;
    }
}
