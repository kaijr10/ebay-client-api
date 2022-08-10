package com.ebay.ebayclientapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ebay.ebayclientapi.entity.request.inventoryitem.InventoryItemRequest;
import com.ebay.ebayclientapi.service.internal.InventoryItemService;

@Controller
@RequestMapping("/inventory_item/")
public class InventoryItemController {
    
    @Autowired
    private InventoryItemService inventoryItemService;
    
    @PostMapping("/create")
    public ResponseEntity<?> createInventoryItem(@RequestBody InventoryItemRequest inventoryItemRequest) {
        inventoryItemService.createBulkInventoryItem(inventoryItemRequest);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
