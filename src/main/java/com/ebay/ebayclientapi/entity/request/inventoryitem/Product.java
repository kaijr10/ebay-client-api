package com.ebay.ebayclientapi.entity.request.inventoryitem;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "aspects",
    "brand",
    "description",
    "ean",
    "epid",
    "imageUrls",
    "isbn",
    "mpn",
    "subtitle",
    "title",
    "upc",
    "videoIds"
})
public class Product {
    
    @JsonProperty("aspects")
    private String aspects;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("description")
    private String description;

    @JsonProperty("ean")
    private List<String> ean = null;

    @JsonProperty("epid")
    private String epid;

    @JsonProperty("imageUrls")
    private List<String> imageUrls = null;

    @JsonProperty("isbn")
    private List<String> isbn = null;

    @JsonProperty("mpn")
    private String mpn;

    @JsonProperty("subtitle")
    private String subtitle;

    @JsonProperty("title")
    private String title;

    @JsonProperty("upc")
    private List<String> upc;

    @JsonProperty("videoIds")
    private List<String> videoIds;
}
