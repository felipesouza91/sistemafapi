package com.sistemaf.domain.repository.estoque.stockitem;

import com.sistemaf.domain.model.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StockItemRepository extends JpaRepository<StockItem, UUID>, FindStockItemQuery {
    Optional<StockItem> findBySerial(String serial);

    StockItem save(StockItem stockItem);

    Optional<StockItem> findById(UUID id);

}
