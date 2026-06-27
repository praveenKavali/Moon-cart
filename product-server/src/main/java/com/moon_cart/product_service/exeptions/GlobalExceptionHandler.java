package com.moon_cart.product_service.exeptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductExistException.class)
    public ResponseEntity<ProductException> handlersProductExistException (ProductExistException ex) {
        return ResponseEntity.status(409).body(ex);
    }
}
