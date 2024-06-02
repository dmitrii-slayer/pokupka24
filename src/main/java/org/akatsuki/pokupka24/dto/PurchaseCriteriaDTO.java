package org.akatsuki.pokupka24.dto;

import lombok.Data;

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
