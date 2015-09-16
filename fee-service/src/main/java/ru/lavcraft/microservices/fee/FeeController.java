package ru.lavcraft.microservices.fee;

import info.developerblog.services.user.TUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.lavcraft.microservices.fee.client.HystrixPaymentServiceClient;
import ru.lavcraft.microservices.fee.client.HystrixUserServiceClient;
import ru.lavcraft.microservices.fee.client.PaymentServiceClient.PaymentInfo;
import ru.lavcraft.microservices.fee.domain.BasicUser;
import rx.Observable;

import java.util.Optional;

/**
 * @author tolkv
 * @since 13/09/15
 */
@Slf4j
@RestController
@RequestMapping("/fee")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FeeController {
  private final HystrixUserServiceClient userServiceClient;
  private final HystrixPaymentServiceClient paymentServiceClient;

  @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public FeeResponse fee(@RequestBody FeeRequest request) {
    log.info("fee request={}", request);

    long userId = Optional.ofNullable(request.getUser())
        .orElseThrow(() -> new RuntimeException("UserNotFound"))
        .getId();


    return Observable.zip(
//        userServiceClient.getUserById(userId),
//        paymentServiceClient.getPaymentInfoForUser(userId, request.getOperationType()),
//        (BasicUser userInfo, PaymentInfo paymentInfo) -> FeeResponse.builder()
//            .user(userInfo)
//            .fee(resolveFee(paymentInfo))
//            .build()
        userServiceClient.getTUserById(userId),
        paymentServiceClient.getPaymentInfoForUser(userId, request.getOperationType()),
        (TUser userInfo, PaymentInfo paymentInfo) -> FeeResponse.builder()
                .user(BasicUser.builder()
                        .id(userInfo.getId())
                        .firstname(userInfo.getFirstname())
                        .lastname(userInfo.getLastname())
                        .balance(BasicUser.Balance.builder()
                                .amount((int)userInfo.getBalance().getAmount())
                                .build()
                        )
                        .build()
                )
                .fee(resolveFee(paymentInfo))
                .build()
    ).doOnError(throwable -> {
      Throwable cause = throwable.getCause();
      log.error("Error: ", cause);
      throw new RuntimeException(cause);
    }).onErrorReturn(throwable1 -> FeeResponse.builder()
            .fee(FeeResponse.Fee.builder()
                    .max(0)
                    .min(0)
                    .currency("UNDEFINED")
                    .build())
            .build())
        .toBlocking()
        .single();
  }


  private static FeeResponse.Fee resolveFee(PaymentInfo paymentInfo) {
    return FeeResponse.Fee.builder()
        .max(paymentInfo.getAdditionalFee().getMax() + 10)
        .min(paymentInfo.getAdditionalFee().getMin() + 10)
        .currency(paymentInfo.getAdditionalFee().getCurrency())
        .build();
  }
}
