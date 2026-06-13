package com.moon_cart.inventory_service.exceptions;

import lombok.Getter;

@Getter
public abstract class InventoryExceptions extends RuntimeException {
    private final String errorCode;
    private final int statusCode;

    public InventoryExceptions (String message, String errorCode, int statusCode) {
        super(message);
        this.errorCode = errorCode;
        this.statusCode = statusCode;
    }
}
