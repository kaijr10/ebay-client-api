package com.ebay.ebayclientapi.service.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.ebay.ebayclientapi.config.HttpRequestUtility;
import com.ebay.ebayclientapi.entity.request.inventoryitem.InventoryItemRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InventoryApiServiceImpl implements InventorrApiService {

    @Value("${inventory.api.bulk_create_or_replace_inventory_item.url}")
    private String rootUrl;

    @Value("${inventory.api.access_token}")
    private String accessToken;

    @Autowired
    private HttpRequestUtility httpRequestUtility;

    @Autowired
    private RestTemplate restTemplate;
    
    @Override
    public String bulkCreateInventoryItem(InventoryItemRequest inventoryItemRequest) {
        try {
            // generate header
            String apiUrl = rootUrl + "/bulk_create_or_replace_inventory_item";
            HttpHeaders httpHeaders = httpRequestUtility.buildHttpHeaders(MediaType.APPLICATION_JSON, accessToken);
            ObjectMapper mapper = new ObjectMapper();
            log.info(rootUrl);
            log.info(accessToken);
            log.info(mapper.writeValueAsString(inventoryItemRequest));
            HttpEntity<String> request = new HttpEntity<>(mapper.writeValueAsString(inventoryItemRequest), httpHeaders);
            // send post request
            String response = restTemplate.postForObject(apiUrl, request, String.class);
            return response;
        } catch (Exception ex) {
            throw new RestClientException("Failed to call external Inventory API to create bulk inventory - err " + ex.getMessage());
        }
    }
}
