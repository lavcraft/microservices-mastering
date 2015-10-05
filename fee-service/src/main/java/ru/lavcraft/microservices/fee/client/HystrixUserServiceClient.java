package ru.lavcraft.microservices.fee.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.ObservableResult;
import info.developerblog.services.user.TAuthData;
import info.developerblog.services.user.TUser;
import info.developerblog.services.user.TUserNotFoundException;
import info.developerblog.services.user.TUserService;
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

  @ThriftClient
  TUserService.Client tUserServiceClient;

  @HystrixCommand(fallbackMethod = "getDefaultUser")
  public Observable<BasicUser> getUserById(Long id) {
    return new ObservableResult<BasicUser>() {
      @Override
      public BasicUser invoke() {
        return userServiceClient.getUserInfo(id);
      }
    };
  }

  @HystrixCommand(fallbackMethod = "getTDefaultUser")
  public Observable<TUser> getTUserById(Long id) {
    return new ObservableResult<TUser>() {
      @Override
      public TUser invoke() {
        try {
          return tUserServiceClient.getUserById(new TAuthData(id));
        } catch (TUserNotFoundException e) {
          return getTDefaultUser(id);
        } catch (TException e) {
          throw new RuntimeException(e);
        }
      }
    };
  }

  private BasicUser getDefaultUser(Long id){
    return BasicUser.builder().firstname(UNDEFINED).lastname(UNDEFINED).build();
  }

  private TUser getTDefaultUser(Long id){
    return new TUser().setLastname(UNDEFINED).setFirstname(UNDEFINED);
  }
}
