package com.ebay.ebayclientapi.entity.request.inventoryitem;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "availabilityDistributions",
    "quantity"
})
public class ShipToLocationAvailability {
    
    @JsonProperty("availabilityDistributions")
    private List<AvailabilityDistribution> availabilityDistributions = null;

    @JsonProperty("quantity")
    private Integer quantity;
}
