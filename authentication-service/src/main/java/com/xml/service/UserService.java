package com.xml.service;

import com.xml.dto.UserDto;
import com.xml.dto.RegistrationRequestDto;
import com.xml.dto.UserDto;
import com.xml.dto.RegistrationRequestDto;
import com.xml.dto.UserDto;
import com.xml.model.Customer;
import com.xml.model.User;

public interface UserService {

    User findByUsername(String username);

    User findById(Long id);

    Customer createCustomerFromRequest(RegistrationRequestDto requestDto);

    void saveCustomer(Customer customer);

    void updateTimesRated(Long id);

    Long createPhysicalUser(UserDto userDto, String token);
}
