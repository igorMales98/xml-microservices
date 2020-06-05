package com.xml.service.impl;

import com.xml.dto.RegistrationRequestDto;
import com.xml.dto.UserDto;
import com.xml.model.Authority;
import com.xml.dto.UserDto;
import com.xml.model.User;
import com.xml.repository.UserRepository;
import com.xml.service.AuthorityService;
import com.xml.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import java.security.SecureRandom;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authorityService;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.getOne(id);
    }

    @Override
    public void updateTimesRated(Long id) {
        User user = this.userRepository.getOne(id);
        user.setAdvertisementsPosted((short) (user.getAdvertisementsPosted() + 1));
        this.userRepository.save(user);
    }

    @Override
    public Long createPhysicalUser(UserDto userDto, String token) {
        User newUser = new User();
        SecureRandom sri = new SecureRandom();
        String sni = Long.toString(Math.abs(sri.nextLong()));
        newUser.setUsername("PhysicalUser" + sni);
        newUser.setPassword(passwordEncoder.encode(sni));
        newUser.setFirstName(userDto.getFirstName());
        newUser.setLastName(userDto.getLastName());
        newUser.setEmail(userDto.getEmail());
        newUser.setCountry(userDto.getCountry());
        newUser.setCity(userDto.getCity());
        newUser.setAddress(userDto.getAddress());
        newUser.setPhone(userDto.getPhone());
        this.userRepository.save(newUser);
        this.userRepository.flush();
        return newUser.getId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User with username '%s' was not found", username));
        } else {
            return user;
        }
    }

    @Override
    public User createCustomerFromRequest(RegistrationRequestDto requestDto) {
        User user = new User(requestDto.getUsername(),
                passwordEncoder.encode(requestDto.getPassword()),
                requestDto.getFirstName(),
                requestDto.getLastName(),
                requestDto.getCountry(),
                requestDto.getCity(),
                requestDto.getAddress(),
                requestDto.getEmail(),
                requestDto.getPhone());
        Set<Authority> auth = authorityService.findById(3L);
        user.setAuthorities(auth);
        user.setEnabled(true);

        return user;
    }

    @Override
    public void saveCustomer(User customer) {
        userRepository.save(customer);
    }

}
