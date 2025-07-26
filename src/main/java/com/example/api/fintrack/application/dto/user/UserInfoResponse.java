package com.example.api.fintrack.application.dto.user;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.api.fintrack.domain.enums.UserStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDate birthDate;
    private UserStatus status;
    private Boolean accountVerified;
    private LocalDateTime lastLogin;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
