package org.akatsuki.pokupka24.dto;

import lombok.Data;
import org.akatsuki.pokupka24.dictionary.AccountCurrency;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class UserAccountDTO {

    private UUID accountId;

    private UUID userId;

    private BigDecimal balance;

    private AccountCurrency currency;

    private Boolean isActive;

}
