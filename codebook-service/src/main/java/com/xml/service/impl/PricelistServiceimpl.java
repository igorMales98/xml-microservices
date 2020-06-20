package com.xml.service.impl;

import com.xml.dto.PricelistDto;
import com.xml.mapper.PricelistDtoMapper;
import com.xml.model.Pricelist;
import com.xml.repository.PricelistRepository;
import com.xml.service.PricelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
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
        return this.pricelistRepository.findAll();
    }

    @Override
    public Long create(PricelistDto pricelistDto) throws ParseException {
        Pricelist pricelist = this.pricelistDtoMapper.toEntity(pricelistDto);
        this.pricelistRepository.save(pricelist);
        this.pricelistRepository.flush();
        return pricelist.getId();
    }
}
