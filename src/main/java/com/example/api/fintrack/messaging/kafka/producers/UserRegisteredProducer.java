package com.example.api.fintrack.messaging.kafka.producers;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.example.api.fintrack.messaging.events.UserRegisteredEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserRegisteredProducer {
    private final KafkaTemplate<String, UserRegisteredEvent> kafkaTemplate;
    private static final String TOPIC = "user-registered";

    public void sendUserRegisteredEvent(UserRegisteredEvent event) {
        kafkaTemplate.send(TOPIC, event);
        log.info("Evento UserRegistered publicado no Kafka para email: {}", event.getEmail());
    }
} 