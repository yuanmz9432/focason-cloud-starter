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
- [Release](#Release)
- [Roadmap](#roadmap)
- [Contributing](#contributing)
- [License](#license)

## Project Description
Focason Lab is a microservices-based backend system built with **Spring Cloud** and **Spring Boot**.  
It provides a service registry, API gateway, centralized configuration, authentication and authorization services, and core business modules.

This project demonstrates how to build, containerize, and deploy a distributed system using modern technologies such as Docker, RabbitMQ, and AWS ECS/ECR.

## Badges
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-v2021.0.5-brightgreen)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-v2.7.8-brightgreen)
![Gradle](https://img.shields.io/badge/Gradle-v6.9.1-lightgrey)
![Java](https://img.shields.io/badge/Amazon%20Corretto%20Java-11-orange)
![MySQL](https://img.shields.io/badge/MySQL-v8.4.6LTS-blue)

![Jupiter](https://img.shields.io/badge/Jupiter-v5.9.1-%230057b7)
![jsonwebtoken](https://img.shields.io/badge/jsonwebtoken-v0.9.1-brightgreen)
![Doma2](https://img.shields.io/badge/Doma2-v2.53.0-blue)
![Doma2 Gen](https://img.shields.io/badge/Doma2%20Gen-v2.28.0-blue)
![AWS CLI 2](https://img.shields.io/badge/AWS%20CLI%20-2-orange)
![Lombok](https://img.shields.io/badge/Lombok-v1.18.24-red)
![Spotless](https://img.shields.io/badge/spotless-v5.16.0-orange)
![RabbitMQ](https://img.shields.io/badge/RabbitMQ-v3.11.8-orange)

![Postman](https://img.shields.io/badge/Postman-v10.6.7-orange)
![Docker Desktop](https://img.shields.io/badge/Docker%20Desktop-v19.03.8-blue)
![Navicat](https://img.shields.io/badge/Navicat-16-yellow)
![Git](https://img.shields.io/badge/Git-v2.39.0-orange)
![Github Desktop](https://img.shields.io/badge/Github%20Desktop-v3.1.3-purple)
![Swagger OpenAPI](https://img.shields.io/badge/Swagger%20OpenAPI-v3.0.3-%2338b832)
![Termius](https://img.shields.io/badge/Termius-v9.32.2-darkblue)

## Demo
[Focason Lab](https://focason.com)

## Modules
| Module                | Description                | Port | Comment                                          |
|-----------------------|----------------------------|------|--------------------------------------------------|
| eureka-server         | Service Registry           | 8761 | Manages service discovery for microservices      |
| configure-server      | Configuration Server       | 8888 | Centralized configuration with Spring Cloud Bus  |
| gateway-service       | API Gateway                | 8080 | Handles routing, filtering, and request dispatch |
| focason-core          | Core Service               |  -   | Manages shared business logic and resources      |
| authentication-server | Authentication Service     | 8085 | Handles user login, registration, and OAuth2     |

## Features
- **Service Discovery**: Manage microservice registration via Eureka.
- **Centralized Configuration**: Unified configuration management and dynamic refresh.
- **API Gateway**: Routing, filtering, and load balancing.
- **Authentication & Authorization**: Email-based registration/login, Google OAuth login, and role-based access control.
- **Core Business Module**: Common resource management.
- **Messaging**: RabbitMQ integration for asynchronous communication.
- **Database Migration**: Managed by Flyway.
- **API Documentation**: Swagger OpenAPI support.

## Prerequisites
* [Java Amazon Corretto 11](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html)
* [Git](https://git-scm.com/downloads)
* [Github Desktop](https://desktop.github.com/)
* [IntelliJ IDEA](https://www.jetbrains.com/zh-cn/idea/download/#section=mac)
* [Erlang](https://www.erlang.org/downloads)
* [RabbitMQ](https://www.rabbitmq.com/)
    - Management Console: http://localhost:15672 (default user: `guest` / `guest`)
* [Docker Desktop](https://www.docker.com/products/docker-desktop/)
* [Postman](https://www.postman.com/downloads/)
* [MySQL 8.4.6LTS](https://dev.mysql.com/downloads/mysql/)

## Setup
### Database Initialization
* Update `gradle.properties` in `focason-core` with your database connection details.  
  This will automatically replace the DB settings in `build.gradle` (Flyway and Doma2).
* Create the database:
  ```sql
  CREATE DATABASE focason;
  ```
* Manage schema with Flyway:
  ```bash
  ./gradlew flywayInfo
  ./gradlew flywayClean
  ./gradlew flywayMigrate
  ```

### Application Configuration
* Update `application.yml` with your DB connection:
  ```yaml
  datasource:
    url: jdbc:mysql://${DB_HOST:localhost}:${DB_PORT:3306}/${DB_SCHEMA:focason}?useSSL=false&autoReconnect=true&characterEncoding=UTF-8&useUnicode=yes
    username: ${DB_USERNAME:root}
    password: ${DB_PASSWORD:password}
  ```

## Start
### Local Development
* Build the project
   ```bash
   ./gradlew build
   ```
* Start each service:
  ```bash
  cd {MODULE_FOLDER}
  ../gradlew bootRun
  ```
  Services:
    * eureka-server
    * configure-server
    * gateway-service
    * authentication-server

* Visit Eureka dashboard:
    * http://localhost:8761

* Please make sure to run the following command to format the code before commit, in order to maintain code consistency and cleanliness.
  ```bash
  ./gradlew spotlessApply
  ```

### Docker Deployment
* Update the `Dockerfile`.
    ```
    ENV APP_PORT=80
    ENV DB_HOST=localhost
    ENV DB_PORT=3306
    ENV DB_SCHEMA=focason
    ENV DB_USERNAME=root
    ENV DB_PASSWORD=secret
    ```

* Build images
    ```bash
    docker build -t focason-cloud/eureka:latest .
    docker build -t focason-cloud/configure:latest .
    docker build -t focason-cloud/gateway:latest .
    docker build -t focason-cloud/auth:latest .
    ```

* Add tag to image
    ```bash
    docker tag focason-{module}:latest focason-{module}:{yyyymmdd}
    ```

* Update docker-compose file with environment variables.

* Start containers:
    ```bash
    docker-compose up
    ```

## Release
### Push images to AWS ECR
* Install [AWS CLI V2](https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html)
* Authenticate Docker to ECR:
    ```shell
    aws ecr get-login-password --region ap-northeast-1 | docker login --username AWS --password-stdin 442426889980.dkr.ecr.ap-northeast-1.amazonaws.com
    ```
* Build, tag, and push image:
   ```bash
   docker build -t focason-cloud/eureka:latest .
   docker tag focason-cloud/eureka:latest 442426889980.dkr.ecr.ap-northeast-1.amazonaws.com/focason-cloud/eureka:latest
   docker push 442426889980.dkr.ecr.ap-northeast-1.amazonaws.com/focason-cloud/eureka:latest
   ```
* Automate with scripts:
   ```shell
   sh 001_build_images.sh
   sh 002_push_images.sh
   ```

### ECS service management
* eureka-server
  ```shell
  aws ecs update-service --cluster focason-cloud-cluster --service eureka-server --desired-count 1
  ```
* rabbitmq-server
  ```shell
  aws ecs update-service --cluster focason-cloud-cluster --service rabbitmq-server --desired-count 1
  ```
* configure-server
  ```shell
  aws ecs update-service --cluster focason-cloud-cluster --service configure-server --desired-count 1
  ```
* gateway-service
  ```shell
  aws ecs update-service --cluster focason-cloud-cluster --service gateway-service --desired-count 1
  ```
* authentication-service
  ```shell
  aws ecs update-service --cluster focason-cloud-cluster --service auth-service --desired-count 1
  ```

## Roadmap
- [ ] **User profile management (update profile, avatar)**

- [ ] **Password reset via email verification**

- [ ] **Role & permission management**

- [ ] **Centralized logging and monitoring (ELK/Prometheus/Grafana)**

- [ ] **CI/CD pipeline with GitHub Actions**

- [ ] **Deployment on AWS EKS (Kubernetes)**

## Contributing
Contributions are welcome!  
Please follow the Git workflow:
1. Fork the repo
2. Create a feature branch (`git checkout -b feature/my-feature`)
3. Commit changes (`git commit -m 'Add new feature'`)
4. Push to the branch (`git push origin feature/my-feature`)
5. Open a Pull Request

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
