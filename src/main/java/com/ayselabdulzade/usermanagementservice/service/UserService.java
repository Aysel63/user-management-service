package com.ayselabdulzade.usermanagementservice.service;

import com.ayselabdulzade.usermanagementservice.dao.UserRepository;
import com.ayselabdulzade.usermanagementservice.dto.request.CreateUserRequest;
import com.ayselabdulzade.usermanagementservice.dto.response.UserResponse;
import com.ayselabdulzade.usermanagementservice.entity.UserEntity;
import com.ayselabdulzade.usermanagementservice.exception.DuplicateEmailException;

import com.ayselabdulzade.usermanagementservice.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional
    public UserResponse createUser(CreateUserRequest request) {
        if (request.getEmail() == null || request.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (!request.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Invalid email format: " + request.getEmail());}

        logger.info("Creating new user with email: {}", request.getEmail());

        if (userRepository.existsByEmail(request.getEmail())) {
            logger.warn("Attempt to create user with duplicate email: {}", request.getEmail());
            throw new DuplicateEmailException("User with email " + request.getEmail() + " already exists");
        }
       UserEntity user= UserEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .phonePrefix(request.getPhonePrefix())
                .role(request.getRole())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        UserEntity savedUser = userRepository.save(user);
        logger.info("User created successfully with ID: {}", savedUser.getId());
        return new UserResponse(savedUser);
    }

    public UserResponse getUserById(Long id) {
        logger.debug("Fetching user with ID: {}", id);

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("User not found with ID: {}", id);
                    return new ResourceNotFoundException("User not found with ID: " + id);
                });

        return new UserResponse(user);
    }

    public List<UserResponse> getAllUsers() {
        logger.info("Fetching all users");

        return userRepository.findAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }
    public UserResponse updateUser(Long id, CreateUserRequest request) {
        logger.info("Updating user with ID: {}", id);

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("User not found with ID: {}", id);
                    return new ResourceNotFoundException("User not found with ID: " + id);
                });
        if (!user.getEmail().equals(request.getEmail()) &&
                userRepository.existsByEmail(request.getEmail())) {
            logger.warn("Attempt to update user with duplicate email: {}", request.getEmail());
            throw new DuplicateEmailException("User with email " + request.getEmail() + " already exists");
        }
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPhonePrefix(request.getPhonePrefix());
        user.setRole(request.getRole());
        user.setUpdatedAt(LocalDateTime.now());

        // Database-ə saxla
        UserEntity updatedUser = userRepository.save(user);
        logger.info("User updated successfully with ID: {}", updatedUser.getId());

        return new UserResponse(updatedUser);
    }
    public void deleteUser(Long id) {
        logger.info("Deleting user with ID: {}", id);

        if (!userRepository.existsById(id)) {
            logger.warn("User not found with ID: {}", id);
            throw new ResourceNotFoundException("User not found with ID: " + id);
        }

        userRepository.deleteById(id);
        logger.info("User deleted successfully with ID: {}", id);
    }

}
