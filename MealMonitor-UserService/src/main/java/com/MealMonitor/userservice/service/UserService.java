package com.MealMonitor.userservice.service;

import com.MealMonitor.userservice.dto.UserLoginDto;
import com.MealMonitor.userservice.dto.UserRegistrationDto;
import com.MealMonitor.userservice.entity.User;
import com.MealMonitor.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(UserRegistrationDto registrationDto) {
        // Check if user already exists
        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            throw new RuntimeException("User with this email already exists");
        }

        if (userRepository.existsByStudentId(registrationDto.getStudentId())) {
            throw new RuntimeException("User with this student ID already exists");
        }

        // Create new user
        User user = new User();

        user.setFirstName(registrationDto.getFirstName());
        user.setLastName(registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
        user.setPasswordHash(passwordEncoder.encode(registrationDto.getPassword()));
        user.setStudentId(registrationDto.getStudentId());

        user.setRoleId("STUDENT");

        return userRepository.save(user);
    }

    public User authenticateUser(UserLoginDto loginDto) {
        Optional<User> userOptional = userRepository.findByEmail(loginDto.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(loginDto.getPassword(), user.getPasswordHash())) {
                return user;
            }
        }

        throw new RuntimeException("Invalid email or password");
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
