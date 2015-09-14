package ru.lavcraft.microservices.fee;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.lavcraft.microservices.fee.client.HystrixUserServiceClient;
import ru.lavcraft.microservices.fee.domain.BasicUser;

import java.util.Optional;

/**
 * @author tolkv
 * @since 13/09/15
 */
@Slf4j
@RestController
@RequestMapping("/fee")
public class FeeController {

  @Autowired
  HystrixUserServiceClient userServiceClient;

  @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public FeeResponse fee(@RequestBody FeeRequest request) {
    log.info("fee request={}", request);

    long userId = Optional.ofNullable(request.getUser())
        .orElseThrow(() -> new RuntimeException("UserNotFound"))
        .getId();


    BasicUser userInfo = userServiceClient.getUserById(userId).toBlocking().single();
    log.info("userInfo = {}", userInfo);
    return FeeResponse.builder()
        .user(userInfo)
        .fee(resolveFee())
        .build();
  }




  private FeeResponse.Fee resolveFee() {
    return FeeResponse.Fee.builder()
        .max(100)
        .min(200)
        .build();
  }
}
