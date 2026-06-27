package com.moon_cart.inventory_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.moon_cart.inventory_service.exceptions.BadRequestException;

public record StockUpdateRequest(
        @JsonProperty("skuCode") String skuCode,
        @JsonProperty("quantity") Integer quantity) {
    public StockUpdateRequest{
        if (quantity == null|| quantity < 1)
            throw new BadRequestException("Quantity is negative: " + quantity, "BAD_REQUEST", 400);
        if (skuCode == null || skuCode.isBlank())
            throw new BadRequestException("Not a valid sku code.", "BAD_REQUEST", 400);
    }
}
