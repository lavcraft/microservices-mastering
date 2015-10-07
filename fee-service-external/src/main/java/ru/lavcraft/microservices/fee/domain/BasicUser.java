package ru.lavcraft.microservices.fee.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tolkv
 * @since 13/09/15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasicUser {
  private Long id;
  private String lastname;
  private String firstname;
  private Balance balance;

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Balance {
    private Integer amount;
  }
}
