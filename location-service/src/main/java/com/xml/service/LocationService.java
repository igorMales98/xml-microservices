package com.xml.service;

import com.xml.dto.CoordinatesDto;

public interface LocationService {
    CoordinatesDto getLocation(String androidToken);

    void resetSeconds();
}
