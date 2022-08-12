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

@Service
public class InventoryApiServiceImpl implements InventorrApiService {

    @Value("${inventory.api.bulk_create_or_replace_inventory_item.url}")
    private String rootUrl;

    @Value("{inventory.api.access_token")
    private String accessToken;

    @Autowired
    private HttpRequestUtility httpRequestUtility;

    @Autowired
    private RestTemplate restTemplate;
    
    @Override
    public String bulkCreateInventoryItem(InventoryItemRequest inventoryItemRequest) {
        try {
            // generate header
            HttpHeaders httpHeaders = httpRequestUtility.buildHttpHeaders(MediaType.MULTIPART_FORM_DATA, accessToken);
            HttpEntity<InventoryItemRequest> request = new HttpEntity<>(inventoryItemRequest, httpHeaders);
            // send post request
            ResponseEntity<String> response = restTemplate.postForEntity(rootUrl, request, String.class);
            return response.getBody();
        } catch (Exception ex) {
            throw new RestClientException("Failed to call external Inventory API to create bulk inventory");
        }
    }
}
