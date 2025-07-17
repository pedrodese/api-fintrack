# FinTrack API

A comprehensive financial tracking API for personal finance management, built with modern Java technologies and following clean architecture principles.

## 📋 About

FinTrack is a RESTful API designed to help users manage their personal finances effectively. The application provides features for tracking income and expenses, setting financial goals, creating budgets, and gaining insights into spending patterns through detailed analytics and dashboards.

## 🎯 Key Features

- **User Authentication & Authorization** - Secure JWT-based authentication system
- **Transaction Management** - Complete CRUD operations for income and expense tracking
- **Category Management** - Customizable transaction categories with color coding
- **Budget Planning** - Monthly budget creation and tracking with progress monitoring
- **Financial Goals** - Goal setting with progress tracking and contribution management
- **Dashboard & Analytics** - Comprehensive financial insights and reporting
- **CSV Import** - Bulk transaction import functionality
- **Installment Management** - Multi-month expense tracking for large purchases
- **Notifications** - Budget alerts and goal reminder system
- **Event-Driven Architecture** - Asynchronous processing with Apache Kafka

## 🛠️ Technology Stack

### Core Framework

- **Spring Boot 3.5.3** - Main application framework
- **Java 21** - Programming language
- **Maven** - Build and dependency management

### Database & Persistence

- **PostgreSQL 15** - Primary relational database
- **Spring Data JPA** - Object-relational mapping
- **Flyway** - Database migration and version control

### Security

- **Spring Security** - Security framework
- **JWT (jjwt 0.12.3)** - Token-based authentication

### Cache & Performance

- **Redis 7** - Distributed caching for improved performance
- **Spring Cache** - Cache abstraction layer

### Messaging & Events

- **Apache Kafka** - Distributed event streaming platform
- **Spring Kafka** - Kafka integration for Spring Boot
- **Event-Driven Architecture** - Asynchronous event processing

### Documentation & Monitoring

- **OpenAPI/Swagger** - Interactive API documentation
- **Spring Actuator** - Application health monitoring
- **Prometheus** - Metrics collection and monitoring
- **Logback** - Structured logging with Logstash encoder

### Utilities

- **MapStruct** - Efficient object mapping
- **Lombok** - Boilerplate code reduction
- **OpenCSV** - CSV file processing for data import
- **Bean Validation** - Input validation and constraints

### Testing

- **Testcontainers** - Integration testing with real databases
- **Spring Security Test** - Security testing utilities
- **Spring Kafka Test** - Kafka testing utilities
- **JUnit 5** - Unit and integration testing framework

## 🏗️ Architecture

The application follows **Hexagonal Architecture** (Clean Architecture) principles with a clear separation of concerns and **Event-Driven Architecture** for asynchronous processing:

```
src/main/java/com/example/api/fintrack/
├── Application.java
├── domain/                    # 🎯 Core business logic and entities
│   ├── entities/             # Domain entities (User, Transaction, etc.)
│   ├── enums/               # Domain enums (TransactionType, etc.)
│   ├── events/              # Domain events (UserRegisteredEvent, etc.)
│   ├── services/            # Domain services (business rules)
│   └── exceptions/          # Domain exceptions (BusinessException, etc.)
├── application/              # 🔄 Use cases and application services
│   ├── services/            # Application services (orchestration)
│   ├── dto/                 # Data Transfer Objects
│   └── exceptions/          # Application exceptions
├── infrastructure/           # 🔧 Technical implementations
│   ├── config/              # Configuration classes
│   ├── repositories/        # Repository implementations
│   ├── messaging/           # Kafka producers/consumers
│   ├── security/            # JWT, authentication
│   ├── external/            # External services (Email, etc.)
│   └── exceptions/          # Infrastructure exceptions
└── interfaces/               # 🌐 Controllers and API endpoints
    └── controllers/         # REST Controllers
```

### 🎯 Architecture Benefits

- **Separation of Concerns**: Each layer has a specific responsibility
- **Testability**: Easy to test each layer in isolation
- **Maintainability**: Changes are isolated to specific layers
- **Scalability**: Easy to add new features without affecting existing code
- **Domain-Driven Design**: Business logic is centralized in the domain layer
- **Dependency Inversion**: High-level modules don't depend on low-level modules
