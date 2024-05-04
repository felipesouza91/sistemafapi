package com.sistemaf.domain.contracts.stock;

import com.sistemaf.domain.model.StockItem;

import java.util.UUID;

public interface UpdateStockItemService {

    StockItem perform(UUID id, StockItem data);
}
