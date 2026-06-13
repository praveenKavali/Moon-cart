package com.moon_cart.inventory_service.dto;

import com.moon_cart.inventory_service.model.Category;
import lombok.*;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class InventoryResponseDTO {
    private String id;
    private String name;
    private String skuCode;
    private int quantity;
    private Instant timeStamp;
    private Category categories;
}
