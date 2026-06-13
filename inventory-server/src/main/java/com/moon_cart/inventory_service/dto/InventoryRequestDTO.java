package com.moon_cart.inventory_service.dto;

import com.moon_cart.inventory_service.model.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class InventoryRequestDTO {
    @NotNull
    private String name;
    private String skuCode;
    @Min(value = 1)
    private int quantity;
    private Instant timeStamp;
    private Category categories;
}
