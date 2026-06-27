package com.moon_cart.product_service.repository;

import com.moon_cart.product_service.model.ProductInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<ProductInfo, String> {

    Boolean existsBySkuCodeStartingWith(String skuCode);

    // Exact match
    List<ProductInfo> findByTags(String tag);

    // Partial match
    List<ProductInfo> findByTagsContaining(String keyword);

    // Range query with BigDecimal
    List<ProductInfo> findByPriceBetween(BigDecimal startingPrice, BigDecimal endingPrice);
    List<ProductInfo> findByNameContaining(String keyword);
    List<ProductInfo> findByCategoryName(String category);
}

