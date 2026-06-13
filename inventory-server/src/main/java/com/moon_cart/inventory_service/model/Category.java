package com.moon_cart.inventory_service.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
     use = JsonTypeInfo.Id.NAME, //tells Jackson to use a simple logical name
     include = JsonTypeInfo.As.PROPERTY, //tells Jackson where to look for that logical name inside the incoming JSON payload.
     property = "type"  //this defines exact key name
)
@JsonSubTypes({ //acts like a Switch case router for api
    @JsonSubTypes.Type(value = DatedCategory.class, name = "ELECTRONICS"),
    @JsonSubTypes.Type(value = DatedCategory.class, name = "KIDS_TOYS"),
    @JsonSubTypes.Type(value = DatedCategory.class, name = "FURNITURE"),
    @JsonSubTypes.Type(value = DatedCategory.class, name = "GROCERIES"),
    @JsonSubTypes.Type(value = ClothingCategory.class, name = "CLOTHS")
})
//We store products in the inventory or warehouse based on the categories
public abstract class Category {
}
