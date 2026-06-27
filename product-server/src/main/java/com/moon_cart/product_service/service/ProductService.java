package com.moon_cart.product_service.service;

import com.moon_cart.product_service.dto.ProductRequest;
import com.moon_cart.product_service.dto.ProductResponse;
import com.moon_cart.product_service.exeptions.ProductExistException;
import com.moon_cart.product_service.mapper.Mapper;
import com.moon_cart.product_service.model.ProductInfo;
import com.moon_cart.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final Mapper mapper;

    /**
     * Using this method we can create product information.
     * @param name is the produce the product.
     * @param request contains the product information given by the dealer.
     * @return {@link ProductResponse} object.*/
    public ProductResponse createProduct (String name, ProductRequest request) {
        if (repository.existsBySkuCodeStartingWith(name + request.getName())) {
            throw new ProductExistException("Product already exist in the database.", "PRODUCT_ALREADY_EXIST", 409);
        }
        ProductInfo info = mapper.requestToProduct(request);
        info.setSkuCode(name + request.getName() + UUID.randomUUID().toString());
        ProductResponse response = mapper.productToResponse(repository.save(info));
        log.info("product is added to cart with skuCode, {}", response.getSkuCode());
        return response;
    }

    /**
     * We use this method to get all the product from database.
     * @return a {@link List} of {@link ProductResponse}*/
    public List<ProductResponse> getAllProduct () {
        return repository.findAll().stream().map(mapper::productToResponse).toList();
    }

    /**
     * We use this method to get list of products by tag names, such as Phones.
     * @param tags used to find the list of tags from database.
     * @return a {@link List} of {@link ProductResponse}*/
    public List<ProductResponse> findByTags (String tags) {
        return repository.findByTagsContaining(tags).stream().map(mapper::productToResponse).toList();
    }

    /**
     * We use this method to get list of products by price range.
     * @param startingPrice initial price or lowest price in range.
     * @param endingPrice end or highest price.
     * @return a {@link List} of {@link ProductResponse}*/
    public List<ProductResponse> findByPriceRange (BigDecimal startingPrice, BigDecimal endingPrice) {
        return repository.findByPriceBetween(startingPrice, endingPrice).stream().map(mapper::productToResponse).toList();
    }

    /**
     * We use this method to get list of products by names, such as Samsung.
     * @param name used to find the list of products from database.
     * @return a {@link List} of {@link ProductResponse}*/
    public List<ProductResponse> findByName (String name) {
        return repository.findByNameContaining(name).stream().map(mapper::productToResponse).toList();
    }

    /**
     * We use this method to get list of products by category type.
     * @param category used to find the list of products from database based on category.
     * @return a {@link List} of {@link ProductResponse}*/
    public List<ProductResponse> findByCategoryName (String category) {
        return repository.findByCategoryName(category).stream().map(mapper::productToResponse).toList();
    }
}
