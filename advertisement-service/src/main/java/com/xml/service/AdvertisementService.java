package com.xml.service;

import com.xml.dto.AdvertisementDto;

import java.io.IOException;
import java.util.List;

public interface AdvertisementService {

    List<AdvertisementDto> getAll(String token);

    List<String> getAdvertisementPhotos(Long id) throws IOException;
}
