package com.sistemaf.domain.repository;

import com.sistemaf.domain.model.StockItem;

import java.util.Optional;

public interface StockItemRepository {
    Optional<StockItem> findBySerial(String serial);

    StockItem save(StockItem stockItem);
}
