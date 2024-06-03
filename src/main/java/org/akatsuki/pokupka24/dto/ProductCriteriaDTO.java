package org.akatsuki.pokupka24.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Data
public class ProductCriteriaDTO implements Serializable {

    private Set<UUID> productIdIn;

    private String titleLike;

    private String descriptionLike;

    private BigDecimal priceMoreThan;

    private BigDecimal priceLessThan;
}
