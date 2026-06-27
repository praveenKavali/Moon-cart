package com.moon_cart.product_service.mapper;

import com.moon_cart.product_service.dto.ProductRequest;
import com.moon_cart.product_service.dto.ProductResponse;
import com.moon_cart.product_service.model.ProductInfo;
import org.springframework.stereotype.Component;

//It a mapper class convert one object into another object.
@Component
public class Mapper {

    /**
     * We use this method to convert {@link ProductRequest} object to {@link ProductInfo}
     * @param request which contains the user enter details about product.
     * @return {@link ProductInfo} object.*/
    public ProductInfo requestToProduct(ProductRequest request) {
        return ProductInfo.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .tags(request.getTags())
                .quantity(request.getQuantity())
                .category(request.getCategory())
                .build();
    }

    /**
     * We use this method to convert {@link ProductInfo} object to {@link ProductResponse}
     * @param product which contains the data from the database.
     * @return {@link ProductResponse} object.*/
    public ProductResponse productToResponse (ProductInfo product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .tags(product.getTags())
                .skuCode(product.getSkuCode())
                .quantity(product.getQuantity())
                .category(product.getCategory())
                .build();
    }
    
}
