package com.sistemaf.domain.contracts.stock;

import com.sistemaf.api.dto.model.StockitemResumeDTO;
import com.sistemaf.domain.filter.StockItemFilter;
import com.sistemaf.domain.model.StockItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FindStockItemServices {
    Page<StockItem> perform(StockItemFilter stockItemFilter, Pageable pageable);
}
