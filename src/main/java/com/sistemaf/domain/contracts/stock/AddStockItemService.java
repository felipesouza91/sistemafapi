package com.sistemaf.domain.contracts.stock;

import com.sistemaf.domain.model.StockItem;

public interface AddStockItemService {
    StockItem perform(StockItem data);
}
