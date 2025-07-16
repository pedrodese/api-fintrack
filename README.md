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

The application follows **Clean Architecture** principles with a clear separation of concerns and **Event-Driven Architecture** for asynchronous processing:

```
src/main/java/com/example/api/fintrack/
├── domain/                    # Core business logic and entities
├── application/               # Use cases and application services
├── infrastructure/            # Technical implementations
└── interfaces/                # Controllers and API endpoints
```
