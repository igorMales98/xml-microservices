package com.xml.service.impl;

import com.xml.model.Pricelist;
import com.xml.repository.PricelistRepository;
import com.xml.service.PricelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricelistServiceimpl implements PricelistService {

    @Autowired
    private PricelistRepository pricelistRepository;

    @Override
    public Pricelist findById(Long id) {
        return this.pricelistRepository.getOne(id);
    }

    @Override
    public List<Pricelist> getAll() {
        return this.pricelistRepository.findAll();
    }
}
