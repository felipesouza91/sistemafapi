package com.sistemaf.api.resource.stock;

import com.sistemaf.api.dto.input.StockItemInput;
import com.sistemaf.api.dto.manager.StockItemMapper;
import com.sistemaf.domain.contracts.stock.StockItemService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/stock/items")
public class StockItemResource {

    private StockItemMapper stockItemMapper  = StockItemMapper.INSTANCE;

    @Autowired
    private StockItemService stockItemService;


    @PostMapping
    public void createNewStockItem(@Valid  @RequestBody StockItemInput stockItemInput) {
        stockItemService.save(stockItemMapper.toModel(stockItemInput));
    }

}
