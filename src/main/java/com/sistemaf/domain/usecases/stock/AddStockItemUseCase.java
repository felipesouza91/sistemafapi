package com.sistemaf.domain.usecases.stock;

import com.sistemaf.domain.contracts.stock.AddStockItemService;
import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.model.StockItem;
import com.sistemaf.domain.repository.StockItemRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AddStockItemUseCase implements AddStockItemService {

    private final StockItemRepository stockItemRepository;
    @Override
    public StockItem perform(StockItem data) {
        Optional<StockItem> usedStockItem = this.stockItemRepository.findBySerial(data.getSerial());
        if(usedStockItem.isPresent()) {
            throw new BusinessException("O serial já esta cadastrado");
        }
        this.stockItemRepository.save(data);
        return null;
    }
}
