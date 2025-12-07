package com.app.user_management.service;

import com.app.user_management.dto.request.SignupRequest;
import com.app.user_management.dto.response.SignupResponse;
import com.app.user_management.entity.User;
import com.app.user_management.entity.User.Role;
import com.app.user_management.entity.User.Status;
import com.app.user_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository userRepository;
    //private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
        //this.passwordEncoder = passwordEncoder;
    }

    public SignupResponse signup(SignupRequest signupRequest) {

        if(userRepository.existsByEmail(signupRequest.getEmail())) {
            return new SignupResponse(null, signupRequest.getEmail(), signupRequest.getPhone(), null, signupRequest.getCompanyId(), null);
        }
        // Hash the password
        //String hashedPassword = passwordEncoder.encode(signupRequest.getPassword());

        // Create a new User entity
        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .email(signupRequest.getEmail())
                .passwordHash(signupRequest.getPassword())
                .fullName(signupRequest.getFullName())
                .phone(signupRequest.getPhone())
                .avatar(signupRequest.getAvatar())
                .companyId(signupRequest.getCompanyId())
                .role(Role.CUSTOMER) // Default role
                .status(Status.ACTIVE) // Default status
                .createdAt(LocalDateTime.now()) // Explicitly set createdAt
                .updatedAt(LocalDateTime.now()) // Explicitly
                .build();

        user = userRepository.save(user);

        //prepare signup response
        return new SignupResponse( user.getFullName(), user.getEmail(), user.getPhone(),
                user.getRole(), user.getCompanyId(), user.getStatus());
    }
}