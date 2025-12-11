package com.focason.configure;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Focason Configure Server
 *
 * @since 0.0.1
 * @author yuanmz9432
 */
@EnableConfigServer
@EnableEurekaClient
@SpringBootApplication
public class ConfigureApplication
{
    public static void main(String[] args) {
        SpringApplication.run(ConfigureApplication.class, args);
    }
}
