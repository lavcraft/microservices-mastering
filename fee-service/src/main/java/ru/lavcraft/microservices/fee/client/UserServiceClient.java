package ru.lavcraft.microservices.fee.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.lavcraft.microservices.fee.domain.BasicUser;

/**
 * @author tolkv
 * @since 14/09/15
 */
@FeignClient("user")
public interface UserServiceClient {
  @RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
  BasicUser getUserInfo(@PathVariable("id") Long id);
}
