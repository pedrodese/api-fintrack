package com.example.api.fintrack.interfaces.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.fintrack.application.dto.auth.LoginRequest;
import com.example.api.fintrack.application.dto.auth.LoginResponse;
import com.example.api.fintrack.application.dto.auth.RegisterRequest;
import com.example.api.fintrack.application.services.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    //TODO: Implement the logic to send the email for verify the account in the register endpoint
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
        String response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //TODO: Implement the logic to validate if the account is verified before login
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    //TODO: Implement the forgot password endpoint

    //TODO: Implement the verify account endpoint //confirm-pin

    //TODO: Implement the resend verification code endpoint //resend-pin
}
