package ru.lavcraft.microservices.fee;

import lombok.Builder;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author tolkv
 * @since 13/09/15
 */
@EnableFeignClients
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class PaymentApplication {
  public static void main(String[] args) {
    SpringApplication.run(PaymentApplication.class, args);
  }
}

@RestController
@RequestMapping("/payments")
class PaymentController {

  public static final String ACCESS_FULL = "FULL";

  @RequestMapping(value = "/check", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity getPaymetnPermission(@RequestParam Long userId, @RequestParam Optional<OperationType> operationType) {
    if (userId == 100)
      throw new RuntimeException("UserIsBlocked");

    return ResponseEntity.ok(Response.builder()
        .additionalFee(Response.Fee.builder()
            .max(1000)
            .min(100)
            .currency("RUR")
            .build())
        .access(ACCESS_FULL)
        .operationType(operationType.orElse(OperationType.EXTERNAL))
        .build());
  }
}

@Data
@Builder
class Response {
  private Fee additionalFee;
  private OperationType operationType;
  private String access;

  @Data
  @Builder
  public static class Fee {
    private int max;
    private int min;
    private String currency;
  }
}

enum OperationType {
  EXTERNAL,
  INTERNAL,
  CUSTOM,
  INTERNATIONAL
}