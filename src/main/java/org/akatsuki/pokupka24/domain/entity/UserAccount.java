package org.akatsuki.pokupka24.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user_account")
// account = счёт или аккаунт пользователя? разобраться что за сущность
public class UserAccount {

    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "account_id", nullable = false)
    private UUID accountId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @PositiveOrZero
    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @OneToMany(mappedBy = "userAccount")
    @OrderBy("created_at DESC")
    Set<Purchase> purchases = new LinkedHashSet<>();
}
