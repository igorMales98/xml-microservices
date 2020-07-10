package com.xml.service.impl;

import com.xml.dto.PricelistDto;
import com.xml.mapper.PricelistDtoMapper;
import com.xml.model.CarModel;
import com.xml.model.Pricelist;
import com.xml.repository.PricelistRepository;
import com.xml.service.PricelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PricelistServiceimpl implements PricelistService {

    @Autowired
    private PricelistRepository pricelistRepository;

    @Autowired
    private PricelistDtoMapper pricelistDtoMapper;

    @Override
    public Pricelist findById(Long id) {
        return this.pricelistRepository.getOne(id);
    }

    @Override
    public List<Pricelist> getAll() {
        return this.enabledPricelists(this.pricelistRepository.findAll());
    }

    @Override
    public Long create(PricelistDto pricelistDto) throws ParseException {
        Pricelist pricelist = this.pricelistDtoMapper.toEntity(pricelistDto);
        this.pricelistRepository.save(pricelist);
        this.pricelistRepository.flush();
        return pricelist.getId();
    }

    @Override
    public void savePricelist(PricelistDto pricelistDto) {
        Pricelist newPricelist = new Pricelist();
        newPricelist.setPriceForCDW(pricelistDto.getPriceForCDW());
        newPricelist.setPricePerDay(pricelistDto.getPricePerDay());
        newPricelist.setPricePerKm(pricelistDto.getPricePerKm());
        this.pricelistRepository.save(newPricelist);
    }

    @Override
    public void deletePricelist(Long id) {
        Pricelist pricelist = this.pricelistRepository.getOne(id);
        pricelist.setDeleted(true);
        this.pricelistRepository.save(pricelist);
    }

    @Override
    public void editPricelist(PricelistDto pricelistDto) {
        Pricelist pricelist = this.pricelistRepository.getOne(pricelistDto.getId());
        pricelist.setPriceForCDW(pricelistDto.getPriceForCDW());
        pricelist.setPricePerDay(pricelistDto.getPricePerDay());
        pricelist.setPricePerKm(pricelistDto.getPricePerKm());
        this.pricelistRepository.save(pricelist);
    }

    private List<Pricelist> enabledPricelists(List<Pricelist> pricelists) {
        List<Pricelist> temp = new ArrayList<>();
        for (Pricelist pricelist : pricelists) {
            if (!pricelist.isDeleted()) {
                temp.add(pricelist);
            }
        }
        return temp;
    }
}
