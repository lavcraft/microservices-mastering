package ru.lavcraft.microservices.eureka;

import info.developerblog.spring.thrift.api.gateway.annotation.EnableThriftGateway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author tolkv
 * @since 13/09/15
 */
@EnableZuulProxy
@EnableDiscoveryClient
@EnableThriftGateway
@SpringBootApplication
public class ZuulServerApplication {
  public static void main(String[] args) {
    SpringApplication.run(ZuulServerApplication.class, args);
  }
}
