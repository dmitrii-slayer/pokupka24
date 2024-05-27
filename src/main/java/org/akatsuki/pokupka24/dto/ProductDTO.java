package org.akatsuki.pokupka24.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ProductDTO {

    private UUID productId;

    private String title;

    private String description;

    private BigDecimal price;
}
