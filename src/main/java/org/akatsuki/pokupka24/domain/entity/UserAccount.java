package org.akatsuki.pokupka24.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.akatsuki.pokupka24.dictionary.AccountCurrency;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user_account")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "account_id", nullable = false)
    private UUID accountId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "currency", nullable = false)
//    @Size(min = 3, max = 3) для String а не AccountCurrency
    @Enumerated(EnumType.STRING)
    // какой тип выбрать: String или мой Enum?
    private AccountCurrency currency;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
}
