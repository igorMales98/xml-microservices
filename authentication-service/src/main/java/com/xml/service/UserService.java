package com.xml.service;

import com.xml.dto.UserDto;
import com.xml.dto.RegistrationRequestDto;
import com.xml.dto.UserDto;
import com.xml.dto.RegistrationRequestDto;
import com.xml.dto.UserDto;
import com.xml.model.Customer;
import com.xml.model.User;

import java.util.List;

public interface UserService {

    User findByUsername(String username);

    User findById(Long id);

    Customer createCustomerFromRequest(RegistrationRequestDto requestDto);

    void saveCustomer(Customer customer);

    void updateTimesPosted(Long id);

    Long createPhysicalUser(UserDto userDto, String token);

    List<UserDto> getAllCustomers();

    void deleteCustomer(Long id);

    void blockUser(Long id);

    void activateUser(long id);

    void registerAgent(UserDto userDto);

    void activateUserEmail(String username);

    void forgotPassword(String email);

    boolean checkPassword(String password);

    void changePassword(String password);
}
