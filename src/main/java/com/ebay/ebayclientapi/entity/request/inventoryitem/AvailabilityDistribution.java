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
    "fulfillmentTime",
    "merchantLocationKey",
    "quantity"
})
public class AvailabilityDistribution {
    
    @JsonProperty("fulfillmentTime")
    private FulfillmentTime fulfillmentTime;

    @JsonProperty("merchantLocationKey")
    private String merchantLocationKey;

    @JsonProperty("quantity")
    private Integer quantity;
}
