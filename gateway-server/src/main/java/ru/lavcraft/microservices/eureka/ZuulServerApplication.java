package ru.lavcraft.microservices.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author tolkv
 * @since 13/09/15
 */
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class ZuulServerApplication {
  public static void main(String[] args) {
    SpringApplication.run(ZuulServerApplication.class, args);
  }
}
