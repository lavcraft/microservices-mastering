package ru.lavcraft.microservices.fee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lavcraft.microservices.fee.domain.BasicUser;
import ru.lavcraft.microservices.fee.domain.OperationType;

/**
 * @author tolkv
 * @since 13/09/15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeeRequest {
  private BasicUser user;
  private OperationType operationType;
}
