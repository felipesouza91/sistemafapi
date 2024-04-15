package com.sistemaf.domain.usecases.stock;

import com.sistemaf.domain.contracts.stock.UpdateStockItemService;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.StockItem;
import com.sistemaf.domain.repository.StockItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UpdateStockItemUseCase implements UpdateStockItemService {

    private StockItemRepository stockItemRepository;

    @Override
    public StockItem perform(UUID id, StockItem data) {
        Optional<StockItem> exitsStockItem = this.stockItemRepository.findById(id);
        if(exitsStockItem.isEmpty()) {
            throw new EntityNotFoundException("O item não foi encontrado");
        }
        return null;
    }
}
