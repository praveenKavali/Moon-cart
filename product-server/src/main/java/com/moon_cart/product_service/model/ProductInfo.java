package com.moon_cart.product_service.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductInfo {
    @Id
    private String id;
    private String name;
    private String description;
    @Positive
    private BigDecimal price;
    private String tags;
    private String skuCode;
    @Min(value = 1)
    private Integer quantity;
    private Category category;
}
