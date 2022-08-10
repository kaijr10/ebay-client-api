package com.ebay.ebayclientapi.entity.request.inventoryitem;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "pickupAtLocationAvailability",
    "shipToLocationAvailability"
})
public class Availability {
    
    @JsonProperty("pickupAtLocationAvailability")
    private List<PickupAtLocationAvailability> pickupAtLocationAvailability = null;

    @JsonProperty("shipToLocationAvailability")
    private ShipToLocationAvailability shipToLocationAvailability;
}
