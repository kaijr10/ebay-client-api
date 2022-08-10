package com.ebay.ebayclientapi.entity.request.inventoryitem;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "unit",
    "value"
})
public class Weight {
    
    @JsonProperty("unit")
    private String unit;
    
    @JsonProperty("value")
    private String value;
}
