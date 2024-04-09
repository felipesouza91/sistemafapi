package com.sistemaf.domain.usecases.stock;

import com.sistemaf.domain.contracts.stock.AddStockItemService;
import com.sistemaf.domain.model.StockItem;
import com.sistemaf.domain.repository.StockItemRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddStockItemUseCase implements AddStockItemService {

    private final StockItemRepository stockItemRepository;
    @Override
    public StockItem perform(StockItem data) {
        this.stockItemRepository.findBySerial(data.getSerial());
        return null;
    }
}
