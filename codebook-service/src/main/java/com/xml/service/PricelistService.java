package com.xml.service;

import com.xml.model.Pricelist;

import java.util.List;

public interface PricelistService {

    Pricelist findById(Long id);

    List<Pricelist> getAll();
}
