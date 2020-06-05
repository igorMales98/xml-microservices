package com.xml.service;

import com.xml.dto.RegistrationRequestDto;
import com.xml.dto.UserDto;
import com.xml.model.User;

public interface UserService {

    User findByUsername(String username);

    User findById(Long id);

    User createCustomerFromRequest(RegistrationRequestDto requestDto);

    void saveCustomer(User customer);
}
