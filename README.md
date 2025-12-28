# Focason Cloud Starter

## Table of Contents
- [Project Description](#project-description)
- [Badges](#badges)
- [Demo](#demo)
- [Modules](#modules)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Setup](#setup)
- [Start](#start)
- [Development](#development)
- [Release](#release)
- [Roadmap](#roadmap)
- [Contributing](#contributing)
- [License](#license)

## Project Description
Focason Cloud Starter is a microservices-based backend system built with **Spring Cloud 2023.0.0** and **Spring Boot 3.2.0**.  
It provides a service registry, API gateway, centralized configuration, authentication and authorization services, and core business modules.

This project demonstrates how to build, containerize, and deploy a distributed system using modern technologies such as Docker, RabbitMQ, AWS S3/SQS, Redis, and WebSocket.

## Badges
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-v2023.0.0-brightgreen)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-v3.2.0-brightgreen)
![Gradle](https://img.shields.io/badge/Gradle-v8.14.3-lightgrey)
![Java](https://img.shields.io/badge/Java-17-orange)
![MySQL](https://img.shields.io/badge/MySQL-v8.0+-blue)

![JWT](https://img.shields.io/badge/JWT-v0.12.3-brightgreen)
![Doma2](https://img.shields.io/badge/Doma2-v2.53.0-blue)
![Doma2 Gen](https://img.shields.io/badge/Doma2%20Gen-v2.28.0-blue)
![Spring Cloud AWS](https://img.shields.io/badge/Spring%20Cloud%20AWS-v3.1.0-orange)
![Lombok](https://img.shields.io/badge/Lombok-v1.18.26-red)
![Spotless](https://img.shields.io/badge/Spotless-v8.0.0-orange)
![RabbitMQ](https://img.shields.io/badge/RabbitMQ-v3.11+-orange)
![Redis](https://img.shields.io/badge/Redis-v7+-red)

## Demo
[Focason Lab](https://focason.com)

## Modules
| Module                   | Description                    | Port | Comment                                          |
|--------------------------|--------------------------------|------|--------------------------------------------------|
| focason-service-registry | Service Registry (Eureka)     | 8761 | Manages service discovery for microservices      |
| focason-config-server    | Configuration Server           | 8888 | Centralized configuration with Spring Cloud Bus  |
| focason-gateway          | API Gateway (WebFlux)         | 8080 | Handles routing, filtering, and request dispatch |
| focason-core             | Core Library                  | -    | Shared business logic, entities, and utilities   |
| focason-platform-service | Platform Service              | 8081 | Handles authentication, users, files, and notifications |

## Features
- **Service Discovery**: Manage microservice registration via Netflix Eureka
- **Centralized Configuration**: Unified configuration management with Spring Cloud Config and dynamic refresh via RabbitMQ
- **API Gateway**: Reactive API gateway built on Spring Cloud Gateway (WebFlux) with JWT authentication
- **Authentication & Authorization**: 
  - Email-based registration/login
  - Google OAuth2 login
  - JWT token management (access & refresh tokens)
  - Token validation and user verification
- **File Management**: 
  - AWS S3 integration for file storage
  - Presigned URL generation for secure uploads/downloads
  - File import/export with asynchronous processing via RabbitMQ
- **Messaging**: 
  - RabbitMQ integration for asynchronous communication
  - AWS SQS integration for email notifications
- **Caching**: Redis integration for session management and caching
- **Real-time Communication**: WebSocket support for real-time notifications
- **Database Migration**: Managed by Flyway
- **Code Generation**: Doma2 code generation for entities and DAOs (only `base00*` tables)
- **Code Formatting**: Spotless integration for consistent code style

## Prerequisites
* [Java 17](https://adoptium.net/) or later
* [Gradle 8.14+](https://gradle.org/install/) (or use Gradle Wrapper)
* [Git](https://git-scm.com/downloads)
* [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) (recommended)
* [Erlang](https://www.erlang.org/downloads) (for RabbitMQ)
* [RabbitMQ](https://www.rabbitmq.com/)
    - Management Console: http://localhost:15672 (default user: `guest` / `guest`)
* [Redis](https://redis.io/download)
* [Docker Desktop](https://www.docker.com/products/docker-desktop/) (optional, for containerization)
* [Postman](https://www.postman.com/downloads/) (optional, for API testing)
* [MySQL 8.0+](https://dev.mysql.com/downloads/mysql/)
* [AWS CLI](https://aws.amazon.com/cli/) (for AWS S3/SQS integration)

## Setup

### Database Initialization
1. Update `focason-core/gradle.properties` with your database connection details:
   ```properties
   DB_HOST=localhost
   DB_PORT=3306
   DB_SCHEMA=focason
   DB_USERNAME=root
   DB_PASSWORD=your_password
   ```

2. Create the database:
   ```sql
   CREATE DATABASE focason CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```

3. Run Flyway migrations:
   ```bash
   ./gradlew :focason-core:flywayInfo
   ./gradlew :focason-core:flywayMigrate
   ```

### Doma Code Generation
Generate entities and DAOs from database tables (only `base00*` tables):
```bash
./gradlew :focason-core:domaGen
```

### Application Configuration
Each service uses `bootstrap.yml` for configuration. Key environment variables:

**Common variables:**
- `DB_HOST`, `DB_PORT`, `DB_SCHEMA`, `DB_USERNAME`, `DB_PASSWORD` - Database connection
- `EUREKA_HOSTNAME` - Eureka server hostname (default: `localhost`)
- `RABBITMQ_HOST`, `RABBITMQ_USERNAME`, `RABBITMQ_PASSWORD` - RabbitMQ connection
- `REDIS_HOST`, `REDIS_PORT` - Redis connection
- `LOG_LEVEL` - Logging level (default: `INFO`)

**AWS Configuration (for platform-service):**
- `AWS_REGION` - AWS region (default: `ap-northeast-1`)
- `AWS_PROFILE` - AWS profile name (default: `default`)
- S3 bucket name and SQS queue URLs configured via Spring Cloud Config

## Start

### Local Development

1. **Start infrastructure services:**
   - MySQL (port 3306)
   - RabbitMQ (port 5672, management: 15672)
   - Redis (port 6379)

2. **Start services in order:**
   ```bash
   # 1. Service Registry (Eureka)
   ./gradlew :focason-service-registry:bootRun
   
   # 2. Config Server
   ./gradlew :focason-config-server:bootRun
   
   # 3. Platform Service
   ./gradlew :focason-platform-service:bootRun
   
   # 4. API Gateway
   ./gradlew :focason-gateway:bootRun
   ```

3. **Access services:**
   - Eureka Dashboard: http://localhost:8761
   - API Gateway: http://localhost:8080
   - Platform Service: http://localhost:8080/api/v1/* (via Gateway)

### Build Project
```bash
# Build all modules
./gradlew build

# Build specific module
./gradlew :focason-gateway:build
```

### Code Formatting
Before committing, format code to maintain consistency:
```bash
./gradlew spotlessApply
```

## Development

### Project Structure
```
focason-cloud-starter/
├── focason-core/              # Core library (shared entities, utilities, exceptions)
├── focason-service-registry/ # Eureka service registry
├── focason-config-server/     # Spring Cloud Config Server
├── focason-gateway/           # API Gateway (Spring Cloud Gateway)
└── focason-platform-service/ # Main business service
    ├── auth/                  # Authentication & authorization
    ├── user/                  # User management
    ├── file/                  # File upload/download/import/export
    ├── notification/          # Notification service
    └── aws/                   # AWS S3/SQS integration
```

### Key Technologies
- **Framework**: Spring Boot 3.2.0, Spring Cloud 2023.0.0
- **Database**: MySQL 8.0+, Doma2 ORM
- **Messaging**: RabbitMQ, AWS SQS
- **Storage**: AWS S3
- **Caching**: Redis
- **Security**: Spring Security, JWT (jjwt 0.12.3)
- **Code Generation**: Doma2 Gen (only `base00*` tables)
- **Code Quality**: Spotless (Eclipse formatter)

### API Gateway Routes
| Route Pattern                    | Target Service              | Description                |
|----------------------------------|------------------------------|----------------------------|
| `/api/v1/eureka/**`              | focason-service-registry     | Eureka dashboard           |
| `/api/v1/auth/**`                | focason-platform-service    | Authentication endpoints   |
| `/api/v1/users/**`               | focason-platform-service    | User management           |
| `/api/v1/files/**`               | focason-platform-service    | File operations            |
| `/api/v1/notifications/**`       | focason-platform-service    | Notifications              |

### Authentication Flow
1. Client sends login request to `/api/v1/auth/login`
2. Platform service validates credentials and returns JWT tokens
3. Client includes `Authorization: Bearer <token>` header in subsequent requests
4. Gateway validates JWT and injects user context (`X-User-Id`, `X-User-Email`) into headers
5. Downstream services use injected headers for authorization

## Release

### Docker Deployment

1. **Build Docker images:**
   ```bash
   docker build -t focason-cloud/service-registry:latest -f focason-service-registry/Dockerfile .
   docker build -t focason-cloud/config-server:latest -f focason-config-server/Dockerfile .
   docker build -t focason-cloud/gateway:latest -f focason-gateway/Dockerfile .
   docker build -t focason-cloud/platform-service:latest -f focason-platform-service/Dockerfile .
   ```

2. **Tag images:**
   ```bash
   docker tag focason-cloud/{module}:latest focason-cloud/{module}:{yyyymmdd}
   ```

3. **Update docker-compose.yml** with environment variables

4. **Start containers:**
   ```bash
   docker-compose up -d
   ```

### Push to AWS ECR

1. **Install AWS CLI V2** (if not already installed)

2. **Authenticate Docker to ECR:**
   ```bash
   aws ecr get-login-password --region ap-northeast-1 | docker login --username AWS --password-stdin 442426889980.dkr.ecr.ap-northeast-1.amazonaws.com
   ```

3. **Build, tag, and push:**
   ```bash
   docker build -t focason-cloud/{module}:latest -f {module}/Dockerfile .
   docker tag focason-cloud/{module}:latest 442426889980.dkr.ecr.ap-northeast-1.amazonaws.com/focason-cloud/{module}:latest
   docker push 442426889980.dkr.ecr.ap-northeast-1.amazonaws.com/focason-cloud/{module}:latest
   ```

### ECS Service Management
```bash
# Update service desired count
aws ecs update-service --cluster focason-cloud-cluster --service {service-name} --desired-count 1

# Services:
# - focason-service-registry
# - focason-config-server
# - focason-gateway
# - focason-platform-service
```

## Roadmap
- [ ] **User profile management** (update profile, avatar)
- [ ] **Password reset via email verification**
- [ ] **Role & permission management**
- [ ] **Centralized logging and monitoring** (ELK/Prometheus/Grafana)
- [ ] **CI/CD pipeline with GitHub Actions**
- [ ] **Deployment on AWS EKS** (Kubernetes)
- [ ] **API documentation** (Swagger/OpenAPI)
- [ ] **Unit and integration tests**

## Contributing
Contributions are welcome!  
Please follow the Git workflow:
1. Fork the repo
2. Create a feature branch (`git checkout -b feature/my-feature`)
3. Commit changes (`git commit -m 'Add new feature'`)
4. Run code formatting: `./gradlew spotlessApply`
5. Push to the branch (`git push origin feature/my-feature`)
6. Open a Pull Request

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
