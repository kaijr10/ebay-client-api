package com.ebay.ebayclientapi.entity.request.inventoryitem;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "availability",
    "condition",
    "conditionDescription",
    "locale",
    "packageWeightAndSize",
    "product",
    "sku"
})
public class Request {
    
    @JsonProperty("availability")
    private Availability availability;

    @JsonProperty("condition")
    private String condition;

    @JsonProperty("conditionDescription")
    private String conditionDescription;
    
    @JsonProperty("locale")
    private String locale;

    @JsonProperty("packageWeightAndSize")
    private PackageWeightAndSize packageWeightAndSize;

    @JsonProperty("product")
    private Product product;

    @JsonProperty("sku")
    private String sku;
}
