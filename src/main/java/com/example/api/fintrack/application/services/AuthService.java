package com.example.api.fintrack.application.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api.fintrack.application.dto.auth.LoginRequest;
import com.example.api.fintrack.application.dto.auth.LoginResponse;
import com.example.api.fintrack.application.dto.auth.RegisterRequest;
import com.example.api.fintrack.application.mappers.UserMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    public String register(RegisterRequest request) {
        userService.registerUser(request);
        return "Usuário registrado com sucesso, verifique seu email para ativar sua conta";
    }

    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticateUser(request);
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        userService.updateLastLogin(userDetails.getUsername());

        return generateLoginResponse(userDetails);
    }

    private Authentication authenticateUser(LoginRequest request) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail().toLowerCase().trim(),
                        request.getPassword()
                )
        );
    }

    private LoginResponse generateLoginResponse(UserDetails userDetails) {
        String token = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        return userMapper.toLoginResponse(token, refreshToken, "Bearer", jwtService.getJwtExpiration());
    }


} 