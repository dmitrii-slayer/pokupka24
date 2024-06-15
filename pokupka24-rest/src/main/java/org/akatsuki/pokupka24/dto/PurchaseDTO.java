package org.akatsuki.pokupka24.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
public class PurchaseDTO {

    private UUID purchaseId;

    // DTO или id?
    private UserAccountDTO userAccount;

    // DTO или id?
    private Set<ProductDTO> products;

    private LocalDateTime createdAt;

}
