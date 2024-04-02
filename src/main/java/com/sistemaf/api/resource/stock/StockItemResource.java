package com.sistemaf.api.resource.stock;

import com.sistemaf.api.dto.input.StockItemInput;
import com.sistemaf.api.dto.manager.StockItemMapper;
import com.sistemaf.api.dto.model.StockItemDTO;
import com.sistemaf.domain.contracts.stock.AddStockItemService;
import com.sistemaf.domain.event.RecursoCriarEvent;
import com.sistemaf.domain.model.StockItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@RestController
@RequestMapping("/stock/items")
public class StockItemResource {

    private StockItemMapper stockItemMapper  = StockItemMapper.INSTANCE;

    @Autowired
    private AddStockItemService addStockItemService;

    @Autowired
    private ApplicationEventPublisher publisher;
    @PostMapping
    public ResponseEntity<StockItemDTO> createNewStockItem(@Valid  @RequestBody StockItemInput stockItemInput, HttpServletResponse response) {
       StockItem stockItem =  addStockItemService.perform(stockItemMapper.toModel(stockItemInput));
        return ResponseEntity.status(HttpStatus.CREATED).body(stockItemMapper.toDTO(stockItem));
    }

}
