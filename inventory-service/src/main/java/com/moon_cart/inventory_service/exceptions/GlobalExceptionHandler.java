package com.moon_cart.inventory_service.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<InventoryExceptions> handleNotFoundException (NotFoundException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<InventoryExceptions> handleBadRequestException (BadRequestException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex);
    }
}
