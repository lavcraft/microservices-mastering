package ru.lavcraft.microservices.fee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author tolkv
 * @since 13/09/15
 */
@EnableHystrix
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = { "ru.lavcraft", "info.developerblog" })
public class FeeApplication {
  public static void main(String[] args) {
    SpringApplication.run(FeeApplication.class, args);
  }
}
