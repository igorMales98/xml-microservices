package com.xml.service.impl;

import com.xml.dto.RegistrationRequestDto;
import com.xml.dto.UserDto;
import com.xml.model.Agent;
import com.xml.model.Authority;
import com.xml.model.Customer;
import com.xml.model.User;
import com.xml.repository.UserRepository;
import com.xml.service.AuthorityService;
import com.xml.service.EmailService;
import com.xml.service.UserService;
import com.xml.validator.PasswordConstraintValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private EmailService emailService;

    private PasswordConstraintValidator passwordConstraintValidator = new PasswordConstraintValidator();

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.getOne(id);
    }

    @Override
    public void updateTimesPosted(Long id) {
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
    public Customer createCustomerFromRequest(RegistrationRequestDto requestDto) {
        Customer customer = new Customer(requestDto.getUsername(),
                requestDto.getPassword(),
                requestDto.getFirstName(),
                requestDto.getLastName(),
                requestDto.getCountry(),
                requestDto.getCity(),
                requestDto.getAddress(),
                requestDto.getEmail(),
                requestDto.getPhone());
        Set<Authority> auth = authorityService.findByName("ROLE_CUSTOMER");
        customer.setAuthorities(auth);
        customer.setEnabled(false);

        return customer;
    }

    @Override
    public void saveCustomer(Customer customer) {
        this.userRepository.save(customer);
        this.userRepository.flush();
    }

    @Override
    public List<UserDto> getAllCustomers() {
        List<UserDto> allCustomersDtos = new ArrayList();
        String forQuery = "CUSTOMER";
        List<User> allCustomers = this.userRepository.findAllCustomers(forQuery);

        return getAllCustomersDtos(allCustomersDtos, allCustomers);

    }

    private List<UserDto> getAllCustomersDtos(List<UserDto> allCustomersDtos, List<User> allCustomers) {
        for (User customer : allCustomers) {
            UserDto customerDto = new UserDto();
            customerDto.setId(customer.getId());
            customerDto.setUsername(customer.getUsername());
            customerDto.setFirstName(customer.getFirstName());
            customerDto.setLastName(customer.getLastName());
            customerDto.setCountry(customer.getCountry());
            customerDto.setCity(customer.getCity());
            customerDto.setEmail(customer.getEmail());
            customerDto.setPhone(customer.getPhone());
            customerDto.setAddress(customer.getAddress());
            customerDto.setEnabled(customer.isEnabled());

            allCustomersDtos.add(customerDto);
        }

        return allCustomersDtos;
    }

    @Override
    public void deleteCustomer(Long id) {
        User customerToDelete = this.userRepository.findById(id).get();
        customerToDelete.setDeleted(true);
        userRepository.save(customerToDelete);
    }

    @Override
    public void blockUser(Long id) {
        User customer = this.userRepository.findById(id).get();
        try {
            customer.setEnabled(false);
            this.userRepository.save(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void activateUser(long id) {
        User customer = this.userRepository.findById(id).get();
        try {
            customer.setEnabled(true);
            this.userRepository.save(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registerAgent(UserDto userDto) {
        Agent agent = new Agent(userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getCountry(),
                userDto.getCity(),
                userDto.getAddress(),
                userDto.getEmail(),
                userDto.getPhone(),
                userDto.getBusinessSocialNumber());
        Set<Authority> auth = authorityService.findByName("ROLE_AGENT");
        agent.setAuthorities(auth);
        agent.setEnabled(true);

        this.userRepository.save(agent);
        this.userRepository.flush();
    }

    @Override
    public void activateUserEmail(String username) {
        User customer = this.userRepository.findByUsername(username);
        customer.setEnabled(true);
        this.userRepository.save(customer);
    }

    @Override
    public void forgotPassword(String email) {
        User user = this.userRepository.findByEmail(email);

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        System.out.println(generatedString);

        emailService.sendMailToUser(user.getEmail(), "Your new password is : " + generatedString, "Successfully reset password");
        user.setPassword(this.passwordEncoder.encode(generatedString));
        this.userRepository.save(user);
    }

    @Override
    public boolean checkPassword(String password) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userRepository.findByUsername(userDetails.getUsername());
        System.out.println("kad menjam sigfru korisnik je " + userDetails.getUsername());
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public void changePassword(String password) throws ValidationException {
        if (passwordConstraintValidator.isValid(password, null)) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = this.userRepository.findByUsername(userDetails.getUsername());
            user.setPassword(passwordEncoder.encode(password));
            this.userRepository.save(user);
        } else {
            throw new ValidationException("Password is not valid.");
        }
    }


}
