package com.xml.service;

import com.xml.dto.AdvertisementDto;
import com.xml.dto.CreateAdvertisementDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface AdvertisementService {

    List<AdvertisementDto> getAll(String token);

    List<String> getAdvertisementPhotos(Long id) throws IOException;

    List<AdvertisementDto> getUserAdvertisements(Long userId, String token);

    Long saveAdvertisement(CreateAdvertisementDto createAdvertisementDto) throws ParseException;

    void uploadPhotos(MultipartFile[] files, Long id) throws IOException;
}
