package com.moon_cart.product_service.exeptions;

public class ProductExistException extends ProductException{
    public ProductExistException(String message, String errorCode, int statusCode) {
        super(message, errorCode, statusCode);
    }
}
