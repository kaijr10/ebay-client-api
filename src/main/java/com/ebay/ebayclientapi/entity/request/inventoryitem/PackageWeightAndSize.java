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
    "dimensions",
    "packageType",
    "weight"
})
public class PackageWeightAndSize {
    
    @JsonProperty("dimensions")
    private Dimensions dimensions;

    @JsonProperty("packageType")
    private String packageType;

    @JsonProperty("weight")
    private Weight weight;
}
