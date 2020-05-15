package com.xml.service;

import com.xml.model.Authority;
import com.xml.model.UserTokenState;
import com.xml.security.auth.JwtAuthenticationRequest;
import javassist.NotFoundException;

import java.util.Set;

public interface AuthorityService {
    Set<Authority> findByName(String name);

    Set<Authority> findById(Long id);

    UserTokenState login(JwtAuthenticationRequest authenticationRequest);


    boolean verify(String token) throws NotFoundException;
}
