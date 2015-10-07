package ru.lavcraft.microservices.fee.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.ObservableResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.lavcraft.microservices.fee.domain.OperationType;
import rx.Observable;

import static ru.lavcraft.microservices.fee.client.PaymentServiceClient.*;

/**
 * @author tolkv
 * @since 14/09/15
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HystrixPaymentServiceClient {
  public static final String UNDEFINED = "undefined";
  private final PaymentServiceClient paymentServiceClient;

  @HystrixCommand
  public Observable<PaymentInfo> getPaymentInfoForUser(Long userId, OperationType operationType) {
    return new ObservableResult<PaymentInfo>() {
      @Override
      public PaymentInfo invoke() {
        return paymentServiceClient.getPaymentInfo(userId, operationType);
      }
    };
  }
}
