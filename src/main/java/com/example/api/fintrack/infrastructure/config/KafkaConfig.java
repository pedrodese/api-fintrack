package com.example.api.fintrack.infrastructure.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    // Tópicos Kafka básicos
    @Bean
    public NewTopic transacaoTopic() {
        return TopicBuilder.name("transacoes")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic notificacaoTopic() {
        return TopicBuilder.name("notificacoes")
                .partitions(1)
                .replicas(1)
                .build();
    }
} 