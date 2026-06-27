package com.moon_cart.product_service.exeptions;

import lombok.Getter;

@Getter
public abstract class ProductException extends RuntimeException{
    private final String errorCode;
    private final int statusCode;

    public ProductException (String message, String errorCode, int statusCode) {
        super(message);
        this.errorCode = errorCode;
        this.statusCode = statusCode;
    }
}
