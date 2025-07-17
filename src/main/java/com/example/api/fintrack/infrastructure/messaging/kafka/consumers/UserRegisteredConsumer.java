package com.example.api.fintrack.infrastructure.messaging.kafka.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.api.fintrack.application.services.EmailService;
import com.example.api.fintrack.infrastructure.messaging.events.UserRegisteredEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserRegisteredConsumer {
    private final EmailService emailService;

    @KafkaListener(topics = "user-registered", groupId = "fintrack-email")
    public void consume(UserRegisteredEvent event) {
        log.info("Consumindo evento UserRegistered para email: {}", event.getEmail());
        try {
            emailService.sendWelcomeEmail(event.getEmail(), event.getName());
        } catch (Exception e) {
            log.error("Erro ao enviar email de boas-vindas para {}: {}", event.getEmail(), e.getMessage());
        }
    }
} 