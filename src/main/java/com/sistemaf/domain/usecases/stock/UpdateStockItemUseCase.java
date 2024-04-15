package com.sistemaf.domain.usecases.stock;

import com.sistemaf.domain.contracts.stock.UpdateStockItemService;
import com.sistemaf.domain.model.StockItem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UpdateStockItemUseCase implements UpdateStockItemService {
    @Override
    public StockItem perform(UUID id, StockItem data) {
        return null;
    }
}
