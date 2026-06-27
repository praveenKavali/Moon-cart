package com.moon_cart.product_service.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME, // tells the Json to use simple logical name
        include = JsonTypeInfo.As.PROPERTY, // tells Jackson where to look for that logical name inside the coming Json payload.
        property = "type" // Key name
)

@JsonSubTypes({
        @JsonSubTypes.Type(value = DateCategory.class, name = "ELECTRONICS"),
        @JsonSubTypes.Type(value = DateCategory.class, name = "KIDS_TOYS"),
        @JsonSubTypes.Type(value = DateCategory.class, name = "FURNITURE"),
        @JsonSubTypes.Type(value = DateCategory.class, name = "GROCERIES"),
        @JsonSubTypes.Type(value = SizeCategory.class, name = "CLOTHS")
})
@Getter
@Setter
public sealed abstract class Category permits DateCategory, SizeCategory{
    private String name;
}
