package com.example.api.fintrack.application.mappers;

import com.example.api.fintrack.application.dto.auth.LoginResponse;
import com.example.api.fintrack.application.dto.auth.RegisterRequest;
import com.example.api.fintrack.application.dto.user.UserInfoResponse;
import com.example.api.fintrack.domain.entities.User;

public class UserMapper {

    public User toEntity(RegisterRequest request) {
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .phone(request.getPhone())
                .birthDate(request.getBirthDate())
                .build();
    }

    public LoginResponse toLoginResponse(String accessToken, String refreshToken, String tokenType, long expiresIn) {
        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType(tokenType)
                .expiresIn(expiresIn)
                .build();
    }

    public UserInfoResponse toUserInfoResponse(User user) {
        return UserInfoResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .birthDate(user.getBirthDate())
                .status(user.getStatus())
                .accountVerified(user.getAccountVerified())
                .lastLogin(user.getLastLogin())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
