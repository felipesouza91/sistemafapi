package com.sistemaf.domain.contracts.stock;

import com.sistemaf.domain.model.StockItem;

import java.util.UUID;

public interface FindStockItemByIdService {

    StockItem perform(UUID id);
}
