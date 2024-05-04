package com.sistemaf.domain.usecases.stock;

import com.sistemaf.domain.contracts.stock.FindStockItemServices;
import com.sistemaf.domain.filter.StockItemFilter;
import com.sistemaf.domain.model.StockItem;
import com.sistemaf.domain.repository.estoque.stockitem.StockItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindStockItemServicesUseCase implements FindStockItemServices {

    private StockItemRepository stockItemRepository;
    @Override
    public Page<StockItem> perform(StockItemFilter stockItemFilter, Pageable pageable) {
        return this.stockItemRepository.findWithFilter(stockItemFilter, pageable);
    }
}
