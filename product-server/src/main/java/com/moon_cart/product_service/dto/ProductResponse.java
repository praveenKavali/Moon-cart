package com.moon_cart.product_service.dto;

import com.moon_cart.product_service.model.Category;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private String tags;
    private String skuCode;
    private Integer quantity;
    private Category category;
}
