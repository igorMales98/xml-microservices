package com.xml.service;

import com.xml.dto.UserDto;
import com.xml.model.User;

public interface UserService {

    User findByUsername(String username);

    User findById(Long id);

    void updateTimesRated(Long id);

    Long createPhysicalUser(UserDto userDto, String token);
}
