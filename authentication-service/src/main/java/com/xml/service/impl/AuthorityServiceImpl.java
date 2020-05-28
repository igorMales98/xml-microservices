package com.xml.service.impl;

import com.xml.dto.RegistrationRequestDto;
import com.xml.mapper.RegistrationRequestDtoMapper;
import com.xml.model.Authority;
import com.xml.model.Permission;
import com.xml.model.User;
import com.xml.model.UserTokenState;
import com.xml.repository.AuthorityRepository;
import com.xml.repository.RegistrationRequestRepository;
import com.xml.repository.UserRepository;
import com.xml.security.TokenUtils;
import com.xml.security.auth.JwtAuthenticationRequest;
import com.xml.security.auth.TokenBasedAuthentication;
import com.xml.service.AuthorityService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegistrationRequestRepository registrationRequestRepository;

    @Autowired
    private RegistrationRequestDtoMapper registrationRequestDtoMapper;

    private UserDetailsService userDetailsService;

    @Override
    public Set<Authority> findByName(String name) {
        Authority authority = this.authorityRepository.findByName(name);
        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority);
        return authorities;
    }

    @Override
    public Set<Authority> findById(Long id) {
        Authority authority = this.authorityRepository.getOne(id);
        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority);
        return authorities;
    }

    @Override
    public UserTokenState login(JwtAuthenticationRequest authenticationRequest) {
        final Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        Authority a = user.getRoleAuthorities().iterator().next();
        String token = tokenUtils.generateToken(user.getUsername(), a);
        int expiresIn = tokenUtils.getExpiredIn();

        return new UserTokenState(token, expiresIn, a.getName());
    }

    @Override
    public Collection<Permission> verify(String token) throws NotFoundException {
        String username = tokenUtils.getUsernameFromToken(token);
        System.out.println("korisnik je " + username);
        if (!this.userRepository.existsByUsername(username)) {
            throw new NotFoundException("User not found.");
        }
        User user = userRepository.findByUsername(username);
        Set<Permission> allPermissions = new HashSet<>();
        for (Authority a : user.getRoleAuthorities()) {
            allPermissions.addAll(a.getPermissions());
        }
        return allPermissions;
    }

    @Override
    public void register(RegistrationRequestDto registrationRequest) throws ParseException {
        this.registrationRequestRepository.save(registrationRequestDtoMapper.toEntity(registrationRequest));
    }


}
