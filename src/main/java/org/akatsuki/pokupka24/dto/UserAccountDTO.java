package org.akatsuki.pokupka24.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class UserAccountDTO {

    private UUID accountId;

    private UUID userId;

    private BigDecimal balance;

    private Boolean isActive;

}
