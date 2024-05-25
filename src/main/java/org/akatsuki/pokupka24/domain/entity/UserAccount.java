package org.akatsuki.pokupka24.domain.entity;

import jakarta.persistence.*;
import org.akatsuki.pokupka24.dictionary.Currency;

import java.util.UUID;

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
    private Double balance;

    @Column(name = "currency", nullable = false)
    private Currency currency;

    @Column(name = "active", nullable = false)
    private Boolean active;
}
