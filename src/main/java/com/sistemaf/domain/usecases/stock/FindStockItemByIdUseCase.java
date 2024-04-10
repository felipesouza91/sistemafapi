package com.sistemaf.domain.usecases.stock;

import com.sistemaf.domain.contracts.stock.FindStockItemByIdService;
import com.sistemaf.domain.model.StockItem;
import com.sistemaf.domain.repository.StockItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class FindStockItemByIdUseCase implements FindStockItemByIdService {

    private final StockItemRepository stockItemRepository;
    @Override
    public StockItem perform(UUID id) {
        this.stockItemRepository.findById(id);
        return null;
    }
}
