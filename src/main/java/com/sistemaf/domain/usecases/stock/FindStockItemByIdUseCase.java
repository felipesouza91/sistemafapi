package com.sistemaf.domain.usecases.stock;

import com.sistemaf.domain.contracts.stock.FindStockItemByIdService;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.StockItem;
import com.sistemaf.domain.repository.StockItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FindStockItemByIdUseCase implements FindStockItemByIdService {

    private final StockItemRepository stockItemRepository;
    @Override
    public StockItem perform(UUID id) {
        Optional<StockItem> existStockItem =  this.stockItemRepository.findById(id);
        if(existStockItem.isEmpty()) {
            throw new EntityNotFoundException("O Item não foi encontrado");
        }
        return null;
    }
}
