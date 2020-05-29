package com.xml.service;

import com.xml.model.User;

public interface UserService {

    User findByUsername(String username);

    User findById(Long id);
}
