package ru.lavcraft.microservices.fee;

import lombok.Builder;
import lombok.Data;
import ru.lavcraft.microservices.fee.domain.BasicUser;

/**
 * @author tolkv
 * @since 13/09/15
 */
@Data
@Builder
public class FeeResponse {

  private Fee fee;
  private BasicUser user;

  @Data
  @Builder
  public static class Fee {
    private int max;
    private int min;
    private String currency = "RUR";
  }
}
