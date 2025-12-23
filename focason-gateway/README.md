## Focason Gateway

The `focason-gateway` module is the API gateway for the Focason Cloud platform.  
It is built on **Spring Cloud Gateway (WebFlux)** and acts as the single entry point for all external HTTP traffic.  
The gateway is responsible for routing, authentication, security, logging, and error handling.

---

### 1. Responsibilities

- **Single entry point** for frontend and external clients
- **JWT-based authentication** and user validation
- **Header propagation** of user identity to downstream services
- **Centralized logging** and error handling
- **Routing** to:
  - `focason-service-registry` (Eureka)
  - `focason-platform-service` (core business APIs)
- **CORS configuration** for web frontends

---

### 2. Key Components

- **`FocasonGatewayApplication`**  
  Main Spring Boot application entry point.

- **`GatewayConfig`**  
  - Defines routing rules via `RouteLocator`:
    - `/api/v1/eureka/**` → `lb://focason-service-registry`
    - `/api/v1/notifications/**`, `/api/v1/auth/**`, `/api/v1/users/**`, `/api/v1/files/**` → `lb://focason-platform-service`
  - Configures global CORS (`CorsWebFilter`).
  - Registers HTTP message converters (`HttpMessageConverters`) for JSON and other formats.

- **`AuthenticationTokenFilter`**  
  - Global filter for **JWT authentication**.
  - Workflow:
    1. Check if the request path matches a **whitelist pattern** (no auth required).
    2. Validate the `Authorization` header (presence and JWT structure).
    3. Decode JWT payload to extract:
       - `sub` (user ID)
       - `email`
       - `exp` (expiration time)
    4. Validate token expiration.
    5. Call Auth Service via Feign to verify whether the user is still **legal and active**.
    6. Inject user identity into headers:
       - `X-User-Id`
       - `X-User-Email`
    7. Forward the request to downstream services.

- **`GlobalLogFilter`**  
  - Logs incoming requests, response status, and latency for observability and debugging.

- **`GatewayExceptionHandler`**  
  - Global exception handler at the gateway layer.
  - Converts internal errors into consistent, client-friendly error responses.

- **`FeignExceptionHandler`**  
  - Captures and logs exceptions thrown by Feign clients used within the gateway.

---

### 3. Configuration (`bootstrap.yml` / `application.yml`)

Key environment variables (via `application.yml` or environment):

| Variable              | Default              | Description                                        |
|-----------------------|----------------------|----------------------------------------------------|
| `SERVER_PORT`         | `8080`               | HTTP port for the gateway                          |
| `APPLICATION_NAME`    | `gateway-service`    | Spring application name / Eureka service ID        |
| `EUREKA_HOSTNAME`     | `localhost`          | Hostname of Eureka Server                          |
| `RABBITMQ_HOST`       | `localhost`          | RabbitMQ host                                      |
| `RABBITMQ_USERNAME`   | `guest`              | RabbitMQ username                                  |
| `RABBITMQ_PASSWORD`   | `guest`              | RabbitMQ password                                  |
| `CONFIG_SERVICE_ID`   | `configure-server`   | Service ID of Spring Cloud Config Server           |
| `LOG_LEVEL`           | `INFO`               | Root logging level                                 |

---

### 4. Routing Overview (`GatewayConfig`)

| Route ID                   | Path Pattern                                                                                      | Target Service (Eureka ID)       | Applied Filter                |
|----------------------------|:--------------------------------------------------------------------------------------------------|:---------------------------------|-------------------------------|
| `focason-service-registry` | `/api/v1/eureka/**`                                                                               | `lb://focason-service-registry` | `AuthenticationTokenFilter`   |
| `focason-platform-service` | `/api/v1/notifications/**`<br>`/api/v1/auth/**`<br>`/api/v1/users/**`<br>`/api/v1/files/**`       | `lb://focason-platform-service` | `AuthenticationTokenFilter`   |

---

### 5. Authentication & Whitelist

#### 5.1 Authentication Flow (`AuthenticationTokenFilter`)

1. **Whitelist check** – if the request path is whitelisted, bypass authentication.
2. **Token presence** – ensure the `Authorization` header exists.
3. **JWT parsing** – validate token structure and decode payload.
4. **Expiration check** – ensure the token has not expired.
5. **User validation** – call Auth Service via Feign to confirm user is still valid (not disabled or deleted).
6. **Header injection** – add `X-User-Id` and `X-User-Email` to the outgoing request headers.

#### 5.2 Whitelist Patterns (`AUTH_WHITE_PATTERNS`)

Requests to the following paths do **not** require a valid access token:

- `/api/v1/auth/register`
- `/api/v1/auth/login`
- `/api/v1/auth/refresh-token`
- `/api/v1/auth/verify-code`
- `/api/v1/auth/oauth/**` (all OAuth callbacks)
- `/api/v1/eureka.*` (all Eureka-related endpoints)

---

### 6. CORS

Configured in `GatewayConfig` via `CorsWebFilter`:
- Allows trusted frontend origins (e.g. local development, production domain).
- Allows common HTTP methods: `GET`, `POST`, `PUT`, `DELETE`, `OPTIONS`.
- Allows all headers and supports credentials.

---

### 7. Quick Start

1. **Start dependencies**  
   Make sure the following services are running:
   - Config Server: `focason-config-server`
   - Service Registry (Eureka): `focason-service-registry`

2. **Configure environment variables**  
   Set the environment variables for RabbitMQ, Eureka, and Config Server as needed.

3. **Build & run**  

   ```bash
   # From project root
   ./gradlew :focason-gateway:clean :focason-gateway:build

   # Run with Gradle
   ./gradlew :focason-gateway:bootRun
   ```

   Or run the generated jar:

   ```bash
   java -jar focason-gateway/build/libs/focason-gateway-*.jar
   ```

4. **Access**  
   Gateway will listen on `http://localhost:8080` (or the port defined by `SERVER_PORT`).

