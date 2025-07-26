package com.example.api.fintrack.application.services;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api.fintrack.application.dto.auth.RegisterRequest;
import com.example.api.fintrack.application.dto.user.UserInfoResponse;
import com.example.api.fintrack.application.exceptions.UserNotFoundException;
import com.example.api.fintrack.application.mappers.UserMapper;
import com.example.api.fintrack.domain.entities.User;
import com.example.api.fintrack.domain.exceptions.BusinessException;
import com.example.api.fintrack.infrastructure.messaging.events.UserRegisteredEvent;
import com.example.api.fintrack.infrastructure.messaging.kafka.producers.UserRegisteredProducer;
import com.example.api.fintrack.infrastructure.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserRegisteredProducer userRegisteredProducer;

    public User registerUser(RegisterRequest request) {
        validateEmailNotExists(request.getEmail());
        
        User user = userMapper.toEntity(request);
        user.setVerificationCode(generateVerificationCode());
        userRepository.save(user);
        publishUserRegisteredEvent(user);
        
        return user;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
    }

    public void updateLastLogin(String email) {
        User user = findByEmail(email);
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    private void validateEmailNotExists(String email) {
        if (existsByEmail(email)) {
            throw new BusinessException("Email já cadastrado, por favor, faça login");
        }
    }

    private void publishUserRegisteredEvent(User user) {
        userRegisteredProducer.sendUserRegisteredEvent(
            new UserRegisteredEvent(user.getEmail(), user.getName(), user.getVerificationCode())
        );
    }

    private String generateVerificationCode() {
        return String.valueOf(new Random().nextInt(9000) + 1000);
    }

    public UserInfoResponse getUserInfoByEmail(String email) {
        return userMapper.toUserInfoResponse(userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado")));
    }
} 