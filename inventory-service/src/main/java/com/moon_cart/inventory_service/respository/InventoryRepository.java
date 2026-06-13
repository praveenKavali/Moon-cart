package com.moon_cart.inventory_service.respository;

import com.moon_cart.inventory_service.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends MongoRepository<Inventory, String> {
    Optional<Inventory> getBySkuCode(String skuCode);
}
