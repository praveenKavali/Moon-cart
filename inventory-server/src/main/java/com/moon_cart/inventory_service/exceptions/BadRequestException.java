package com.moon_cart.inventory_service.exceptions;

public class BadRequestException extends InventoryExceptions{
    public BadRequestException(String message, String errorCode, int statusCode) {
        super(message, errorCode, statusCode);
    }
}
