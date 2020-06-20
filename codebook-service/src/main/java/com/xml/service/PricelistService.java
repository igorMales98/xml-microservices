package com.xml.service;

import com.xml.dto.PricelistDto;
import com.xml.model.Pricelist;

import java.text.ParseException;
import java.util.List;

public interface PricelistService {

    Pricelist findById(Long id);

    List<Pricelist> getAll();

    Long create(PricelistDto pricelistDto) throws ParseException;
}
