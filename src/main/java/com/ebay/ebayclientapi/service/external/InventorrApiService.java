package com.ebay.ebayclientapi.service.external;

import com.ebay.ebayclientapi.entity.request.inventoryitem.InventoryItemRequest;

public interface InventorrApiService {

    public String bulkCreateInventoryItem(InventoryItemRequest request);
}
