package com.sistemaf.domain.usecases.stock;

import com.sistemaf.domain.contracts.stock.ChangeActiveStockItemService;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.StockItem;
import com.sistemaf.domain.repository.estoque.stockitem.StockItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ChangeActiveStockItemUseCase implements ChangeActiveStockItemService {

    private StockItemRepository stockItemRepository;
    @Override
    public void perform(UUID id, Boolean active) {
        Optional<StockItem> findStockItem = this.stockItemRepository.findById(id);
        if(findStockItem.isEmpty()) {
            throw new EntityNotFoundException("O item do stock nao foi encontrado");
        }
    }
}
