package com.viki3d.spring.cloud.service.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka server - Microservices Dynamic Registration and Discovery.
 *
 * <p><a href="http://localhost:8761/">test link</a></p>
 */
@SpringBootApplication
@EnableEurekaServer
public class Main {
  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }
}
