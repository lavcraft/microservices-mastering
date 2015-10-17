package ru.lavcraft.microservices.fee.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.ObservableResult;
import info.developerblog.services.user.*;
import info.developerblog.spring.thrift.annotation.ThriftClient;
import lombok.RequiredArgsConstructor;
import org.apache.thrift.TException;
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

  @ThriftClient("user-service")
  TUserService.Client tUserServiceClient;

  @HystrixCommand(fallbackMethod = "getTDefaultUser")
  public Observable<TUser> getTUser(String token) {
    return new ObservableResult<TUser>() {
      @Override
      public TUser invoke() {
        try {
          return tUserServiceClient.getUserById(new TAuthToken(token));
        } catch (TUserNotFoundException e) {
          return getTDefaultUser(token);
        } catch (TException e) {
          throw new RuntimeException(e);
        }
      }
    };
  }

  private TUser getTDefaultUser(String token){
    return new TUser().setLastname(UNDEFINED).setFirstname(UNDEFINED);
  }
}
