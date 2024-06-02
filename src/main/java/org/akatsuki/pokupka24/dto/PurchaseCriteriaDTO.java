package org.akatsuki.pokupka24.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.akatsuki.pokupka24.domain.entity.Product;
import org.akatsuki.pokupka24.domain.entity.UserAccount;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
public class PurchaseCriteriaDTO {

    private Set<UUID> purchaseIdIn;

    private Set<UUID> accountIdIn;

    private Set<UUID> productIdIn;

    private LocalDateTime createdAfter;

    private LocalDateTime createdBefore;
}
