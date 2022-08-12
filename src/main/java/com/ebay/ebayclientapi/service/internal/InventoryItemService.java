package com.ebay.ebayclientapi.service.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ebay.ebayclientapi.entity.request.inventoryitem.InventoryItemRequest;
import com.ebay.ebayclientapi.exception.InvalidException;
import com.ebay.ebayclientapi.helper.CsvHelper;
import com.ebay.ebayclientapi.service.external.InventorrApiService;

@Service
public class InventoryItemService {

    @Autowired
    private InventorrApiService inventorrApiService;

    public String createBulkInventoryItem(MultipartFile csvUploadFile) {
        try {
            InventoryItemRequest inventoryItemRequest = CsvHelper.parseCsvFile(csvUploadFile.getInputStream());
            String response = inventorrApiService.bulkCreateInventoryItem(inventoryItemRequest);
            return response;
        } catch (Exception ex) {
            throw new InvalidException("Failed to create buil inventory item");
        }
    }
}
