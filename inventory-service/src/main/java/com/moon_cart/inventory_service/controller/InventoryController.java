package com.moon_cart.inventory_service.controller;

import com.moon_cart.inventory_service.dto.InventoryRequestDTO;
import com.moon_cart.inventory_service.dto.InventoryResponseDTO;
import com.moon_cart.inventory_service.dto.StockUpdateRequest;
import com.moon_cart.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory/api")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService service;

    /**
     * This API is used to find the stock is present in the inventory.
     * We use {@link GetMapping} to retrieve the response.
     * If we found the product with given ske-code and has the quantity,
     * of given quantity then we will get {@link ResponseStatus} 200
     * @param request contains the {@link StockUpdateRequest} information, it contains sku-code and quantity number.
     * @return {@link Boolean} response.*/
    @GetMapping
    public ResponseEntity<Boolean> isInStock(StockUpdateRequest request) {
        return ResponseEntity.ok(service.isInStock(request.skuCode(), request.quantity()));
    }

    /**
     * This API is used to reduce the given number quantity from particular product.
     * We use {@link PatchMapping} because we are only applying particular parameter only.
     * @param request contains the {@link StockUpdateRequest} information, it contains sku-code and quantity number.
     * @return {@link String} message.*/
    @PatchMapping("/orderPlaced")
    public ResponseEntity<String> reduceStock (StockUpdateRequest request) {
        service.reduceStock(request.skuCode(), request.quantity());
        return ResponseEntity.status(HttpStatus.OK).body("Stock has been reduced");
    }

    /**
     * This API is used to add the product to the inventory.
     * We use {@link PostMapping} to save data to the database.
     * @param requestDTO contains the information about the product.
     * @return {@link InventoryResponseDTO}*/
    @PostMapping
    public ResponseEntity<InventoryResponseDTO> addStock (@RequestBody InventoryRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addToStock(requestDTO));
    }

    /**
     * This API is used to get the all product information present in the inventory.
     * We use {@link GetMapping} to retrieve the information.
     * @return {@link List} of {@link InventoryResponseDTO}*/
    @GetMapping("/all")
    public ResponseEntity<List<InventoryResponseDTO>> getAllProducts () {
        return ResponseEntity.ok(service.inventoryList());
    }

    /**
     * This API is used to add the new quantity to the existing quantity.
     * we use {@link PatchMapping} for updating.
     * @param request contains the {@link StockUpdateRequest} information, it contains sku-code and quantity number.
     * @return {@link InventoryResponseDTO}*/
    @PatchMapping("/update")
    public ResponseEntity<InventoryResponseDTO> addQuantity (StockUpdateRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateQuantity(request.skuCode(), request.quantity()));
    }
}
