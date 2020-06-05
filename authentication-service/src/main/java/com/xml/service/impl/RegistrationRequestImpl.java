package com.xml.service.impl;

import com.xml.dto.RegistrationRequestDto;
import com.xml.repository.RegistrationRequestRepository;
import com.xml.model.RegistrationRequest;
import com.xml.service.RegistrationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationRequestImpl implements RegistrationRequestService {

    @Autowired
    private RegistrationRequestRepository registrationRequestRepository;

    @Override
    public List<RegistrationRequestDto> getAll() {
        List<RegistrationRequestDto> registrationRequestDtos = new ArrayList<>();

        List<RegistrationRequest> allRequests = this.registrationRequestRepository.findAll();

        return getRegistrationRequestDtos(registrationRequestDtos, allRequests);
    }

    private List<RegistrationRequestDto> getRegistrationRequestDtos(List<RegistrationRequestDto> registrationRequestDtos, List<RegistrationRequest> allRequests) {
        for (RegistrationRequest request : allRequests) {

            RegistrationRequestDto requestDto = new RegistrationRequestDto();
            requestDto.setId(request.getId());
            requestDto.setAddress(request.getAddress());
            requestDto.setCity(request.getCity());
            requestDto.setCountry(request.getCountry());
            requestDto.setEmail(request.getEmail());
            requestDto.setFirstName(request.getFirstName());
            requestDto.setLastName(request.getLastName());
            requestDto.setPassword(request.getPassword());
            requestDto.setPhone(request.getPhone());
            requestDto.setUsername(request.getUsername());

            registrationRequestDtos.add(requestDto);
        }

        return registrationRequestDtos;
    }

    @Override
    public void deleteRegistrationRequest(Long id) {
        RegistrationRequest request = this.registrationRequestRepository.findOneById(id);
        try {
            this.registrationRequestRepository.delete(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
