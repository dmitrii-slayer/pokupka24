package org.akatsuki.pokupka24.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "purchase_id", nullable = false)
    private UUID purchaseId;

//    @ManyToOne(targetEntity = UserAccount.class)
//    @Column(name = "account_id", nullable = false)
//    private UUID accountId;

    // покупка связана только к account? не ссылается на id пользователя?
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private UserAccount userAccount;

//    @ManyToOne
//    @Column(name = "product_id", nullable = false)
//    private UUID productId;

// сейчас 1 покупка = 1 товар, мб сделать отдельную таблицу purchase_detail?
// 1 purchase может содержать несколько товаров (несколько строк в таблице purchase_detail)
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // статус покупки? DONE/SUCCESS, PENDING, FAILED?
}