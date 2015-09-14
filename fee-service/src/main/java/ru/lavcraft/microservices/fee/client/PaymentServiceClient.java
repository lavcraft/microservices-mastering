package ru.lavcraft.microservices.fee.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.lavcraft.microservices.fee.domain.OperationType;

/**
 * @author tolkv
 * @since 14/09/15
 */
@FeignClient("payment")
public interface PaymentServiceClient {
  @RequestMapping(method = RequestMethod.GET, value = "/payments/check")
  PaymentInfo getPaymentInfo(@RequestParam("userId") Long userId,
                        @RequestParam("operationType") OperationType operationType);

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  class PaymentInfo {
    private Fee additionalFee;
    private OperationType operationType;
    private String access;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Fee {
      private int max;
      private int min;
      private String currency;
    }
  }
}
