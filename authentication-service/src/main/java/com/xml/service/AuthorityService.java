package com.xml.service;

import com.xml.dto.RegistrationRequestDto;
import com.xml.model.Authority;
import com.xml.model.Permission;
import com.xml.model.UserTokenState;
import com.xml.security.auth.JwtAuthenticationRequest;
import com.xml.security.auth.TokenBasedAuthentication;
import javassist.NotFoundException;
import org.springframework.security.core.GrantedAuthority;

import javax.xml.bind.ValidationException;
import java.text.ParseException;
import java.util.Collection;
import java.util.Set;

public interface AuthorityService {
    Set<Authority> findByName(String name);

    Set<Authority> findById(Long id);

    UserTokenState login(JwtAuthenticationRequest authenticationRequest);


    Collection<Permission> verify(String token) throws NotFoundException;

    void register(RegistrationRequestDto registrationRequest) throws ParseException, ValidationException;

    Long getLoggedInUserId(String token);
}
