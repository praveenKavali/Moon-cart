package com.moon_cart.product_service.dto;

import com.moon_cart.product_service.model.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    @NonNull
    private String name;
    private String description;
    @Positive
    private BigDecimal price;
    private String tags;
    @Min(value = 1)
    private Integer quantity;
    private Category category;
}
