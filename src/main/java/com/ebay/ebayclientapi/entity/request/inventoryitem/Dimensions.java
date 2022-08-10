package com.ebay.ebayclientapi.entity.request.inventoryitem;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "height",
    "length",
    "unit",
    "width"
})
public class Dimensions {
    
    @JsonProperty("height")
    private String height;

    @JsonProperty("length")
    private String length;

    @JsonProperty("unit")
    private String unit;

    @JsonProperty("width")
    private String width;
}
