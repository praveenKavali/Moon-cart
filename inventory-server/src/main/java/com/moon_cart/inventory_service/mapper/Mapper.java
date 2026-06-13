package com.moon_cart.inventory_service.mapper;

import com.moon_cart.inventory_service.dto.InventoryRequestDTO;
import com.moon_cart.inventory_service.dto.InventoryResponseDTO;
import com.moon_cart.inventory_service.model.Inventory;

public class Mapper {
    
    public static Inventory RequestToInventory (InventoryRequestDTO requestDTO) {
        return Inventory.builder()
                .name(requestDTO.getName())
                .skuCode(requestDTO.getSkuCode())
                .quantity(requestDTO.getQuantity())
                .categories(requestDTO.getCategories())
                .timeStamp(requestDTO.getTimeStamp())
                .build();
    }
    
    public static InventoryResponseDTO InventoryToResponse (Inventory inventory) {
        return InventoryResponseDTO.builder()
                .id(inventory.getId())
                .name(inventory.getName())
                .skuCode(inventory.getSkuCode())
                .quantity(inventory.getQuantity())
                .categories(inventory.getCategories())
                .timeStamp(inventory.getTimeStamp())
                .build();
    }
}

