package ru.lavcraft.microservices.fee.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @author tolkv
 * @since 13/09/15
 */
@Data
@Builder
public class EnhancedUser {
  private long id;
  private String lastname;
  private String firstname;
  private Balance balance;

  @Data
  @Builder
  public static class Balance {
    private String currency = "RUR";
    private int amount;
  }
}
