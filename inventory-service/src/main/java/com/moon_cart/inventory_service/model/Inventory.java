package com.moon_cart.inventory_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Document
public class Inventory {
    @Id
    private String id;
    private String name;
    private String skuCode;
    private int quantity;
    @Field(name = "LastUpdatedAt")
    private Instant timeStamp;
    @Field(name = "item_category")
    private Category categories;
}
