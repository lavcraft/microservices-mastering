package ru.lavcraft.microservices.fee.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.ObservableResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.lavcraft.microservices.fee.domain.BasicUser;
import rx.Observable;

/**
 * @author tolkv
 * @since 14/09/15
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HystrixUserServiceClient {
  public static final String UNDEFINED = "undefined";
  private final UserServiceClient userServiceClient;

  @HystrixCommand(fallbackMethod = "getDefaultUser")
  public Observable<BasicUser> getUserById(Long id) {
    return new ObservableResult<BasicUser>() {
      @Override
      public BasicUser invoke() {
        return userServiceClient.getUserInfo(id);
      }
    };
  }

  private BasicUser getDefaultUser(Long id){
    return BasicUser.builder().firstname(UNDEFINED).lastname(UNDEFINED).build();
  }
}
