package com.ebay.ebayclientapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ebay.ebayclientapi.service.internal.InventoryItemService;

@Controller
@RequestMapping("/inventory_item/")
public class InventoryItemController {
    
    @Autowired
    private InventoryItemService inventoryItemService;

    @PostMapping("/create")
    public ResponseEntity<?> createInventoryItem(@RequestParam MultipartFile csvUploadFile) {
        if (csvUploadFile.isEmpty()) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
        try {
            String response = inventoryItemService.createBulkInventoryItem(csvUploadFile);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Failed to process csv file uploaded", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
