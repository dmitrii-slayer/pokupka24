package org.akatsuki.pokupka24.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ProductDTO {

    private UUID productId;

    @Size(max = 120)
    private String title;

    private String description;

    private BigDecimal price;
}
