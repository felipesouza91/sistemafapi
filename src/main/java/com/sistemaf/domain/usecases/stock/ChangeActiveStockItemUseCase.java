package com.sistemaf.domain.usecases.stock;

import com.sistemaf.domain.contracts.stock.ChangeActiveStockItemService;
import com.sistemaf.domain.repository.estoque.stockitem.StockItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ChangeActiveStockItemUseCase implements ChangeActiveStockItemService {

    private StockItemRepository stockItemRepository;
    @Override
    public void perform(UUID id, Boolean active) {
        this.stockItemRepository.findById(id);
    }
}
