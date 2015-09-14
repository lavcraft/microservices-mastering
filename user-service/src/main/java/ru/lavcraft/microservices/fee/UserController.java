package ru.lavcraft.microservices.fee;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.lavcraft.microservices.fee.domain.EnhancedUser;

import java.util.Random;

/**
 * @author tolkv
 * @since 13/09/15
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
  @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity user(@PathVariable Integer id) {
    if (id != 1)
      return ResponseEntity.notFound().build();
    return ResponseEntity.ok().body(EnhancedUser.builder()
        .id(id)
        .firstname("Test")
        .lastname("Testovich")
        .balance(EnhancedUser.Balance.builder()
            .amount(new Random().nextInt())
            .currency("RUR")
            .build())
        .build());
  }
}
