package com.moon_cart.inventory_service.exceptions;

public class NotFoundException extends InventoryExceptions{
    public NotFoundException(String message, String errorCode, int statusCode) {
        super(message, errorCode, statusCode);
    }
}
