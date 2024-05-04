package com.sistemaf.domain.repository.estoque.stockitem;

import com.sistemaf.domain.filter.StockItemFilter;
import com.sistemaf.domain.model.StockItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindStockItemQuery {

    Page<StockItem> findWithFilter(StockItemFilter stockItemFilter, Pageable page);

}
