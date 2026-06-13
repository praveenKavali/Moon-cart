package com.moon_cart.inventory_service.service;

import com.moon_cart.inventory_service.dto.InventoryRequestDTO;
import com.moon_cart.inventory_service.dto.InventoryResponseDTO;
import com.moon_cart.inventory_service.exceptions.BadRequestException;
import com.moon_cart.inventory_service.exceptions.NotFoundException;
import com.moon_cart.inventory_service.mapper.Mapper;
import com.moon_cart.inventory_service.model.Inventory;
import com.moon_cart.inventory_service.respository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final InventoryRepository repository;

    /**
     * This method is used to check if the stock is available in warehouse or inventory, to place an order.
     * @param skuCode used to find the specified item from the database.
     * @param requestedQuantity used to check if that many products are present or not.
     * @return boolean result if the stock is available.
     * @throws NotFoundException if no stock present with given sku-code
     * @throws BadRequestException if the requestedQuantity is negative*/
    public Boolean isInStock (String skuCode, Integer requestedQuantity) {
        Inventory inventory = getInventoryOrThrow(skuCode);
        return inventory.getQuantity() >= requestedQuantity;
    }

    /**
     * This method is used to reduce the stock, if user place an order we have to reduce the stock.
     * @param skuCode used to find the specific produce from the inventory.
     * @param reduceStock is the number of items placed by the user.
     * @throws BadRequestException if the reduceStock is negative.*/
    public void reduceStock (String skuCode, Integer reduceStock) {
        Inventory inventory = getInventoryOrThrow(skuCode);
        assignNewQuantity(inventory, reduceStock, false);
        Inventory inventory1 = repository.save(inventory);
        log.info("Stock is reduced from inventory. The current stock is {}", inventory1.getQuantity());
    }

    /**
     * This method is used to store stock into inventory service.
     * @param requestDTO which contains the product details.
     * @return {@link InventoryResponseDTO}*/
    public InventoryResponseDTO addToStock (InventoryRequestDTO requestDTO) {
        Inventory inventory = Mapper.RequestToInventory(requestDTO);
        inventory.setSkuCode(UUID.randomUUID().toString());
        inventory.setTimeStamp(Instant.now());
        Inventory inventory1 = repository.save(inventory);
        log.info("Product is added to the inventory.");
        return Mapper.InventoryToResponse(inventory1);
    }

    /**
     * This method is used to get the all product details from inventory.
     * @return {@link List} of {@link InventoryResponseDTO} objects*/
    public List<InventoryResponseDTO> inventoryList () {
        return repository.findAll().stream().map(Mapper::InventoryToResponse).toList();
    }

    /**
     * This method is used to update or add quantity to the inventory.
     * @param skuCode used to find the specific product.
     * @param newQuantity adding this many products to the inventory.
     * @return {@link InventoryResponseDTO} after adding the new quantity.
     * @throws BadRequestException if quantity is negative.
     * @throws NotFoundException if product is not found with the given sku-code*/
    public InventoryResponseDTO updateQuantity (String skuCode, Integer newQuantity) {
        Inventory inventory = getInventoryOrThrow(skuCode);
        assignNewQuantity(inventory, newQuantity, true);
        Inventory inventory1 = repository.save(inventory);
        log.info("Quantity has been updated. New quantity {}", inventory1.getQuantity());
        return Mapper.InventoryToResponse(inventory1);
    }

    /**
     * This method is used to check if the product is present with given sku-code.
     * @param skuCode used to find the specific product.
     * @return {@link Inventory} if the product is present with the given sku-code.
     * @throws NotFoundException if the product is not found with the given sku-code.*/
    private Inventory getInventoryOrThrow (String skuCode) {
        return repository.getBySkuCode(skuCode).orElseThrow(() ->
                new NotFoundException("Stock with skuCode " + skuCode + " not found.", "Item_NOT_FOUND", 404));
    }

    /**
     * This method is used to assign new quantity(either adding or reducing) for an existing quantiry,
     * @param inventory we have to assign new quantity to this inventory.
     * @param quantity the new quantity that is need to either add or reduce.*/
    private void assignNewQuantity (Inventory inventory, int quantity, boolean operation) {
        int presentQuantity = inventory.getQuantity();
        if (operation) {
            inventory.setQuantity(presentQuantity + quantity);
        } else {
            inventory.setQuantity(presentQuantity - quantity);
        }
    }
}
