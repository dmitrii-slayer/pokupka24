package org.akatsuki.pokupka24.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "purchase_id", nullable = false)
    private UUID purchaseId;

    // покупка связана только к account? не ссылается на id пользователя?
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private UserAccount userAccount;

//    @OneToMany(
////            targetEntity = PurchaseDetail.class,
//            mappedBy = "purchase",
//            fetch = FetchType.EAGER
////            cascade = CascadeType.ALL
//    )
//    private Set<PurchaseDetail> purchaseDetails = new HashSet<>();
//    @ElementCollection
//    @CollectionTable(name = "product")
//    @AttributeOverride(
//            name = "",
//            column = @Column())
//    private Set<Product> products = new HashSet<>();

    @OneToMany
    @JoinTable(name = "purchase_detail",
            joinColumns = @JoinColumn(
                    name = "purchase_id"
//                    referencedColumnName = "purchase_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "product_id"
//                    referencedColumnName = "product_id"
            )
    )
//    @JoinTable
    private Set<Product> products = new HashSet<>();
//    public Set<Product> getProducts() {
//        return purchaseDetails.stream().map(PurchaseDetail::getProduct)
//                .collect(Collectors.toSet());
//    }

    @PastOrPresent
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // статус покупки? DONE/SUCCESS, PENDING, FAILED?
}