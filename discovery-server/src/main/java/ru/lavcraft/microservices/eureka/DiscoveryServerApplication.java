package ru.lavcraft.microservices.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author tolkv
 * @since 13/09/15
 */
@EnableEurekaServer
@SpringBootApplication
public class DiscoveryServerApplication {
  public static void main(String[] args) {
    SpringApplication.run(DiscoveryServerApplication.class, args);
  }
}
