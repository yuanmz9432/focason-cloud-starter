# Focason Config Server

## Introduction
`focason-config-server` is a configuration center service built on **Spring Cloud Config**.

In a microservices architecture, it is responsible for the unified management of externalized configuration for all downstream services. It allows for dynamic changes to application configurations without requiring service redeployment, while leveraging a version control system (like Git) to manage configuration history and environment segregation.

## Key Features

* **Centralized Configuration Management:** All client service configurations (e.g., Gateway, business services) are stored centrally in a Git repository and served by this Config Server.
* **Environment Isolation Support:** Easily achieve configuration separation for different environments (e.g., `dev`, `test`, `prod`) using Spring Boot Profiles.
* **Version Control Integration:** Configuration modifications, rollbacks, and auditing are managed through the integrated Git repository, ensuring traceability and security.
* **High Availability (Optional):** Can be registered with a service registry (like Eureka) to achieve load balancing and high availability.

## Accessing Configuration

Once the application is running, you can access the configuration for client services using the following URL format:

Format: `/{application}/{profile}/{label}`

**Example URL:**

To retrieve the configuration for the `focason-gateway` service under the `dev` profile from the `main` branch, access:

```text
http://localhost:8888/focason-service-user/dev/main
```

## Configuration Guide
The core configuration is located in `src/main/resources/application.yml`.

1. Configure the Git Repository

    You must specify the remote Git repository address that the Config Server will use to fetch configuration files.
    ```yaml
    # application.yml Example
    server:
      port: 8888 # Default port for Config Server
    
    spring:
      application:
        name: focason-config-server
    cloud:
      config:
        server:
          git:
            # REQUIRED: The URI of the Git repository storing configurations
            uri: ${GIT_URI:[https://github.com/your-org/config-repo.git](https://github.com/your-org/config-repo.git)}

            # OPTIONAL: Credentials if the Git repository requires authentication
            # username: ${GIT_USERNAME} 
            # password: ${GIT_PASSWORD} 
            
            # OPTIONAL: Search paths within the repository
            search-paths: 
              - 'config-data'
              - 'shared-config'
    ```
2. Eureka Service Registry Integration <br/>To integrate the Config Server with a Eureka Discovery Server for high availability and service lookup:
   1. **Dependency Check:** Ensure the `spring-cloud-starter-netflix-eureka-client` dependency is included in your project's build file (e.g., `pom.xml`)

   2. **Configuration:** Add the following configuration to your `application.yml` to point to the Eureka Server:
      ```yaml
      # application.yml continuation
      eureka:
        client:
          service-url:
            # REQUIRED: Set the URL of your Eureka Server
            defaultZone: ${EUREKA_URI:http://localhost:8761/eureka}
      instance:
        # Optional: Custom instance ID for clarity
        instance-id: ${spring.application.name}:${server.port}
        # Recommended for Docker/Cloud environments to ensure clients connect via IP
        prefer-ip-address: true
      ```
   3. **Main Application Class Annotation:** Ensure the main class (e.g., `FocasonConfigServerApplication.java`) is annotated with `@EnableDiscoveryClient` to activate the service registration process:
      ```java
      // Example Main Class
      import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
      import org.springframework.cloud.config.server.EnableConfigServer;
      // ...
      
      @SpringBootApplication
      @EnableConfigServer
      @EnableDiscoveryClient // <-- This is required for Eureka registration
      public class FocasonConfigServerApplication {
      // ...
      }
      ```



