package org.akatsuki.pokupka24.dto;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ProductDTO {

    private UUID productId;

    @Size(max = 120)
    private String title;

    @Size(max = 255)
    private String description;

    @PositiveOrZero(message = "Price can't be less than 0!")
    private BigDecimal price;
}
