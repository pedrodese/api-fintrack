package com.example.api.fintrack.application.services;

import java.time.LocalDateTime;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api.fintrack.application.dto.auth.LoginRequest;
import com.example.api.fintrack.application.dto.auth.LoginResponse;
import com.example.api.fintrack.application.dto.auth.RegisterRequest;
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
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRegisteredProducer userRegisteredProducer;

    public String register(RegisterRequest request) {
        log.info("Iniciando registro de usuário com email: {}", request.getEmail());
        
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Email já está em uso");
        }
        User user = User.builder()
                .name(request.getName().trim())
                .email(request.getEmail().toLowerCase().trim())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone() != null ? request.getPhone().trim() : null)
                .birthDate(request.getBirthDate())
                .build();

        User savedUser = userRepository.save(user);

        // Publicar evento no Kafka para envio assíncrono de email
        userRegisteredProducer.sendUserRegisteredEvent(
            new UserRegisteredEvent(savedUser.getEmail(), savedUser.getName())
        );

        log.info("Usuário registrado com sucesso. ID: {}", savedUser.getId());

        return "Usuário registrado com sucesso";
    }

    public LoginResponse login(LoginRequest request) {
        log.info("Iniciando login para email: {}", request.getEmail());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail().toLowerCase().trim(),
                        request.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        String token = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        log.info("Login realizado com sucesso para usuário ID: {}", user.getId());

        return LoginResponse.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .expiresIn(jwtService.getJwtExpiration())
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .lastLogin(user.getLastLogin())
                .build();
    }
} 