package com.sistemaf.domain.usecases.stock;

import com.sistemaf.domain.contracts.stock.AddStockItemService;
import com.sistemaf.domain.model.StockItem;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Service;

@Service
public class AddStockItemUseCase implements AddStockItemService {
    @Override
    public StockItem perform(StockItem data) {
        return null;
    }
}
