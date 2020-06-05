package com.xml.service;

import com.xml.dto.RegistrationRequestDto;

import java.util.List;

public interface RegistrationRequestService {

    List<RegistrationRequestDto> getAll();
    void deleteRegistrationRequest(Long id);
}
