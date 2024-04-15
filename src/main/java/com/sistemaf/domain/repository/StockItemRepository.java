package com.sistemaf.domain.repository;

import com.sistemaf.domain.filter.StockItemFilter;
import com.sistemaf.domain.model.StockItem;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface StockItemRepository {
    Optional<StockItem> findBySerial(String serial);

    StockItem save(StockItem stockItem);

    Optional<StockItem> findById(UUID id);

    Page<StockItem> findWithFilter(StockItemFilter stockItemFilter, Object o);
}
