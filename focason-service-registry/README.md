# Focason Service Registry

The Service Registry is a fundamental component of the Focason Cloud microservice architecture by Eureka. It provides a
central registry where client services (producers) can register themselves, and other services (consumers) can discover
them for dynamic, load-balanced communication.

This setup is configured for a standalone environment, intentionally disabling client functionality on the server
itself.

## 1. Core Configuration

The Eureka Server is configured via `src/main/resources/application.yml` and utilizes environment variables for
sensitive or environment-specific values.

### Key Features:

* **Service Registry**: Manages service instance registration and heartbeats.

* **Disabled Client Mode**: `register-with-eureka: false` and `fetch-registry: false` ensure this instance acts purely
  as a Server, not a Client.

* **Security Configuration**: The `WebSecurityConfigure.java` disables CSRF and default basic authentication for the
  Eureka dashboard for simplicity, but the client registration endpoint (`defaultZone`) still supports Basic Auth via
  credentials defined in the environment variables.

### Environment Variables

The following variables are available for configuring the Eureka Server instance:

| Environment Variable    | Default Value  | Description                                                      |
|-------------------------|:---------------|:-----------------------------------------------------------------|
| SERVER_PORT             | 8761           | The port the Eureka server will run on.                          |
| APPLICATION_NAME        | eureka-service | The application name registered in the Spring Cloud context.     |
| EUREKA_SERVICE_USERNAME | root           | Username for Basic Auth when clients register (YAML configured). |
| EUREKA_SERVICE_PASSWORD | password       | Password for Basic Auth when clients register (YAML configured). |
| ENVIRONMENT             | development    | Environment identifier (e.g., development, staging, production). |
| INSTANCE_HOSTNAME       | localhost      | The hostname used for the Eureka client's default zone URL.      |
| LOG_LEVEL               | INFO           | The log level for eureka service.                                |

## 2. Access and Credentials

The Eureka Server dashboard provides a visual interface to monitor registered services.

* **Dashboard URL**: http://localhost:8761

* **Default Credentials (for Client Registration)**:

    * **Username**: `root`

    * **Password**: `password`

## 3. Client Quick Start Guide

To enable a microservice to register with this Eureka Server, follow these steps:

### Step 1: Add Dependency

Add the necessary Eureka Client dependency to your project's build file:

```yaml
// build.gradle (Groovy)
implementation 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-client'

// build.gradle.kts (Kotlin)
implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
```

### Step 2: Configure Application

Add the client configuration to your service's `application.yaml` file. This includes setting the discovery URL and
ensuring the instance is registered by IP for consistency.

```yaml
# application.yaml
eureka:
  environment: ${ENVIRONMENT:development} # Deployment environment (development/production)
  instance:
    hostname: ${INSTANCE_HOSTNAME:localhost} # Hostname for this Eureka instance
    # prefer-ip-address: true # Display as IP address instead of hostname (default: false)
    # instance-id: ${LOCAL_IPV4}:${server.port} # Custom instance display format (e.g., 192.168.0.1:8080)
  client:
    service-url:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/ # Eureka Server URL for service registration
    register-with-eureka: true # Register this instance with Eureka Server (default: true)
    fetch-registry: false # Fetch service registry from Eureka Server (default: true, disabled for standalone server)
  server:
    peer-node-connect-timeout-ms: 1000 # Connection timeout for peer node communication in milliseconds
```

### Step 3: Enable Eureka Client

Ensure your main Spring Boot application class is annotated with `@EnableDiscoveryClient` or `@EnableEurekaClient`.

```java
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDiscoveryClient
@SpringBootApplication
public class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}
```