package com.moon_cart.product_service.contrller;

import com.moon_cart.product_service.dto.ProductRequest;
import com.moon_cart.product_service.dto.ProductResponse;
import com.moon_cart.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    /**
     * We use this method to post new product information to the database.
     * We use post method to create a new entity to the database.
     * we can get CREATE as response status code.
     * @param name it is the name of the person who creating this product.
     * @param request contains the product details.
     * @return {@link ProductResponse} object.*/
    @PostMapping("/{name}")
    public ResponseEntity<ProductResponse> createProduct (@PathVariable String name,
                                                          @RequestBody ProductRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createProduct(name, request));
    }

    /**
     * We use this method to get all the Product information from the database.
     * @return a list of {@link ProductResponse}*/
    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAllProducts () {
        return ResponseEntity.ok(service.getAllProduct());
    }

    /**
     * We use this method to get all the Product of specific tag information from the database.
     * @param tag is the particular tag user want to search from the database.
     * @return a list of {@link ProductResponse}*/
    @GetMapping("/tag/{tag}")
    public ResponseEntity<List<ProductResponse>> findAllTag (@PathVariable String tag) {
        return ResponseEntity.ok(service.findByTags(tag));
    }

    /**
     * We use this method to get all the Product in specific price range information from the database.
     * @param price1 is the starting price.
     * @param price2 is the ending price.
     * @return a list of {@link ProductResponse}*/
    @GetMapping("/{price1}/{price2}")
    public ResponseEntity<List<ProductResponse>> findByPriceRange (@PathVariable BigDecimal price1,
                                                                   @PathVariable BigDecimal price2) {
        return ResponseEntity.ok(service.findByPriceRange(price1, price2));
    }

    /**
     * We use this method to get all the Product with specific name from the database.
     * @param name is the particular name, user enter to search from the database.
     * @return a list of {@link ProductResponse}*/
    @GetMapping("/name/{name}")
    public ResponseEntity<List<ProductResponse>> findByName (@PathVariable String name) {
        return ResponseEntity.ok(service.findByName(name));
    }

    /**
     * We use this method to get all the Product of specific category name from the database.
     * @param category is the particular category name user want to search from the database.
     * @return a list of {@link ProductResponse}*/
    @GetMapping("/{category}")
    public ResponseEntity<List<ProductResponse>> findByCategoryName (@PathVariable String category) {
        return ResponseEntity.ok(service.findByCategoryName(category));
    }
}
